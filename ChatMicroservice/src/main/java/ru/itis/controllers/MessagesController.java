package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.AccountDto;
import ru.itis.dto.MessageDto;
import ru.itis.services.MessagesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessagesController {
    private final MessagesService messagesService;

    @GetMapping("chatRooms/{chat-room-id}/messages")
    public ResponseEntity<List<MessageDto>> getMessagesByChatRoom(@PathVariable("chat-room-id") Long chatRoomId) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(messagesService.getMessagesByChatRoom(chatRoomId));
    }

    @PostMapping("chatRooms/{chat-room-id}/messages")
    public ResponseEntity<MessageDto> addMessageToChatRoom(@PathVariable("chat-room-id") Long chatRoomId,
                                                           @RequestBody MessageDto messageDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(messagesService.addMessageToChatRoom(chatRoomId, messageDto));
    }

    @DeleteMapping("chatRooms/{chat-room-id}/messages")
    public ResponseEntity<MessageDto> deleteMessageFromChatRoom(@PathVariable("chat-room-id") Long chatRoomId,
                                                                @RequestParam("messageId") Long messageId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(messagesService.deleteMessageFromChatRoom(chatRoomId, messageId));
    }

    @GetMapping("accounts/{account-id}/messages")
    public ResponseEntity<List<MessageDto>> getMessagesByAccount(@PathVariable("account-id") Long accountId) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(messagesService.getMessagesByAccount(accountId));
    }
}
