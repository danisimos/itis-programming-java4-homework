package ru.itis.services;

import ru.itis.dto.ChatRoomDto;

import java.util.List;

public interface ChatRoomsService {
    List<ChatRoomDto> getChatRooms();

    ChatRoomDto addChatRoom(ChatRoomDto chatRoomDto);

    ChatRoomDto updateChatRoom(Long chatRoomId, ChatRoomDto chatRoomDto);

    ChatRoomDto deleteChatRoom(Long chatRoomId);

    List<ChatRoomDto> getChatRoomsByAccount(Long accountId);

    ChatRoomDto addAccountToChatRoom(Long chatRoomId, Long accountId);

    ChatRoomDto deleteAccountFromChatRoom(Long chatRoomId, Long accountId);
}
