package ru.practicum.comments.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.comments.dto.CommentResponseDto;
import ru.practicum.comments.model.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "authorName", source = "comment.author.name")
    CommentResponseDto toDto(Comment comment);

    List<CommentResponseDto> toDtoList(List<Comment> comments);
}
