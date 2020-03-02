package com.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Info<T> {

    @Builder.Default
    private T data = null;

    @Builder.Default
    private String error = null;
}
