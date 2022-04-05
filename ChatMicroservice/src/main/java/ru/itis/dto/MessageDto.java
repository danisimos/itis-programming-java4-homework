package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Account;
import ru.itis.models.ChatRoom;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    public enum State {
        PUBLISHED, DELETED
    };

    private Long id;
    private String body;
    private Long chatRoomId;
    private Long accountId;
    private State state;
}
