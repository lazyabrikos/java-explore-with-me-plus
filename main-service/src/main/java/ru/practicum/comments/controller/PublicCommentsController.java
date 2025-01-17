package ru.practicum.comments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.comments.dto.CommentResponseDto;
import ru.practicum.comments.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@Slf4j
public class PublicCommentsController {

    private final CommentService commentService;

    @GetMapping("/{targetId}")
    public CommentResponseDto getCommentsByTargetId(@PathVariable Long targetId) {
        return null;
    }

    @GetMapping("/{commentId}")
    public CommentResponseDto getCommentsByCommentId(@PathVariable Long commentId) {
        return null;
    }
}
