package cloud.aws.s3;

import cloud.aws.s3.config.AwsS3BucketProvider;
import cloud.aws.s3.model.S3File;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.robotmate.core.types.DirectoryType;
import org.springframework.web.multipart.MultipartFile;

import static cloud.aws.s3.util.AwsUtil.createFilename;

public class DefaultS3UploadProvider implements S3UploadProvider {

    private final AmazonS3Client amazonS3Client;
    private final AwsS3BucketProvider provider;

    public DefaultS3UploadProvider(AmazonS3Client amazonS3Client, AwsS3BucketProvider provider) {
        this.amazonS3Client = amazonS3Client;
        this.provider = provider;
    }

    @Override
    public S3File upload(MultipartFile file, DirectoryType type) {
        checkEmptyFile(file);
        String s3FileName = createFilename(type.getValue());

        try {
            ObjectMetadata objMeta = new ObjectMetadata();
            objMeta.setContentLength(file.getInputStream().available());
            objMeta.setContentType(file.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    provider.getBucket(),
                    s3FileName,
                    file.getInputStream(),
                    objMeta
            );
            amazonS3Client.putObject(putObjectRequest);

            return getS3File(1, file, s3FileName);

        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new IllegalArgumentException("파일 업로드에 실패했습니다.");
        }
    }

    private static S3File getS3File(int index, MultipartFile file, String bucket) {
        S3File s3File = new S3File();
        s3File.setBucket(bucket);
        s3File.setIndex(index);
        s3File.setFormat(file.getContentType());
        s3File.setOriginalFileName(file.getOriginalFilename());
        s3File.setSize(file.getSize());

        return s3File;
    }

    private void checkEmptyFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 존재하지 않습니다.");
        }
    }
}