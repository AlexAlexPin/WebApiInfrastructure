package com.pinin.alex.dto.demodocument;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoDocumentGetDto {

    private String id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String value;
}
