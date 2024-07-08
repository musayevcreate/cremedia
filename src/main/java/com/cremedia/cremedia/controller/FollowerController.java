package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/followers")
public class FollowerController {

    private final FollowerService followerService;

    @PostMapping("/follow")
    public ResponseEntity<String> follow(@RequestBody Long followerId, @RequestBody Long followingId) {
        followerService.follow(followerId, followingId);
        return new ResponseEntity<>("User followed successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollow(@RequestParam Long followerId, @RequestParam Long followingId) {
        followerService.unfollow(followerId, followingId);
        return new ResponseEntity<>("User unfollowed successfully", HttpStatus.OK);
    }
}
