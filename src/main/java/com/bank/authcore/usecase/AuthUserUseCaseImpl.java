package com.bank.authcore.usecase;

import com.bank.authcore.adapter.AccountAdapter;
import com.bank.authcore.repository.AuthAccountRepository;
import com.bank.authcore.repository.model.AccountModel;
import com.bank.authcore.usecase.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthUserUseCaseImpl implements AuthUserUseCase {


    @Autowired private AuthAccountRepository repository;

    @Override
    public boolean create(AccountDto payload) {
        try {
            repository.save(AccountAdapter.INSTANCE.convertToModel(payload));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<AccountDto> get() {
        return AccountAdapter.INSTANCE.convertToModelList(
                repository.findAll()
        );
    }

    @Override
    public boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public AccountDto update(AccountDto payload) {
        Optional<AccountModel> hasAccount = repository.findById(payload.getId());

        if (hasAccount.isPresent()) {
            return AccountAdapter.INSTANCE.convert(
                    repository.save(
                            AccountAdapter.INSTANCE.convertToModel(payload)
                    )
            );

        } else {
            return null;
        }
    }
}
