package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Account;
import ru.itis.models.ChatRoom;
import ru.itis.validation.annotations.NotContainsWords;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    public enum State {
        PUBLISHED, DELETED
    };

    @NotNull(message = "MESSAGE_ID_NULL")
    private Long id;
    @NotBlank(message = "MESSAGE_BODY_EMPTY")
    @NotContainsWords(words = {"example1", "example2"}, message = "text cannot contains forbidden words: {words}")
    private String body;
    private Long chatRoomId;
    private Long accountId;
    private State state;
}
