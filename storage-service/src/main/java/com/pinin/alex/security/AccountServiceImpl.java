package com.pinin.alex.security;

import com.pinin.alex.document.Account;
import com.pinin.alex.dto.account.AccountAddDto;
import com.pinin.alex.repository.AccountRepository;
import com.pinin.alex.service.exceptions.AccountCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(AccountAddDto addDto) {
        Account account = Account.builder()
                .created(LocalDateTime.now())
                .login(addDto.getLogin())
                .password(passwordEncoder.encode(addDto.getPassword()))
                .email(addDto.getEmail())
                .build();
        try {
            accountRepository.save(account);
        } catch (DuplicateKeyException e) {
            throw new AccountCreationException(addDto.getLogin(), "account exists", e);
        } catch (Exception e) {
            throw new AccountCreationException(addDto.getLogin(), "unknown error", e); // TODO add more exception handlers
        }
    }

    // UserDetailsService

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = findAccount(login);
        return new User(account.getLogin(), account.getPassword(), getAuthorities());
    }

    // ClientDetailsService

    @Override
    public ClientDetails loadClientByClientId(String login) throws ClientRegistrationException {
        Account account = findAccount(login);
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(account.getLogin());
        clientDetails.setClientSecret(account.getPassword());
        clientDetails.setAuthorities(getAuthorities());
        return clientDetails;
    }

    private Account findAccount(String login) {
        return accountRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Account login='%s' not found", login)));
    }

    private List<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("user")); // TODO authorities to constants
    }
}
