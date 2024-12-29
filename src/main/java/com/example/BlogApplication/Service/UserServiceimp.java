package com.example.BlogApplication.Service;

import com.example.BlogApplication.Entity.User;
import com.example.BlogApplication.Exception.ResourceNotFoundException;
import com.example.BlogApplication.Payload.UserDto;
import com.example.BlogApplication.Repositor.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceimp implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return this.toUserDto(user);
    }
    @CachePut(value = "User", key = "#userId")
    @Override
    public UserDto UpdateUser(UserDto userDto,long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(hashedPassword);
        user.setAbout(userDto.getAbout());
        return this.toUserDto(user);
    }
    @Cacheable(value = "User", key = "#userId")
    @Override
    public UserDto GetUser(long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        return this.toUserDto(user);
    }
    @CacheEvict(value = "User", key = "#userId")
    @Override
    public void DeleteUser(long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
        userRepository.delete(user);
    }
    @Cacheable(value ="Users")
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
