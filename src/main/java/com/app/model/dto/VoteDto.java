package com.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
    private Long songId;
    // na potrzeby testowanie moge dodac LocalDate zeby moc pozniej miec glosy np
    // z maja 2017 ale w normalnych warunkach data glosowania bylaby data oddania glosu
    private LocalDate date;
}
