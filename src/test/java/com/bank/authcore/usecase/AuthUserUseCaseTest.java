package com.bank.authcore.usecase;

import com.bank.authcore.adapter.AccountAdapter;
import com.bank.authcore.repository.AuthAccountRepository;
import com.bank.authcore.repository.model.AccountModel;
import com.bank.authcore.usecase.dto.AccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthUserUseCaseTest {

    @Mock
    private AuthAccountRepository repository;

    @InjectMocks
    private AuthUserUseCaseImpl useCase;

    private AccountDto accountDto;
    private AccountModel accountModel;
    private String accountId = "669dc78967b04353acb31de2";
    private String userName = "Gabriel";

    @BeforeEach
    public void setUp() {
        accountDto = AccountDto
                .builder()
                .id(accountId)
                .build();

        accountModel = AccountModel
                .builder()
                .id(accountId)
                .userName(userName)
                .build();
    }

    @Test
    void createUserInDataBase() {
        AccountDto dto = new AccountDto();
        AccountModel model = new AccountModel();

        when(repository.save(model)).thenReturn(model);
        boolean response = useCase.create(dto);
        assertTrue(response);
    }

    @Test
    void tryCreateUserInDatabaseButReceivedError() {
        AccountDto dto = new AccountDto();
        AccountModel model = new AccountModel();

        when(repository.save(model)).thenThrow();

        boolean response = useCase.create(dto);
        assertFalse(response);
    }

    @Test
    void getAllLists() {
        List<AccountModel> modelList = new ArrayList<>();

        modelList.add(accountModel);

        when(repository.findAll()).thenReturn(modelList);

        List<AccountDto> response = useCase.get();

        AccountDto account = response.stream().findFirst().get();

        assertEquals(1, response.size());
        assertNotNull(response);
        assertEquals(userName, account.getUserName());
    }

    @Test
    void deleteUserInDataBase() {
        String userId = "123";

       doNothing().when(repository).deleteById(userId);

        boolean response = useCase.delete(userId);

        assertTrue(response);
    }

    @Test
    void tryDeleteUserInDatabaseButReceivedError() {
        String userId = "123";

        doThrow(NullPointerException.class).when(repository).deleteById(userId);

        boolean response = useCase.delete(userId);

        assertFalse(response);
    }

    @Test
    void updateUserInDataBase() {
        when(repository.findById(accountDto.getId())).thenReturn(Optional.of(accountModel));
        when(repository.save(any(AccountModel.class))).thenReturn(accountModel);

        AccountDto response = useCase.update(accountDto);

        assertNotNull(response);
        assertEquals(userName, response.getUserName());
    }

    @Test
    void tryUpdateUserInDatabaseButReceivedErrorInSave() {
        when(repository.findById(accountDto.getId())).thenReturn(Optional.of(accountModel));
        when(repository.save(any(AccountModel.class))).thenThrow(NullPointerException.class);

        assertThrowsExactly(NullPointerException.class, () -> {
            useCase.update(accountDto);
        });
    }

    @Test
    void tryUpdateUserInDatabaseButReceivedError() {
        when(repository.findById(accountDto.getId())).thenReturn(Optional.empty());
        AccountDto response = useCase.update(accountDto);
        assertNull(response);
    }

}
