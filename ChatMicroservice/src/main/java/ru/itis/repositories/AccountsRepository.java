package ru.itis.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Account;

import java.util.List;

public interface AccountsRepository extends JpaRepository<Account, Long> {
}
