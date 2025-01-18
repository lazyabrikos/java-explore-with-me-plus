package ru.practicum.comments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        log.info("GET all comments for user with id: {}", userId);
        return commentService.getUserComments(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(@PathVariable Long eventId,
                                            @PathVariable Long userId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        log.info("POST request with body: {}", commentRequestDto);
        return commentService.createComment(eventId, userId, commentRequestDto);
    }

    @PatchMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long eventId,
                                            @PathVariable Long commentId,
                                            @PathVariable Long userId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        log.info("PATCH request with body: {}", commentRequestDto);
        return commentService.updateCommentAsAuthor(eventId, commentId, userId, commentRequestDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long eventId,
                              @PathVariable Long commentId,
                              @PathVariable Long userId) {
        log.info("DELETE request with commentId: {}", commentId);
        commentService.deleteCommentAsAuthor(eventId, userId, commentId);
    }
}
