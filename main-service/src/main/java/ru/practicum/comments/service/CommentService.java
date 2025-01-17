package ru.practicum.comments.service;

import ru.practicum.comments.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto createComment();

    List<CommentResponseDto> getCommentByTargetId(Long id);

    CommentResponseDto getCommentByCommentId(Long id);

    List<CommentResponseDto> getUserComments(Long id);

    CommentResponseDto updateCommentAsAuthor(CommentResponseDto comment);

    CommentResponseDto updateCommentAsAdmin(CommentResponseDto comment);

    void deleteCommentAsAuthor(Long id);

    void deleteCommentAsAdmin(Long id);
}
