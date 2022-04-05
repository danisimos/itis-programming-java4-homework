package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {
    public enum State {
        PUBLISHED, DELETED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    @ManyToOne
    @JoinColumn(name = "chat_room")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
