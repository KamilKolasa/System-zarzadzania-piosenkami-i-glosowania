package com.app.service;

import com.app.model.Song;
import com.app.model.User;
import com.app.model.Vote;
import com.app.model.dto.*;
import com.app.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface Mapper {

    static GetSongDto fromSongToGetSongDto(Song song){
        return song == null ? null : GetSongDto.builder()
                .name(song.getName())
                .author(song.getAuthor())
                .genre(song.getGenre())
                .build();
    }

    static GetUserDto fromUserToCreateUserDto(User user){
        return user == null ? null : GetUserDto.builder()
                .username(user.getUsername())
                .age(user.getAge())
                .role(user.getRole())
                .build();
    }

    static User fromCreateUserDtoToUser(CreateUserDto createUserDto){
        return createUserDto == null ? null : User.builder()
                .username(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                .email(createUserDto.getEmail())
                .age(createUserDto.getAge())
                .role(createUserDto.getRole())
                .build();
    }

    static CreateSongDto fromSongToCreateSongDto(Song song){
        return song == null ? null : CreateSongDto.builder()
                .name(song.getName())
                .author(song.getAuthor())
                .genre(song.getGenre())
                .build();
    }

    static Song fromCreateSongDtoToSong(CreateSongDto createSongDto){
        return createSongDto == null ? null : Song.builder()
                .name(createSongDto.getName())
                .author(createSongDto.getAuthor())
                .genre(createSongDto.getGenre())
                .build();
    }

    static Vote fromVoteDtoToVote(VoteDto votedto){
        return votedto == null ? null : Vote.builder()
                .song(Song.builder().id(votedto.getSongId()).build())
                .date(LocalDate.now())
                .build();
    }

    static VoteDto fromVoteToVoteDto(Vote vote){
        return vote == null ? null : VoteDto.builder()
                .songId(vote.getSong().getId())
                .date(vote.getDate())
                .build();
    }

    static GetVoteDto fromVoteToGetVoteDto(Vote vote){
        return vote == null ? null : GetVoteDto.builder()
                .song(fromSongToGetSongDto(vote.getSong()))
                .user(fromUserToCreateUserDto(vote.getUser()))
                .build();
    }
}
