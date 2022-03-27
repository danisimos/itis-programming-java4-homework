package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.ChatRoom;

public interface ChatRoomsRepository extends JpaRepository<ChatRoom, Long> {
}
