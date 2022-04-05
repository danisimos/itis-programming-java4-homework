package ru.itis.dto.mappers;

import org.mapstruct.*;
import ru.itis.dto.AccountDto;
import ru.itis.dto.ChatRoomDto;
import ru.itis.models.Account;
import ru.itis.models.ChatRoom;
import ru.itis.models.Message;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class ChatRoomMapper {
    public abstract ChatRoomDto toChatRoomDto(ChatRoom chatRoom);
    public abstract ChatRoom toChatRoom(ChatRoomDto chatRoomDto);
    public abstract List<ChatRoomDto> toChatRoomDtoList(List<ChatRoom> chatRooms);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateChatRoomFromDto(ChatRoomDto chatRoomDto, @MappingTarget ChatRoom chatRoom);

    @BeforeMapping
    protected void setAccountsIdAndMessagesId(ChatRoom chatRoom, @MappingTarget ChatRoomDto chatRoomDto) {
        if(chatRoom.getMessages() != null) {
            chatRoomDto.setMessagesId(chatRoom.getMessages()
                    .stream()
                    .map(Message::getId)
                    .collect(Collectors.toList()));
        }
        if(chatRoom.getAccounts() != null) {
            chatRoomDto.setAccountsId(chatRoom.getAccounts()
                    .stream()
                    .map(Account::getId)
                    .collect(Collectors.toList()));
        }
    }
}
