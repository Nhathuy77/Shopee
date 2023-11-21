package com.vti.myshopee.service;

import com.vti.myshopee.model.dto.AccountCreateDTO;
import com.vti.myshopee.model.dto.AccountUpdateDTO;
import com.vti.myshopee.model.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    List<Account> getAll();
    Account create(AccountCreateDTO dto);
    Account update(AccountUpdateDTO dto);
    Account getById(int id);
    Account delete(int id);
}
