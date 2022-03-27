package ru.itis.dto.mappers;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.dto.ChatRoomDto;
import ru.itis.models.ChatRoom;
import ru.itis.models.Message;
import ru.itis.models.User;

import java.util.stream.Collectors;

@Mapper
public abstract class ChatRoomMapper {
    public abstract ChatRoomDto toChatRoomDto(ChatRoom chatRoom);
    public abstract ChatRoom toChatRoom(ChatRoomDto chatRoomDto);

    @BeforeMapping
    protected void setUsersIdAndMessagesId(ChatRoom chatRoom, @MappingTarget ChatRoomDto chatRoomDto) {
        chatRoomDto.setMessagesId(chatRoom.getMessages()
                .stream()
                .map(Message::getId)
                .collect(Collectors.toList()));

        chatRoomDto.setUsersId(chatRoom.getUsers()
                .stream()
                .map(User::getId)
                .collect(Collectors.toList()));
    }
}
