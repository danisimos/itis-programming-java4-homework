package ru.itis.dto.mappers;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;

@Mapper
public abstract class MessageMapper {
    public abstract Message toMessage(MessageDto messageDto);
    public abstract MessageDto toMessageDto(Message message);

    @BeforeMapping
    protected void setChatRoomIdAndUserId(Message message, @MappingTarget MessageDto messageDto) {
        messageDto.setChatRoomId(message.getChatRoom().getId());
        messageDto.setUserId(message.getUser().getId());
    }
}
