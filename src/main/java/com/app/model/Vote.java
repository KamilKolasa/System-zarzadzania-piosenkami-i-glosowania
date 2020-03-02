package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "song_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Song song;
}
