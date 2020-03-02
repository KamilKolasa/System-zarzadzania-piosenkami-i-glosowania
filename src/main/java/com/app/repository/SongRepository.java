package com.app.repository;

import com.app.model.Genre;
import com.app.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("select distinct genre from Song s")
    List<Genre> findAllGenres();
}
