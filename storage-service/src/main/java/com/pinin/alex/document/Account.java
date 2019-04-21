package com.pinin.alex.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "accounts")
@TypeAlias("Account")
public class Account extends AbstractDocument {

    @Indexed(unique = true)
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @Email
    private String email;

    private long timestamp;

    @Builder
    public Account(String id, @NotNull LocalDateTime created, LocalDateTime updated,
                   @NotBlank String login, @NotBlank String password, @Email String email, long timestamp) {
        super(id, created, updated);
        this.login = login;
        this.password = password;
        this.email = email;
        this.timestamp = timestamp;
    }
}
