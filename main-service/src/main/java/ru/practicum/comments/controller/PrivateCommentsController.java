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
@RequestMapping("/user/{userId}/{targetId}/comments")
@Slf4j
public class PrivateCommentsController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentResponseDto> getAllUserComments(@PathVariable Long userId) {
        return null;
    }

    @PostMapping
    public CommentResponseDto createComment(@PathVariable Long targetId,
                                            @PathVariable Long userId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        log.info("Calling the POST request to /comments endpoint");
        return null;
    }

    @PatchMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long targetId,
                                            @PathVariable Long commentId,
                                            @PathVariable Long userId,
                                            @RequestBody CommentRequestDto commentRequestDto) {
        return null;
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long targetId,
                              @PathVariable Long commentId,
                              @PathVariable Long userId) {

    }
}
