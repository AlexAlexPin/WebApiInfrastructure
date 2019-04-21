package com.pinin.alex.document;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
abstract class AbstractDocument {

    @Id
    private String id;

    @NotNull
    private LocalDateTime created;

    private LocalDateTime updated;
}
