package com.example.BlogApplication.Service;

import com.example.BlogApplication.Entity.User;
import com.example.BlogApplication.Exception.ResourceNotFoundException;
import com.example.BlogApplication.Payload.UserDto;
import com.example.BlogApplication.Repositor.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceimp implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

     @Autowired
    public void UserServiceImp(UserRepository userRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }
    @Override
    public UserDto CreateUser(UserDto userDto) {

        User user = toUser(userDto);
        userRepository.save(user);
        return this.toUserDto(user);
    }

    @Override
    public UserDto UpdateUser(UserDto userDto,long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return this.toUserDto(user);
    }

    @Override
    public UserDto GetUser(long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        return this.toUserDto(user);
    }

    @Override
    public void DeleteUser(long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> GetAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserDto> userDtos=users.stream().map(user -> toUserDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    public User toUser(UserDto userDto) {
        User user = new User();
      return  modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

//        return user;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
       return modelMapper.map(user,UserDto.class);
        /*userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
*/
//        return userDto;
    }
}
