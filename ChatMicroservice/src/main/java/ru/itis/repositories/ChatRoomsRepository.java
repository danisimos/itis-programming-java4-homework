package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Account;
import ru.itis.models.ChatRoom;

import java.util.List;

public interface ChatRoomsRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByAccountsContains(Account account);
}
