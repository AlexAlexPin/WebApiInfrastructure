package com.pinin.alex.dto.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestDto<T> {
    @NonNull
    private RequestMetadata metadata;

    @NonNull
    private T data;
}
