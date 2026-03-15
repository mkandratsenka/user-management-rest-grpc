package com.kondr.api.client;

import com.kondr.api.dto.UserRequestDto;
import com.kondr.api.dto.UserResponseDto;
import com.kondr.grpc.user.CreateUserRequest;
import com.kondr.grpc.user.DeleteUserRequest;
import com.kondr.grpc.user.GetAllUsersRequest;
import com.kondr.grpc.user.GetUserRequest;
import com.kondr.grpc.user.UserResponse;
import com.kondr.grpc.user.UserServiceGrpc.UserServiceBlockingStub;
import java.util.List;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class UserGrpcClient {

  private final UserServiceBlockingStub userServiceStub;

  public UserGrpcClient(@GrpcClient("user-service") UserServiceBlockingStub userServiceStub) {
    this.userServiceStub = userServiceStub;
  }

  public UserResponseDto getUserById(String id) {
    UserResponse userResponse = userServiceStub.getUser(
        GetUserRequest.newBuilder().setId(id).build()
    );
    return mapToDto(userResponse);
  }

  public UserResponseDto createUser(UserRequestDto userDto) {
    UserResponse response = userServiceStub.createUser(
        CreateUserRequest.newBuilder()
            .setName(userDto.name())
            .setEmail(userDto.email())
            .build()
    );
    return mapToDto(response);
  }

  public List<UserResponseDto> getAllUsers() {
    return userServiceStub.getAllUsers(GetAllUsersRequest.getDefaultInstance())
        .getUsersList().stream()
        .map(this::mapToDto)
        .toList();
  }

  public void deleteUserById(String id) {
    userServiceStub.deleteUser(DeleteUserRequest.newBuilder().setId(id).build());
  }

  private UserResponseDto mapToDto(UserResponse user) {
    return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
  }

}