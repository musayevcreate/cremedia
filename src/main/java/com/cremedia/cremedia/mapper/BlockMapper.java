//package com.cremedia.cremedia.mapper;
//
//import com.cremedia.cremedia.models.dto.response.BlockResponseDto;
//import com.cremedia.cremedia.models.entity.BlockList;
//import jakarta.persistence.Column;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface BlockMapper {
//    @Mapping(source = "user.id", target = "user")
//    @Mapping(source = "user.id", target = "blocked")
//    BlockResponseDto toDto(BlockList blockList);
//    List<BlockResponseDto> toDtoList(List<BlockList> blockLists);
//}
