package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Account {
    public enum State {
        ACTIVE, DELETED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String email;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private Long age;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "chat_room_id", referencedColumnName = "id"))
    private List<ChatRoom> chatRooms;

    @OneToMany(mappedBy = "account")
    private List<Message> messages;

    @Enumerated(value = EnumType.STRING)
    private State state;
}
