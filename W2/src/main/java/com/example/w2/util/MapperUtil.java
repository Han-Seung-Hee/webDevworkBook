package com.example.w2.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

// VO 와 DTO 간 변환을 위한 ModelMapper의 설정을 변경등을 담당함.
public enum MapperUtil {
    INSTANCE;

    private ModelMapper modelMapper;

    // private 로 선언된 필드도 접근 가능하도록.
    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ModelMapper get(){
        return modelMapper;
    }
}
