//package com.cremedia.cremedia.controller;
//
//import com.cremedia.cremedia.models.dto.request.BlockRequestDto;
//import com.cremedia.cremedia.models.dto.response.BlockResponseDto;
//import com.cremedia.cremedia.service.BlockService;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/block")
//@AllArgsConstructor
//@RequiredArgsConstructor
//public class BlockController {
//
//    private BlockService blockService;
//
//    @PostMapping("/block")
//    public String blockUser(@RequestBody BlockRequestDto blockRequestDTO) {
//        blockService.blockUser(blockRequestDTO);
//        return "User blocked successfully";
//    }
//
//    @GetMapping("/blocked-users")
//    public List<BlockResponseDto> getBlockedUsers(@RequestParam String username) {
//        return blockService.getBlockedUsers(username);
//    }
//}
