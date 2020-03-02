package com.app.controller;

import com.app.model.dto.Info;
import com.app.model.dto.VoteDto;
import com.app.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

    private final VoteService voteService;

    @PostMapping("/addVote")
    public Info<String> AddVote(@RequestBody VoteDto voteDto) {
        voteService.addVote(voteDto);
        return Info.<String>builder().data("VOTE IS ADD").build();
    }
}
