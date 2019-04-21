package com.pinin.alex.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "demo_documents")
@TypeAlias("DemoDocument")
public class DemoDocument extends AbstractDocument implements AccountDocument {

    @DBRef
    @NotNull
    private Account account;

    @NotBlank
    private String value;

    @Builder
    public DemoDocument(String id, @NotNull LocalDateTime created, LocalDateTime updated,
                        @NotNull Account account, @NotBlank String value) {
        super(id, created, updated);
        this.account = account;
        this.value = value;
    }
}
