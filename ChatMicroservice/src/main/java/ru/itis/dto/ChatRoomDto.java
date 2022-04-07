package ru.itis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Account;
import ru.itis.models.Message;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDto {
    public enum State {
        ACTIVE, DELETED
    };

    @NotNull(message = "CHAT_ROOM_ID_NULL")
    private Long id;
    @NotBlank(message = "CHAT_ROOM_NAME_EMPTY")
    private String name;
    private List<Long> accountsId;
    private List<Long> messagesId;
    private State state;
}
