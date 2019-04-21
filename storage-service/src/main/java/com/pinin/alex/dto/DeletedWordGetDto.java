package com.pinin.alex.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeletedWordGetDto {

    @NonNull
    private String id;

    @NonNull
    private Date created;
}
