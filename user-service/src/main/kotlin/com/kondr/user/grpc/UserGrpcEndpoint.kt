package com.kondr.user.grpc

import com.kondr.grpc.user.*
import io.grpc.Status
import net.devh.boot.grpc.server.service.GrpcService
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@GrpcService
class UserGrpcEndpoint : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    private val userStorage = ConcurrentHashMap<String, UserResponse>()

    override suspend fun getUser(request: GetUserRequest): UserResponse {
        return userStorage[request.id]
            ?: throw Status.NOT_FOUND.asRuntimeException()
    }

    override suspend fun createUser(request: CreateUserRequest): UserResponse {
        val user = userResponse {
            id = UUID.randomUUID().toString()
            name = request.name
            email = request.email
        }
        userStorage[user.id] = user
        return user
    }

    override suspend fun getAllUsers(request: GetAllUsersRequest): UsersResponse {
        return usersResponse {
            users += userStorage.values
        }
    }

    override suspend fun deleteUser(request: DeleteUserRequest): DeleteUserResponse {
        if (userStorage.remove(request.id) == null) {
            throw Status.NOT_FOUND.asRuntimeException()
        }

        return DeleteUserResponse.getDefaultInstance()
    }

}
