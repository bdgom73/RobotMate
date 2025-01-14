package com.project.robotmate.admin.domain.gallery.service;

import cloud.aws.s3.S3UploadProvider;
import cloud.aws.s3.model.S3File;
import com.project.robotmate.admin.domain.common.file.dto.FileData;
import com.project.robotmate.admin.domain.common.file.dto.TargetFileData;
import com.project.robotmate.admin.domain.common.file.provider.ThumbnailProvider;
import com.project.robotmate.admin.domain.common.file.service.FileService;
import com.project.robotmate.admin.domain.gallery.dto.request.GalleryRequest;
import com.project.robotmate.admin.domain.gallery.dto.request.GalleryUpdateRequest;
import com.project.robotmate.admin.domain.gallery.dto.response.GalleryResponse;
import com.project.robotmate.core.types.DirectoryType;
import com.project.robotmate.core.types.TargetType;
import com.project.robotmate.domain.common.dto.Page;
import com.project.robotmate.domain.common.dto.Pageable;
import com.project.robotmate.domain.common.dto.Searchable;
import com.project.robotmate.domain.entity.admin.Admin;
import com.project.robotmate.domain.entity.gallery.Gallery;
import com.project.robotmate.domain.entity.gallery.repository.GalleryQueryRepository;
import com.project.robotmate.domain.entity.gallery.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultGalleryService implements GalleryService {

    private final GalleryRepository galleryRepository;
    private final GalleryQueryRepository galleryQueryRepository;
    private final FileService fileService;
    private final S3UploadProvider s3UploadProvider;
    private final ThumbnailProvider thumbnailProvider;
    @Override
    @Transactional
    public Long save(GalleryRequest request, Admin admin) {
        // 파일 업로드
        S3File s3File = s3UploadProvider.upload(request.getFile(), DirectoryType.GALLERY);
        Gallery gallery = Gallery.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .year(request.getYear())
                .type(request.getType())
                .admin(admin)
                .build();

        String thumbnail = getThumbnail(request.getFile());
        galleryRepository.save(gallery);
        TargetFileData target = new TargetFileData(gallery.getId(), TargetType.GALLERY);
        target.setThumbnailBucket(thumbnail);
        fileService.save(s3File, target);

        return gallery.getId();
    }

    @Override
    @Transactional
    public void update(Long id, GalleryUpdateRequest request) {
        Gallery gallery = galleryQueryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 갤러리입니다"));


        if (!request.getFile().isEmpty()) {
            if (!ObjectUtils.isEmpty(request.getFileId())) {
                fileService.deleteFile(request.getFileId());
            }

            String thumbnail = getThumbnail(request.getFile());

            S3File s3File = s3UploadProvider.upload(request.getFile(), DirectoryType.GALLERY);
            TargetFileData target = new TargetFileData(gallery.getId(), TargetType.GALLERY);
            target.setThumbnailBucket(thumbnail);
            fileService.save(s3File, target);
        }

        gallery.changeGallery(request.getTitle(), request.getContents());
        gallery.changeYear(request.getYear());
        gallery.changeType(request.getType());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Gallery gallery = galleryQueryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 갤러리입니다"));
        gallery.remove();
    }

    @Override
    public Page<List<GalleryResponse>> getGalleries(int page) {
        Long totalCount = galleryQueryRepository.countAll();
        Pageable pageable = getPageable(totalCount.intValue(), page);
        List<Gallery> result = galleryQueryRepository.findAll(pageable);
        return new Page<>(pageable, getGalleryResponses(result));
    }

    @Override
    public Page<List<GalleryResponse>> getGalleries(Searchable searchable) {
        Long totalCount = galleryQueryRepository.countAllBySearchable(searchable);
        Pageable pageable = getPageable(totalCount.intValue(), searchable.getPage());
        List<Gallery> result = galleryQueryRepository.findAllBySearchable(pageable, searchable);
        return new Page<>(pageable, getGalleryResponses(result));
    }


    @Override
    public GalleryResponse getGallery(Long id) {
        Gallery gallery = galleryQueryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 갤러리입니다"));

        FileData file = fileService.getFile(gallery.getId(), TargetType.GALLERY);

        return new GalleryResponse(gallery, file);
    }

    private List<GalleryResponse> getGalleryResponses(List<Gallery> result) {
        List<Long> targetIds = result.stream().map(Gallery::getId).collect(Collectors.toList());
        List<FileData> targetFiles = fileService.getTargetFile(targetIds, TargetType.GALLERY);

        return result.stream().map(r -> {
            FileData fileData = targetFiles.stream()
                    .filter(tf -> tf.getTargetId().equals(r.getId()))
                    .findFirst()
                    .orElseGet(null);
            return new GalleryResponse(r, fileData);
        }).collect(Collectors.toList());
    }

    /**
     * Pageable 객체 가져오기
     * */
    private Pageable getPageable(int totalCount, int page) {
        return new Pageable(totalCount, page);
    }

    private String getThumbnail(MultipartFile multipartFile) {
        String thumbnail = null;
        if (!multipartFile.isEmpty()) {
            try {
                if (thumbnailProvider.isVideoFormat(multipartFile)) {
                    File file = thumbnailProvider.extractThumbnail(multipartFile);
                    S3File thumbnailS3File = s3UploadProvider.upload(file, DirectoryType.GALLERY);
                    thumbnail = thumbnailS3File.getBucket();
                }

            } catch (Exception e) {e.printStackTrace();}
        }
        return thumbnail;
    }
}
