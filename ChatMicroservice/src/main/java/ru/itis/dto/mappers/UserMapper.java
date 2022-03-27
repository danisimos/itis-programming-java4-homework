package ru.itis.dto.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.dto.UserDto;
import ru.itis.models.ChatRoom;
import ru.itis.models.Message;
import ru.itis.models.User;

import java.util.stream.Collectors;

@Mapper
public abstract class UserMapper {
    public abstract User toUser(UserDto userDto);
    public abstract UserDto toUserDto(User user);

    @BeforeMapping
    protected void setChatRoomsIdAndMessagesId(User user, @MappingTarget UserDto userDto) {
        userDto.setChatRoomsId(user.getChatRooms()
                .stream()
                .map(ChatRoom::getId)
                .collect(Collectors.toList()));

        userDto.setMessagesId(user.getMessages()
                .stream()
                .map(Message::getId)
                .collect(Collectors.toList()));
    }
}
