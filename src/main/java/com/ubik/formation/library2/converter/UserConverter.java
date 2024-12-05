package com.ubik.formation.library2.converter;

import com.ubik.formation.library2.dto.UserDto;
import com.ubik.formation.library2.model.Book;
import com.ubik.formation.library2.model.User;
import com.ubik.formation.library2.service.UserService;

public class UserConverter {

    public UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        if (user == null) {
            return userDto;
        }
        userDto.setEmail(user.getEmail());
        userDto.setLogin(user.getLogin());
        userDto.setUsername(user.getUsername());

        return userDto;
    }
}
