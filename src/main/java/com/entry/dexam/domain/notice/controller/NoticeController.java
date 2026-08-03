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
