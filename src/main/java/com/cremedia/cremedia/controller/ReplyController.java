package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.dto.request.ReplyRequestDto;
import com.cremedia.cremedia.models.dto.response.ReplyResponseDto;
import com.cremedia.cremedia.models.entity.Post;
import com.cremedia.cremedia.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "Create a new Reply")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReplyResponseDto create(@Valid @RequestBody ReplyRequestDto replyRequestDto) {
        return replyService.create(replyRequestDto);
    }

    @Operation(summary = "Get a Reply by ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReplyResponseDto getById(@PathVariable Long id) {
        return getOrThrow(id);
    }

    @Operation(summary = "Update a Reply by ID")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReplyResponseDto update(@PathVariable Long id, @Valid @RequestBody ReplyRequestDto replyRequestDto) {
        return getOrThrow(id, replyService.update(id, replyRequestDto));
    }

    @Operation(summary = "Delete a Reply by ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        getOrThrow(id);
        replyService.delete(id);
        log.info("Reply deleted with ID: {}", id);
    }

    @Operation(summary = "Get Replies by Post ID")
    @GetMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReplyResponseDto> getByPost(@PathVariable Long postId) {
        List<ReplyResponseDto> replies = replyService.getByPost(postId);
        log.info("Replies found for post with ID {}: {}", postId, replies);
        return replies;
    }

    private ReplyResponseDto getOrThrow(Long id) {
        ReplyResponseDto reply = replyService.getById(id);
        if (reply == null) {
            log.info("Reply with ID {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found");
        }
        log.info("Reply found: {}", reply);
        return reply;
    }

    private ReplyResponseDto getOrThrow(Long id, ReplyResponseDto reply) {
        if (reply == null) {
            log.info("Reply with ID {} not found", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found");
        }
        log.info("Reply updated: {}", reply);
        return reply;
    }
}
