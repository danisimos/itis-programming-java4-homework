package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.ChatRoomDto;
import ru.itis.dto.MessageDto;
import ru.itis.dto.UserDto;
import ru.itis.dto.mappers.ChatRoomMapper;
import ru.itis.dto.mappers.MessageMapper;
import ru.itis.dto.mappers.UserMapper;
import ru.itis.models.ChatRoom;
import ru.itis.models.Message;
import ru.itis.models.User;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UsersController {
    @GetMapping("users")
    public String getTest() {
        User user = User.builder()
                .email("asdff")
                .lastName("asdf")
                .firstName("egrgg")
                .age(33L)
                .id(1L)
                .login("ag")
                .build();

        ChatRoom chatRoom = ChatRoom.builder()
                .id(1L)
                .users(Collections.singletonList(user))
                .name("asddas")
                .build();

        Message message = Message.builder()
                .user(user)
                .chatRoom(chatRoom)
                .id(3L)
                .build();

        user.setChatRooms(Collections.singletonList(chatRoom));
        user.setMessages(Collections.singletonList(message));
        chatRoom.setMessages(Collections.singletonList(message));

        UserDto userDto = Mappers.getMapper(UserMapper.class).toUserDto(user);
        ChatRoomDto chatRoomDto = Mappers.getMapper(ChatRoomMapper.class).toChatRoomDto(chatRoom);
        MessageDto messageDto = Mappers.getMapper(MessageMapper.class).toMessageDto(message);

        return userDto + "     " + chatRoomDto + "     " + messageDto;
    }
}
