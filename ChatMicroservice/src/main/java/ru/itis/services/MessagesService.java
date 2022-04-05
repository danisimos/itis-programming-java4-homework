package ru.itis.services;

import ru.itis.dto.MessageDto;

import java.util.List;

public interface MessagesService {
    List<MessageDto> getMessagesByChatRoom(Long chatRoomId);

    MessageDto addMessageToChatRoom(Long chatRoomId, MessageDto messageDto);

    MessageDto deleteMessageFromChatRoom(Long chatRoomId, Long messageId);

    List<MessageDto> getMessagesByAccount(Long accountId);
}
