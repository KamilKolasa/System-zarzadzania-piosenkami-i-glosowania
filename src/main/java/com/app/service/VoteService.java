package com.app.service;

import com.app.exception.AppException;
import com.app.model.Vote;
import com.app.model.dto.VoteDto;
import com.app.repository.SongRepository;
import com.app.repository.UserRepository;
import com.app.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public VoteDto addVote(VoteDto voteDto) {
        if (voteDto == null) {
            throw new AppException("ADD VOTE - VOTE IS NULL");
        }

        if(voteDto.getDate() == null){
            voteDto.setDate(LocalDate.now());
        }
        Long idUser = userRepository.findByUsername(AuthenticationData.getAuthenticatedUser()).get().getId();
        Long idSong = voteDto.getSongId();

        Vote vote = Mapper.fromVoteDtoToVote(VoteDto.builder().date(voteDto.getDate()).build());
        vote.setSong(songRepository.findById(idSong).get());
        vote.setUser(userRepository.findById(idUser).get());

        return Mapper.fromVoteToVoteDto(voteRepository.save(vote));
    }
}
