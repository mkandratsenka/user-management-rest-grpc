# User Management (gRPC Demo)

## Архитектура
Взаимодействие между компонентами строится по следующей цепочке:

`Client -> api-service (REST) -> user-service (gRPC)`

**api-service**: Java 21, Spring Boot, Virtual Threads, REST API.

**user-service**: Kotlin, Spring Boot, gRPC-сервер.

**proto**: Общий модуль с `.proto` контрактами.

## REST API

| Метод    | URL               | Описание                           |
|:---------|:------------------|:-----------------------------------|
| `POST`   | `/api/users`      | Создать пользователя               |
| `GET`    | `/api/users/{id}` | Получить пользователя по ID        |
| `GET`    | `/api/users`      | Получить список всех пользователей |
| `DELETE` | `/api/users/{id}` | Удалить пользователя по ID         |