package com.vti.myshopee.controller;


import com.vti.myshopee.model.dto.AccountCreateDTO;
import com.vti.myshopee.model.dto.AccountUpdateDTO;
import com.vti.myshopee.model.entity.Account;
import com.vti.myshopee.service.iml.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/account")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @GetMapping("/get-all")
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")

    @PostMapping("/create")
    public Account create(@RequestBody AccountCreateDTO dto) {
        return accountService.create(dto);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")

    @PutMapping("/update")
    public Account update(@RequestBody AccountUpdateDTO dto) {
        return accountService.update(dto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")

    @GetMapping("/{id}")
    public Account getById(@PathVariable int id) {
        return accountService.getById(id);
    }



    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        accountService.delete(id);
    }

}
