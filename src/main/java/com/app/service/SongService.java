package com.app.service;

import com.app.exception.AppException;
import com.app.model.dto.CreateSongDto;
import com.app.model.dto.GetSongDto;
import com.app.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public CreateSongDto addSong(CreateSongDto createSongDto) {
        if (createSongDto == null) {
            throw new AppException("ADD SONG - SONG IS NULL");
        }

        return Mapper.fromSongToCreateSongDto(songRepository.save(Mapper.fromCreateSongDtoToSong(createSongDto)));
    }
}
