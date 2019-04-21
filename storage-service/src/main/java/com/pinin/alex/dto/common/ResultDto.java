package com.pinin.alex.dto.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ResultDto<T> {
    private ResultMetadata metadata;
    private T data;
}
