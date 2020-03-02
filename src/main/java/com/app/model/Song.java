package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "song")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Vote> votes;
}
