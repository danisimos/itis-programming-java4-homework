package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.ChatRoom;
import ru.itis.models.Message;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    public enum State {
        ACTIVE, DELETED
    };

    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private Long age;
    private List<Long> chatRoomsId;
    private List<Long> messagesId;
    private State state;
}
