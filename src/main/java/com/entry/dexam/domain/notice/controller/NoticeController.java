package com.entry.dexam.domain.notice.controller;

import com.entry.dexam.domain.notice.dto.request.NoticeCreateRequest;
import com.entry.dexam.domain.notice.dto.request.NoticeUpdateRequest;
import com.entry.dexam.domain.notice.dto.response.*;
import com.entry.dexam.domain.notice.enums.Target;
import com.entry.dexam.domain.notice.service.NoticeService;
import com.entry.dexam.global.annotations.CurrentUserId.CurrentUserId;
import com.entry.dexam.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/admin/notices")
    public ApiResponse<NoticeCreateResponse> createNotice(
            @RequestBody @Valid NoticeCreateRequest request,
            @Parameter(hidden = true) @CurrentUserId Long userId
            ) {

        NoticeCreateResponse response = noticeService.createNotice(userId, request);
        return ApiResponse.ok(response);
    }

    @DeleteMapping("/admin/notices/{noticeId}")
    public ApiResponse<NoticeDeleteResponse> deleteNotice(
            @PathVariable Long noticeId,
            @Parameter(hidden = true) @CurrentUserId Long userId
            ) {

        NoticeDeleteResponse response = noticeService.deleteNotice(userId, noticeId);
        return ApiResponse.ok(response);
    }

    @PutMapping("/admin/notices/{noticeId}")
    public ApiResponse<NoticeUpdateResponse> updateNotice(
            @PathVariable Long noticeId,
            @RequestBody @Valid NoticeUpdateRequest request,
            @Parameter(hidden = true) @CurrentUserId Long userId
    ){

        NoticeUpdateResponse response = noticeService.updateNotice(userId, noticeId, request);
        return ApiResponse.ok(response);
    }

    @GetMapping("/notices")
    public ApiResponse<NoticeListResponse> getNoticeList(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @RequestParam Target target,
            @RequestParam(required = false) String keyword
    ){

        NoticeListResponse response = noticeService.readNoticeList(userId, target, keyword);
        return ApiResponse.ok(response);
    }

    @GetMapping("/notices/{noticeId}")
    public ApiResponse<NoticeDetailResponse> getNoticeDetail(
            @Parameter(hidden = true) @CurrentUserId Long userId,
            @PathVariable Long noticeId
    ){

        NoticeDetailResponse response = noticeService.readNoticeDetail(userId, noticeId);
        return ApiResponse.ok(response);
    }

}
