package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.AccountDto;
import ru.itis.dto.ChatRoomDto;
import ru.itis.services.AccountsService;
import ru.itis.services.ChatRoomsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class ChatRoomsController {
    private final ChatRoomsService chatRoomsService;

    @GetMapping("chatRooms")
    public ResponseEntity<List<ChatRoomDto>> getChatRooms() {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(chatRoomsService.getChatRooms());
    }

    @PostMapping("chatRooms")
    public ResponseEntity<ChatRoomDto> addChatRoom(@RequestBody ChatRoomDto chatRoomDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(chatRoomsService.addChatRoom(chatRoomDto));
    }

    @PutMapping("chatRooms/{chat-room-id}")
    public ResponseEntity<ChatRoomDto> updateChatRoom(@PathVariable("chat-room-id") Long chatRoomId,
                                                    @RequestBody ChatRoomDto chatRoomDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(chatRoomsService.updateChatRoom(chatRoomId, chatRoomDto));
    }

    @DeleteMapping("chatRooms/{chat-room-id}")
    public ResponseEntity<ChatRoomDto> deleteChatRoom(@PathVariable("chat-room-id") Long chatRoomId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(chatRoomsService.deleteChatRoom(chatRoomId));
    }

    @GetMapping("accounts/{account-id}/chatRooms")
    public ResponseEntity<List<ChatRoomDto>> getChatRoomsByAccount(@PathVariable("account-id") Long accountId) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(chatRoomsService.getChatRoomsByAccount(accountId));
    }

    @PostMapping("chatRooms/{chat-room-id}/addAccount/{account-id}")
    public ResponseEntity<ChatRoomDto> addAccountToChatRoom(@PathVariable("chat-room-id") Long chatRoomId,
                                                            @PathVariable("account-id") Long accountId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(chatRoomsService.addAccountToChatRoom(chatRoomId, accountId));
    }

    @PostMapping("chatRooms/{chat-room-id}/deleteAccount/{account-id}")
    public ResponseEntity<ChatRoomDto> deleteAccountFromChatRoom(@PathVariable("chat-room-id") Long chatRoomId,
                                                            @PathVariable("account-id") Long accountId) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(chatRoomsService.deleteAccountFromChatRoom(chatRoomId, accountId));
    }
}
