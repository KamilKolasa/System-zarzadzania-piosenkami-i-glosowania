package com.app.repository;

import com.app.model.Genre;
import com.app.model.Song;
import com.app.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select s from Vote v join v.song s join v.user u where u.username = :username")
    List<Song> findAllSongsFromUser(@Param("username") String username);
}
