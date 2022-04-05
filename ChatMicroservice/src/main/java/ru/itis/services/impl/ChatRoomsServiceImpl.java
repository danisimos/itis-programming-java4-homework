package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.ChatRoomDto;
import ru.itis.dto.mappers.ChatRoomMapper;
import ru.itis.exceptions.AccountNotFoundException;
import ru.itis.exceptions.ChatRoomNotFoundException;
import ru.itis.models.Account;
import ru.itis.models.ChatRoom;
import ru.itis.repositories.AccountsRepository;
import ru.itis.repositories.ChatRoomsRepository;
import ru.itis.services.ChatRoomsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomsServiceImpl implements ChatRoomsService {
    private final ChatRoomsRepository chatRoomsRepository;
    private final AccountsRepository accountsRepository;
    private final ChatRoomMapper chatRoomMapper;

    @Override
    public List<ChatRoomDto> getChatRooms() {
        return chatRoomMapper.toChatRoomDtoList(chatRoomsRepository.findAll());
    }

    @Override
    public ChatRoomDto addChatRoom(ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = chatRoomMapper.toChatRoom(chatRoomDto);
        chatRoom.setState(ChatRoom.State.ACTIVE);

        return chatRoomMapper.toChatRoomDto(chatRoomsRepository.save(chatRoom));
    }

    @Override
    public ChatRoomDto updateChatRoom(Long chatRoomId, ChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = chatRoomsRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        chatRoomMapper.updateChatRoomFromDto(chatRoomDto, chatRoom);

        return chatRoomMapper.toChatRoomDto(chatRoomsRepository.save(chatRoom));
    }

    @Override
    public ChatRoomDto deleteChatRoom(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomsRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        chatRoom.setState(ChatRoom.State.DELETED);

        return chatRoomMapper.toChatRoomDto(chatRoomsRepository.save(chatRoom));
    }

    @Override
    public List<ChatRoomDto> getChatRoomsByAccount(Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);

        return chatRoomMapper.toChatRoomDtoList(chatRoomsRepository.findByAccountsContains(account));
    }

    @Override
    public ChatRoomDto addAccountToChatRoom(Long chatRoomId, Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        ChatRoom chatRoom = chatRoomsRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        chatRoom.getAccounts().add(account);
        account.getChatRooms().add(chatRoom);

        accountsRepository.save(account);

        return chatRoomMapper.toChatRoomDto(chatRoomsRepository.save(chatRoom));
    }

    @Override
    public ChatRoomDto deleteAccountFromChatRoom(Long chatRoomId, Long accountId) {
        Account account = accountsRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);

        ChatRoom chatRoom = account.getChatRooms().stream()
                .filter(chatRoom1 -> chatRoom1.getId() == chatRoomId)
                .findAny()
                .orElseThrow(ChatRoomNotFoundException::new);

        account.getChatRooms().remove(chatRoom);
        accountsRepository.save(account);

        chatRoom.getAccounts().remove(account);
        return chatRoomMapper.toChatRoomDto(chatRoomsRepository.save(chatRoom));
    }
}
