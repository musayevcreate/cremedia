package com.cremedia.cremedia.controller;
import com.cremedia.cremedia.models.dto.request.FollowerRequestDto;
import com.cremedia.cremedia.models.dto.response.FollowerResponseDto;
import com.cremedia.cremedia.service.FollowerService;
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
    public FollowerResponseDto follow(@RequestBody FollowerRequestDto dto) {
        return followerService.follow(dto);
    }

    @DeleteMapping("/{id}")
    public void unfollow(@PathVariable Long id) {
        followerService.unfollow(id);
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
