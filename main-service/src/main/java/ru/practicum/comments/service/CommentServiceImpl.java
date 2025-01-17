package ru.practicum.comments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.comments.dto.CommentResponseDto;
import ru.practicum.comments.repository.CommentRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto createComment() {
        return null;
    }

    @Override
    public List<CommentResponseDto> getCommentByTargetId(Long id) {
        return List.of();
    }

    @Override
    public CommentResponseDto getCommentByCommentId(Long id) {
        return null;
    }

    @Override
    public List<CommentResponseDto> getUserComments(Long id) {
        return List.of();
    }

    @Override
    public CommentResponseDto updateCommentAsAuthor(CommentResponseDto comment) {
        return null;
    }

    @Override
    public CommentResponseDto updateCommentAsAdmin(CommentResponseDto comment) {
        return null;
    }

    @Override
    public void deleteCommentAsAuthor(Long id) {

    }

    @Override
    public void deleteCommentAsAdmin(Long id) {

    }
}
