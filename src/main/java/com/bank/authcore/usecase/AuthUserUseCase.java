package com.bank.authcore.usecase;

import com.bank.authcore.usecase.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AuthUserUseCase {
    public boolean create(AccountDto payload);

    public List<AccountDto> get();

    public boolean delete(String id);

    public AccountDto update(AccountDto payload);
}
