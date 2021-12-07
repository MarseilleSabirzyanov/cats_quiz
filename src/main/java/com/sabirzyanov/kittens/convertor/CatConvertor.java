package com.sabirzyanov.kittens.convertor;

import com.sabirzyanov.kittens.DTO.CatDto;
import com.sabirzyanov.kittens.domain.Cat;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CatConvertor {
    private final ModelMapper modelMapper;

    public CatConvertor() {
        this.modelMapper = new ModelMapper();
    }

    public CatDto convertToCatDto(Cat cat) {
        return modelMapper.map(cat, CatDto.class);
    }
}
