package com.cremedia.cremedia.controller;
import com.cremedia.cremedia.mapper.LikeMapper;
import com.cremedia.cremedia.models.dto.request.LikeRequestDto;
import com.cremedia.cremedia.models.dto.response.LikeResponseDto;
import com.cremedia.cremedia.models.entity.Like;
import com.cremedia.cremedia.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;
    private final LikeMapper likeMapper;

    @PostMapping("/like")
    public ResponseEntity<LikeResponseDto> like(@RequestBody LikeRequestDto likeRequestDto) {
        Like like = likeService.likePost(likeRequestDto.getPostId(), likeRequestDto.getUserId());
        LikeResponseDto likeResponseDto = likeMapper.toResponseDto(like);
        return new ResponseEntity<>(likeResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/unlike/{likeId}")
    public ResponseEntity<String> unlike(@PathVariable Long likeId) {
        likeService.unlikePost(likeId);
        return new ResponseEntity<>("Post unliked successfully", HttpStatus.OK);
    }
}
