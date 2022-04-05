package ru.itis.dto.mappers;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;

import java.util.List;

@Mapper
public abstract class MessageMapper {
    public abstract Message toMessage(MessageDto messageDto);
    public abstract MessageDto toMessageDto(Message message);
    public abstract List<MessageDto> toMessageDtoList(List<Message> messages);

    @BeforeMapping
    protected void setChatRoomIdAndUserId(Message message, @MappingTarget MessageDto messageDto) {
        messageDto.setChatRoomId(message.getChatRoom().getId());
        messageDto.setAccountId(message.getAccount().getId());

    }
}
