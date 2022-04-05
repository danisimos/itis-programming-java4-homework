package ru.itis.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Message;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoom_Id(Long chatRoomId);
    List<Message> findByAccount_Id(Long accountId);
}
