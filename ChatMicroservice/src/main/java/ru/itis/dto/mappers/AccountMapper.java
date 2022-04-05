package ru.itis.dto.mappers;

import org.mapstruct.*;
import ru.itis.dto.AccountDto;
import ru.itis.models.ChatRoom;
import ru.itis.models.Message;
import ru.itis.models.Account;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class AccountMapper {
    public abstract Account toAccount(AccountDto accountDto);
    public abstract AccountDto toAccountDto(Account account);
    public abstract List<AccountDto> toAccountDtoList(List<Account> accounts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateAccountFromDto(AccountDto accountDto, @MappingTarget Account account);

    @BeforeMapping
    protected void setChatRoomsIdAndMessagesId(Account account, @MappingTarget AccountDto accountDto) {
        if(account.getChatRooms() != null) {
            accountDto.setChatRoomsId(account.getChatRooms()
                    .stream()
                    .map(ChatRoom::getId)
                    .collect(Collectors.toList()));
        }

        if(account.getMessages() != null) {
            accountDto.setMessagesId(account.getMessages()
                    .stream()
                    .map(Message::getId)
                    .collect(Collectors.toList()));
        }
    }
}
