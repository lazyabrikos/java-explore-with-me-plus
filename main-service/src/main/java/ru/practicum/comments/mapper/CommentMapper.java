package ru.practicum.comments.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.practicum.comments.dto.CommentRequestDto;
import ru.practicum.comments.dto.CommentResponseDto;
import ru.practicum.comments.model.Comment;
import ru.practicum.users.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "authorName", source = "comment.author.name")
    CommentResponseDto toDto(Comment comment);

    @Mapping(target = "author", source = "author")
    @Mapping(target = "eventId", source = "eventId")
    @Mapping(target = "createdAt", source = "createdAt")
    Comment toComment(CommentRequestDto commentRequestDto, @MappingTarget User author,
                      @MappingTarget Long eventId, @MappingTarget LocalDateTime createdAt);

    List<CommentResponseDto> toDtoList(List<Comment> comments);
}
