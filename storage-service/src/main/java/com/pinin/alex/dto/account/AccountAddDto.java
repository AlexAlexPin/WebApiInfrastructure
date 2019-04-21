package com.pinin.alex.dto.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AccountAddDto {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @Email
    private String email;
}
