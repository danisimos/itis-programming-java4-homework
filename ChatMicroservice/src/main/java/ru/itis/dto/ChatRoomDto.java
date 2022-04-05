package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Account;
import ru.itis.models.Message;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDto {
    public enum State {
        ACTIVE, DELETED
    };

    private Long id;
    private String name;
    private List<Long> accountsId;
    private List<Long> messagesId;
    private State state;
}
