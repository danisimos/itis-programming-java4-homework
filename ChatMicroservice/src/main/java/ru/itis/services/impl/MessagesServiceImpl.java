package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.MessageDto;
import ru.itis.dto.mappers.MessageMapper;
import ru.itis.exceptions.AccountNotFoundException;
import ru.itis.exceptions.ChatRoomNotFoundException;
import ru.itis.exceptions.MessageNotFoundException;
import ru.itis.models.Account;
import ru.itis.models.ChatRoom;
import ru.itis.models.Message;
import ru.itis.repositories.AccountsRepository;
import ru.itis.repositories.ChatRoomsRepository;
import ru.itis.repositories.MessagesRepository;
import ru.itis.services.MessagesService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesServiceImpl implements MessagesService {
    private final MessagesRepository messagesRepository;
    private final ChatRoomsRepository chatRoomsRepository;
    private final AccountsRepository accountRepository;
    private final MessageMapper messageMapper;

    @Override
    public List<MessageDto> getMessagesByChatRoom(Long chatRoomId) {
        return messageMapper.toMessageDtoList(messagesRepository.findByChatRoom_Id(chatRoomId));
    }

    @Override
    public MessageDto addMessageToChatRoom(Long chatRoomId, MessageDto messageDto) {
        Message message = messageMapper.toMessage(messageDto);

        Account account = accountRepository.findById(messageDto.getAccountId()).orElseThrow(AccountNotFoundException::new);
        ChatRoom chatRoom = chatRoomsRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        message.setAccount(account);
        message.setState(Message.State.PUBLISHED);
        message.setChatRoom(chatRoom);

        account.getMessages().add(message);
        chatRoom.getMessages().add(message);

        accountRepository.save(account);
        chatRoomsRepository.save(chatRoom);

        return messageMapper.toMessageDto(messagesRepository.save(message));
    }

    @Override
    public MessageDto deleteMessageFromChatRoom(Long chatRoomId, Long messageId) {
        Message message = messagesRepository.findById(messageId).orElseThrow(MessageNotFoundException::new);

        message.setState(Message.State.DELETED);

        return messageMapper.toMessageDto(messagesRepository.save(message));
    }

    @Override
    public List<MessageDto> getMessagesByAccount(Long accountId) {
        return messageMapper.toMessageDtoList(messagesRepository.findByAccount_Id(accountId));
    }
}
