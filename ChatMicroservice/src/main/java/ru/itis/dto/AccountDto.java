package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.ChatRoom;
import ru.itis.models.Message;

import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    public enum State {
        ACTIVE, DELETED
    };

    @NotNull(message = "ACCOUNT_ID_NULL")
    private Long id;
    @NotBlank(message = "ACCOUNT_LOGIN_EMPTY")
    private String login;
    @Email(message = "ACCOUNT_WRONG_EMAIL")
    private String email;
    @NotBlank(message = "ACCOUNT_FIRSTNAME_EMPTY")
    private String firstName;
    @NotBlank(message = "ACCOUNT_LASTNAME_EMPTY")
    private String lastName;
    @Min(value = 18, message = "ACCOUNT_WRONG_AGE")
    @Max(value = 150, message = "ACCOUNT_WRONG_AGE")
    private Long age;
    private List<Long> chatRoomsId;
    private List<Long> messagesId;
    private State state;
}
