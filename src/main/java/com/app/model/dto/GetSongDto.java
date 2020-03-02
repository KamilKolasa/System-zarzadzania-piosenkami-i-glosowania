package com.app.model.dto;

import com.app.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSongDto {
    private String name;
    private String author;
    private Genre genre;
}
