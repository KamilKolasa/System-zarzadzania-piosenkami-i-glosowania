package com.app.service;

import com.app.exception.AppException;
import com.app.model.Genre;
import com.app.model.Vote;
import com.app.model.dto.GetSongDto;
import com.app.repository.SongRepository;
import com.app.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final VoteRepository voteRepository;
    private final SongRepository songRepository;

    public List<GetSongDto> popularSongEver(Integer count) {
        if (count == null) {
            throw new AppException("Count is Null");
        }
        return voteRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Vote::getSong, Collectors.counting()))
                .entrySet().stream()
                .sorted((s1, s2) -> s2.getValue().compareTo(s1.getValue()))
                .limit(count)
                .map(e -> Mapper.fromSongToGetSongDto(e.getKey()))
                .collect(Collectors.toList());
    }

    public List<GetSongDto> popularSongForUser(Integer count, String username) {
        if (count == null) {
            throw new AppException("Count is Null");
        }
        if (username == null) {
            throw new AppException("Username is Null");
        }

        return voteRepository
                .findAllSongsFromUser("u")
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(count)
                .map(e -> Mapper.fromSongToGetSongDto(e.getKey()))
                .collect(Collectors.toList());
    }

    public Map<Genre, GetSongDto> popularSongInGenre() {
        Map<GetSongDto, Long> songsPopular = voteRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Vote::getSong, Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.toMap(s -> Mapper.fromSongToGetSongDto(s.getKey()), s -> s.getValue()));

        List<Genre> genres = songRepository.findAllGenres();
        Map<Genre, GetSongDto> genreSong = new HashMap<>();
        for (Genre g : genres) {
            genreSong.put(g, null);
        }

        List<Genre> genres2 = songRepository.findAllGenres();
        genres2.forEach(System.out::println);

        for (Map.Entry<Genre, GetSongDto> gs : genreSong.entrySet()) {
            Long popularSong = 0L;
            for (Map.Entry<GetSongDto, Long> sp : songsPopular.entrySet()) {
                if (gs.getKey().equals(sp.getKey().getGenre())) {
                    if (gs.getValue() == null || popularSong > sp.getValue()) {
                        gs.setValue(sp.getKey());
                        popularSong = sp.getValue();
                    }
                }
            }
        }
        return genreSong;
    }

    public List<GetSongDto> listPopularSongInDate(Integer count, Integer year, Integer month) {
        if (count == null) {
            throw new AppException("Count is Null");
        }
        if (year == null) {
            throw new AppException("Year is Null");
        }
        if (month == null) {
            throw new AppException("Month is Null");
        }
        return voteRepository.findAll()
                .stream()
                .filter(s ->
                        s.getDate().isAfter(dateSupport(year, month, false))
                                && s.getDate().isBefore(dateSupport(year, month, true)))
                .collect(Collectors.groupingBy(Vote::getSong, Collectors.counting()))
                .entrySet().stream()
                .sorted((s1, s2) -> s2.getValue().compareTo(s1.getValue()))
                .limit(count)
                .map(e -> Mapper.fromSongToGetSongDto(e.getKey()))
                .collect(Collectors.toList());
    }

    private static LocalDate dateSupport(Integer year, Integer month, boolean monthAfter) {
        LocalDate localDatePlus = LocalDate.of(year, month, 1);
        if (monthAfter) {
            return localDatePlus.plusMonths(1);
        } else {
            return localDatePlus.minusDays(1);
        }
    }
}
