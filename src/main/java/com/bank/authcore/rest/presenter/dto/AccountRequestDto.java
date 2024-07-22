package com.bank.authcore.rest.presenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    String id;
    String userName;
    String email;
    String password;
}
