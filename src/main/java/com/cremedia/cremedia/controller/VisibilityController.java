//package com.cremedia.cremedia.controller;
//
//import com.cremedia.cremedia.models.dto.request.VisibilityRequestDto;
//import com.cremedia.cremedia.models.dto.response.VisibilityResponseDto;
//import com.cremedia.cremedia.service.VisibilityService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/visibilities")
//public class VisibilityController {
//
//    private final VisibilityService visibilityService;
//
//    @PostMapping
//    public ResponseEntity<VisibilityResponseDto> create(@RequestBody VisibilityRequestDto visibilityRequestDto) {
//        VisibilityResponseDto visibilityResponseDto = visibilityService.create(visibilityRequestDto);
//        return ResponseEntity.ok(visibilityResponseDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<VisibilityResponseDto>> getAll() {
//        List<VisibilityResponseDto> visibilities = visibilityService.getAll();
//        return ResponseEntity.ok(visibilities);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<VisibilityResponseDto> getById(@PathVariable Long id) {
//        VisibilityResponseDto visibilityResponseDto = visibilityService.getById(id);
//        return ResponseEntity.ok(visibilityResponseDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        visibilityService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
