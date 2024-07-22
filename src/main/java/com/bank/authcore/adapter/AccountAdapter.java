package com.bank.authcore.adapter;

import com.bank.authcore.repository.model.AccountModel;
import com.bank.authcore.rest.presenter.dto.AccountRequestDto;
import com.bank.authcore.rest.presenter.dto.AccountResponseDto;
import com.bank.authcore.usecase.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountAdapter {
    AccountAdapter INSTANCE = Mappers.getMapper(AccountAdapter.class);

    public AccountDto convert(AccountRequestDto dto);
    public AccountResponseDto convert(AccountDto dto);
    public AccountModel convertToModel(AccountDto dto);
    public AccountDto convert(AccountModel dto);
    public List<AccountResponseDto> convertToList(List<AccountDto> dto);
    public List<AccountDto> convertToModelList(List<AccountModel> dto);
}
