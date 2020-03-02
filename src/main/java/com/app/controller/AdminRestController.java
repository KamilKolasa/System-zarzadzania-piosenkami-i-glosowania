package com.app.controller;

import com.app.model.dto.CreateSongDto;
import com.app.model.dto.Info;
import com.app.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {

    private final SongService songService;

    @PostMapping("/addSong")
    public Info<String> AddSong(@RequestBody CreateSongDto createSongDto) {
        var createSong = songService.addSong(createSongDto);
        return Info.<String>builder().data("SONG " + createSong.getName() + " IS ADD").build();
    }
}
