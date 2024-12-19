package com.example.BlogApplication.Service;

import com.example.BlogApplication.Payload.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    UserDto CreateUser(UserDto userDto);

    UserDto UpdateUser(UserDto userDto,long userId);

    UserDto GetUser(long userId);

    void DeleteUser(long userId);

    List<UserDto> GetAllUsers();
}
