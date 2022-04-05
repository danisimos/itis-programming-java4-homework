package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatRoom {
    public enum State {
        ACTIVE, DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "chatRooms")
    private List<Account> accounts;

    @OneToMany(mappedBy = "chatRoom")
    private List<Message> messages;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
