package com.kondr.api.rest;


import com.kondr.api.client.UserGrpcClient;
import com.kondr.api.dto.UserRequestDto;
import com.kondr.api.dto.UserResponseDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

  private final UserGrpcClient userGrpcClient;

  public UserRestController(UserGrpcClient userGrpcClient) {
    this.userGrpcClient = userGrpcClient;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponseDto createUser(@RequestBody UserRequestDto userDto) {
    return userGrpcClient.createUser(userDto);
  }

  @GetMapping("/{id}")
  public UserResponseDto getUser(@PathVariable String id) {
    return userGrpcClient.getUserById(id);
  }

  @GetMapping
  public List<UserResponseDto> getAllUsers() {
    return userGrpcClient.getAllUsers();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable String id) {
    userGrpcClient.deleteUserById(id);
  }

}