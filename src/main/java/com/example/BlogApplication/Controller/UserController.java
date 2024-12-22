package com.example.BlogApplication.Controller;

import com.example.BlogApplication.Payload.UserDto;
import com.example.BlogApplication.Responce.ApiResponse;
import com.example.BlogApplication.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void  UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> CreateUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto SaveDto = userService.CreateUser(userDto);
        return new ResponseEntity<>(SaveDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> UpdateUser(@RequestBody UserDto userDto, @PathVariable long userId)
    {
        UserDto updatedDto=userService.UpdateUser(userDto,userId);
        return ResponseEntity.ok(updatedDto);
    }

    @GetMapping("/users/{UserId}")
    public ResponseEntity<UserDto> GetUserById(@PathVariable long UserId)
    {
       UserDto userDto = userService.GetUser(UserId);
      return new ResponseEntity<>(userDto,HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> GetAll()
    {
        List<UserDto> userDtos = userService.GetAllUsers();
        return ResponseEntity.ok(userDtos);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> DeleteUser(long userId)
    {
        userService.DeleteUser(userId);

        return ResponseEntity.ok(new ApiResponse("user is deleted",true));
    }
}
