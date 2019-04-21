package com.pinin.alex.dto.demodocument;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoDocumentAddDto {

    @NotBlank
    private String value;
}
