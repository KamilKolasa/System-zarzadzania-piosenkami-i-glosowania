package com.app.controller;

import com.app.model.Genre;
import com.app.model.dto.GetSongDto;
import com.app.model.dto.Info;
import com.app.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stat")
public class StatisticRestController {

    private final StatisticService statisticService;

    @GetMapping("/popular")
    public Info<String> popularSongs(@RequestParam("count") Integer count) {
        List<GetSongDto> songs = statisticService.popularSongEver(count);
        return Info.<String>builder().data(songs.toString()).build();
    }

    @GetMapping("/popularUser")
    public Info<List<GetSongDto>> popularSongsforUser(@RequestParam("count") Integer count, @RequestParam("user") String user) {
        List<GetSongDto> songs = statisticService.popularSongForUser(count, user);
        return Info.<List<GetSongDto>>builder().data(songs).build();
    }

    @GetMapping("/popularGenre")
    public Info<String> listPopularSongsByGenre() {
        Map<Genre, GetSongDto> songs = statisticService.popularSongInGenre();
        return Info.<String>builder().data(songs.toString()).build();
    }

    @GetMapping("/popularDate")
    public Info<String> listPopularSongInDate(@RequestParam("count") Integer count, @RequestParam("year") Integer year, @RequestParam("month") Integer month) {
        List<GetSongDto> songs = statisticService.listPopularSongInDate(count, year, month);
        return Info.<String>builder().data(songs.toString()).build();
    }
}
