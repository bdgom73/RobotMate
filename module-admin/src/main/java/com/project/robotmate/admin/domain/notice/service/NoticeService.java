package com.project.robotmate.admin.domain.notice.service;

import com.project.robotmate.admin.domain.gallery.dto.response.GalleryResponse;
import com.project.robotmate.admin.domain.notice.dto.request.NoticeRequest;
import com.project.robotmate.admin.domain.notice.dto.response.NoticeResponse;
import com.project.robotmate.domain.common.dto.Page;
import com.project.robotmate.domain.entity.admin.Admin;

import java.util.List;

public interface NoticeService {
    void save(NoticeRequest request, Admin admin);

    Page<List<NoticeResponse>> getNotices(int page);

    NoticeResponse getNotice(Long id);
}
