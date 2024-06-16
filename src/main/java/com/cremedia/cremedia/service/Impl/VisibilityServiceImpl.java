//package com.cremedia.cremedia.service.Impl;
//
//import com.cremedia.cremedia.mapper.VisibilityMapper;
//import com.cremedia.cremedia.repository.VisibilityRepository;
//import com.cremedia.cremedia.service.VisibilityService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class VisibilityServiceImpl implements VisibilityService {
//
//    private VisibilityRepository visibilityRepository;
//
//    private VisibilityMapper visibilityMapper;
//
//    @Override
//    public VisibilityResponseDTO createVisibility(VisibilityRequestDTO visibilityRequestDTO) {
//        Visibility visibility = visibilityMapper.toEntity(visibilityRequestDTO);
//        visibilityRepository.save(visibility);
//        return visibilityMapper.toDto(visibility);
//    }
//
//    @Override
//    public List<VisibilityResponseDTO> getAllVisibilities() {
//        return visibilityRepository.findAll().stream()
//                .map(visibilityMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public VisibilityResponseDTO getVisibilityById(Long id) {
//        Visibility visibility = visibilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Visibility not found"));
//        return visibilityMapper.toDto(visibility);
//    }
//
//    @Override
//    public void deleteVisibility(Long id) {
//        visibilityRepository.deleteById(id);
//    }
//}
//
