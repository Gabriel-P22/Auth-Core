package com.bank.authcore.rest.presenter;

import com.bank.authcore.adapter.AccountAdapter;
import com.bank.authcore.rest.presenter.dto.AccountRequestDto;
import com.bank.authcore.rest.presenter.dto.AccountResponseDto;
import com.bank.authcore.usecase.AuthUserUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/auth")
public class AuthCoreImpl implements AuthCore {

    @Autowired
    private AuthUserUseCase useCase;

    @Override
    @PostMapping("/register")
    public ResponseEntity<Void> createAccount(AccountRequestDto payload) {
        useCase.create(AccountAdapter.INSTANCE.convert(payload));
        return ResponseEntity.accepted().build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAccount() {
        return ResponseEntity.ok((AccountAdapter.INSTANCE.convertToList(useCase.get())));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(String id) {
        boolean condition = useCase.delete(id);

        if (condition) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @PutMapping
    public ResponseEntity<AccountResponseDto> updateAccount(AccountRequestDto payload) {
        AccountResponseDto account = AccountAdapter.INSTANCE.convert(
                useCase.update(
                        AccountAdapter.INSTANCE.convert(payload)
                )
        );

        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
