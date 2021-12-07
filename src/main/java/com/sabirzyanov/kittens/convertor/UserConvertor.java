package com.sabirzyanov.kittens.convertor;

import com.sabirzyanov.kittens.DTO.UserDto;
import com.sabirzyanov.kittens.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {
    private final ModelMapper modelMapper;

    public UserConvertor() {
        this.modelMapper = new ModelMapper();
    }

    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
