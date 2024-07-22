//package com.cremedia.cremedia.service.Impl;
//
//import com.cremedia.cremedia.exception.EntityNotFoundException;
//import com.cremedia.cremedia.mapper.BlockMapper;
//import com.cremedia.cremedia.models.dto.request.BlockRequestDto;
//import com.cremedia.cremedia.models.dto.response.BlockResponseDto;
//import com.cremedia.cremedia.models.entity.BlockList;
//import com.cremedia.cremedia.models.entity.User;
//import com.cremedia.cremedia.repository.BlockListRepository;
//import com.cremedia.cremedia.repository.UserRepository;
//import com.cremedia.cremedia.service.BlockService;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@AllArgsConstructor
//public class BlockServiceImpl implements BlockService {
//
//    private UserRepository userRepository;
//
//    private BlockListRepository blockListRepository;
//
//    private BlockMapper blockMapper;
//
//    @Override
//    public void blockUser(BlockRequestDto blockRequestDTO) {
//        User user = userRepository.findUserByUsername(blockRequestDTO.getUser())
//                .orElseThrow(() -> new EntityNotFoundException("USER_NOT_FOUND"));
//        User blockedUser = userRepository.findUserByUsername(blockRequestDTO.getBlocked())
//                .orElseThrow(() -> new EntityNotFoundException("BLOCKED_USER_NOT_FOUND"));
//
//        if (blockListRepository.existsByUserAndBlockedUser(user, blockedUser)) {
//            throw new RuntimeException("User is already blocked");
//        }
//
//        BlockList blockList = new BlockList();
//        blockList.setUser(user);
//        blockList.setBlocked(blockedUser);
//        blockListRepository.save(blockList);
//    }
//
//    @Override
//    public List<BlockResponseDto> getBlockedUsers(String username) {
//        User user = userRepository.findUserByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        List<BlockList> blockLists = blockListRepository.findByUser(user);
//        return blockMapper.toDtoList(blockLists);
//    }
//}
