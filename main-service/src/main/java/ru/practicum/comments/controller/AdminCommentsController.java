package ru.practicum.comments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.dto.CommentRequestDto;
import ru.practicum.comments.dto.CommentResponseDto;
import ru.practicum.comments.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/{eventId}/comments")
@Slf4j
public class AdminCommentsController {
    private final CommentService commentService;

    @PatchMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long eventId,
                                            @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateCommentAsAdmin(eventId, commentId, commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long eventId, @PathVariable Long commentId) {
        commentService.deleteCommentAsAdmin(eventId, commentId);
    }
}
