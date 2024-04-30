package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.models.User;
import com.cremedia.cremedia.models.entity.UserTag;
import com.cremedia.cremedia.service.UserTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userTags")
@RequiredArgsConstructor
public class UserTagController {

    private final UserTagService userTagService;

    @GetMapping("/{userId}")
    public UserTag getById(@PathVariable Long userId){
        return userTagService.getById(userId);
    }

    @GetMapping
    public Page<UserTag> getAll(Pageable pageable){
        return userTagService.getAll(pageable);
    }

    @PostMapping
    public ResponseEntity<UserTag> add(@RequestBody UserTag userTag){
        return ResponseEntity.status(HttpStatus.CREATED).header("tag_name", "test").body(userTagService.add(userTag));
    }

    @PutMapping
    public UserTag update(@RequestBody UserTag userTag){
        return userTagService.update(userTag);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userTagService.delete(id);
    }
}
