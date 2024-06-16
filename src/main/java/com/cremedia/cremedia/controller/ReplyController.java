package com.cremedia.cremedia.controller;
import com.cremedia.cremedia.models.dto.request.ReplyRequestDto;
import com.cremedia.cremedia.models.dto.response.ReplyResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ReplyResponseDto create(@RequestBody ReplyRequestDto replyRequestDto){
       return replyService.create(replyRequestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponseDto> getById(@PathVariable Long id) {
        ReplyResponseDto reply = replyService.getById(id);
        log.info("Reply found: {}", reply);
        return ResponseEntity.ok(reply);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReplyResponseDto> update(@PathVariable Long id,
                                                        @RequestBody ReplyRequestDto replyRequestDto) {
        ReplyResponseDto updatedReply = replyService.update(id, replyRequestDto);
        log.info("Reply updated: {}", updatedReply);
        return ResponseEntity.ok(updatedReply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        replyService.delete(id);
        log.info("Reply deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<ReplyResponseDto>> getByPost(@PathVariable Post postId) {
        List<ReplyResponseDto> replies = replyService.getByPost(postId);
        log.info("Replies found for post with id {}: {}", postId, replies);
        return ResponseEntity.ok(replies);
    }
}
