package com.bank.authcore.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("AuthAccounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    String id;
    String userName;
    String email;
    String password;
}