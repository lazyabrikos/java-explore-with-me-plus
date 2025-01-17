package ru.practicum.comments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.comments.dto.CommentRequestDto;
import ru.practicum.comments.dto.CommentResponseDto;
import ru.practicum.comments.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/{userId}/{eventId}/comments")
@Slf4j
public class PrivateCommentsController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentResponseDto> getAllUserComments(@PathVariable Long userId) {
        return commentService.getUserComments(userId);
    }

    @PostMapping
    public CommentResponseDto createComment(@PathVariable Long eventId,
                                            @PathVariable Long userId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        log.info("Calling the POST request to /comments endpoint");
        return commentService.createComment(eventId, userId, commentRequestDto);
    }

    @PatchMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long eventId,
                                            @PathVariable Long commentId,
                                            @PathVariable Long userId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateCommentAsAuthor(eventId, commentId, userId, commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long eventId,
                              @PathVariable Long commentId,
                              @PathVariable Long userId) {
        commentService.deleteCommentAsAuthor(eventId, userId, commentId);
    }
}
