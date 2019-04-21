package com.pinin.alex.security;

import com.pinin.alex.dto.account.AccountAddDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;

public interface AccountService extends UserDetailsService, ClientDetailsService {

    void register(AccountAddDto addDto);
}
