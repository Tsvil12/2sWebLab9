# Лабораторная работа №7: Безопасность (Spring Security + JWT)

## Выполнил Цвиль Павел ФИТ-231
---
## Статус
- Аутентификация (регистрация/логин) работает  
- JWT токены генерируются и валидируются  
- Авторизация по ролям USER/ADMIN  
- Обработка ошибок 401/403

## Что сделано

### 1. Расширение сущности User
- Добавлены поля `password` (BCrypt) и `role` (USER/ADMIN)
- Flyway миграция `V6__add_password_and_role_to_users.sql`

### 2. Аутентификация
- `POST /auth/register` — регистрация нового пользователя, возвращает JWT
- `POST /auth/login` — логин, возвращает JWT
- Пароли хешируются с помощью BCryptPasswordEncoder

### 3. JWT
- `JwtService` — генерация токена, валидация, извлечение email и роли
- `JwtAuthenticationFilter` — проверяет токен в заголовке `Authorization: Bearer <token>`

### 4. Spring Security
- `SecurityConfig` — отключён CSRF, stateless сессии
- Открыты эндпоинты: `/auth/**` и `GET /api/v1/restaurants/**`
- Все остальные требуют аутентификации

### 5. Авторизация (@PreAuthorize)
- CRUD для ресторанов и блюд — только ADMIN
- Создание заказа — USER или ADMIN
- Просмотр заказа — владелец или ADMIN
- Изменение статуса заказа — только ADMIN

### 6. Обработка ошибок безопасности
- `BadCredentialsException` → 401
- `AccessDeniedException` → 403
- Невалидный JWT → 401
