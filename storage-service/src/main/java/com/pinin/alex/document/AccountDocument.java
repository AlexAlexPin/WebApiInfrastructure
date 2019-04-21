package com.pinin.alex.document;

import java.time.LocalDateTime;

public interface AccountDocument {

    String getId();

    LocalDateTime getCreated();

    void setCreated(LocalDateTime created);

    LocalDateTime getUpdated();

    void setUpdated(LocalDateTime updated);

    Account getAccount();

    void setAccount(Account account);
}
