package com.cremedia.cremedia.mapper;

import com.cremedia.cremedia.models.dto.response.HashtagResponseDto;
import com.cremedia.cremedia.models.entity.Hashtag;

public class HashtagMapper {

    public static HashtagResponseDto toDto(Hashtag hashtag) {
        HashtagResponseDto dto = new HashtagResponseDto();
        dto.setId(hashtag.getId());
        dto.setText(hashtag.getText());
        return dto;
    }
}
