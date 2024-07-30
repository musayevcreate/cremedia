package com.cremedia.cremedia.controller;
import com.cremedia.cremedia.models.dto.request.FollowerRequestDto;
import com.cremedia.cremedia.models.dto.response.FollowerResponseDto;
import com.cremedia.cremedia.service.FollowerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/followers")
public class FollowerController {

    private final FollowerService followerService;

    @Autowired
    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @PostMapping
    public FollowerResponseDto follow(@RequestBody FollowerRequestDto dto, HttpServletRequest request) {
        return followerService.follow(dto,request);
    }


    @PostMapping("/unfollow")
    public void unfollow(@RequestBody FollowerRequestDto requestDto, HttpServletRequest request) {
        followerService.unfollow(requestDto, request);
    }

    @GetMapping("/followers/{userId}")
    public List<FollowerResponseDto> getFollowers(@PathVariable Long userId) {
        return followerService.getFollowers(userId);
    }

    @GetMapping("/following/{userId}")
    public List<FollowerResponseDto> getFollowing(@PathVariable Long userId) {
        return followerService.getFollowing(userId);
    }
}
