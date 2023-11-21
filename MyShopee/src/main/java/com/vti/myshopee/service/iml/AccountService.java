package com.vti.myshopee.service.iml;

import com.vti.myshopee.model.dto.AccountCreateDTO;
import com.vti.myshopee.model.dto.AccountUpdateDTO;
import com.vti.myshopee.model.entity.Account;
import com.vti.myshopee.model.entity.Role;
import com.vti.myshopee.repository.AccountRepository;
import com.vti.myshopee.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@AllArgsConstructor
public class AccountService implements IAccountService {
    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(AccountCreateDTO dto) {
        Account account = new Account();
        BeanUtils.copyProperties(dto, account);
        account.setRole(Role.USER);
        return accountRepository.save(account);
    }

    @Override
    public Account update(AccountUpdateDTO dto) {
        Optional<Account> optionalAccount = accountRepository.findById(dto.getId());
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            BeanUtils.copyProperties(dto, account);
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public Account getById(int id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()){
            return optionalAccount.get();
        }
        return null;
    }

    @Override
    public Account delete(int id) {
        accountRepository.deleteById(id);
        return null;
    }

    /**
     * Dùng để Spring security kiểm tra username có tồn tại trong hệ thống hay ko.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()){
            throw new UsernameNotFoundException("Username không tồn tại");
        }
        Account account = accountOptional.get();
        // Nếu Account có tồn tại -> tạo ra đối tượng UserDetails từ Account
        /**
         * account.getUsername(): username
         * account.getPassword(): password đã được mã hoá.
         * authorities: danh sách quyền của user
         */
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(account.getRole());
        return new User(account.getUsername(), account.getPassword(), authorities);
    }
}
