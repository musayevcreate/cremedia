//package com.cremedia.cremedia.mapper;
//
//import com.cremedia.cremedia.models.dto.request.VisibilityRequestDto;
//import com.cremedia.cremedia.models.dto.response.VisibilityResponseDto;
//import com.cremedia.cremedia.models.entity.Visibility;
//
//public class VisibilityMapper {
//    public Visibility toEntity(VisibilityRequestDto visibilityRequestDto) {
//        Visibility visibility = new Visibility();
//        visibility.setType(visibilityRequestDto.getType());
//        return visibility;
//    }
//
//    public VisibilityResponseDto toDto(Visibility visibility) {
//        VisibilityResponseDto visibilityResponseDto = new VisibilityResponseDto();
//        visibilityResponseDto.setId(visibility.getId());
//        visibilityResponseDto.setType(visibility.getType());
//        return visibilityResponseDto;
//    }
//}
