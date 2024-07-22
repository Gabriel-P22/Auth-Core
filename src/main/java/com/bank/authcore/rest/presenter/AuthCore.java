package com.bank.authcore.rest.presenter;

import com.bank.authcore.rest.presenter.dto.AccountRequestDto;
import com.bank.authcore.rest.presenter.dto.AccountResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AuthCore {
    public ResponseEntity<Void> createAccount(@RequestBody AccountRequestDto payload);

    public ResponseEntity<List<AccountResponseDto>> getAccount();

    public ResponseEntity<Void> deleteAccount(@RequestParam(value = "id") String id);

    public ResponseEntity<AccountResponseDto> updateAccount(@RequestBody AccountRequestDto payload);
}

