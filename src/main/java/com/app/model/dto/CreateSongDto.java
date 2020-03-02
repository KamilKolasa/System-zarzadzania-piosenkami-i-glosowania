package com.app.model.dto;

import com.app.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSongDto {
    private String name;
    private String author;
    private Genre genre;
}
