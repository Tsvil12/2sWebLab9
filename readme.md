# Лабораторная работа №5: Обработка ошибок. Валидация. Логгирование

Выполнил Цвиль Павел ФИТ-231
---
## Статус
- Приложение запускается  
- Все эндпоинты протестированы  
- Валидация работает  
- Ошибки возвращаются в едином формате  
- Логи пишутся в консоль и файл

## Что сделано

### 1. Иерархия кастомных исключений
- `sealed class AppException` — базовый класс
- `NotFoundException` (404) — ресурс не найден
- `AlreadyExistsException` (409) — дубликат
- `InvalidOrderStateException` (400) — недопустимый статус заказа

### 2. Глобальный обработчик ошибок (@RestControllerAdvice)
- `NotFoundException` -> 404
- `AlreadyExistsException` -> 409
- `InvalidOrderStateException` -> 400
- `MethodArgumentNotValidException` -> 400
- `Exception` -> 500 (без стека клиенту)

### 3. Валидация DTO
- `@field:NotBlank`, `@field:Email`, `@field:Positive`, `@field:NotEmpty`
- `@Valid` в контроллерах для `@RequestBody`
- Все входные DTO валидируются перед обработкой

### 4. Логгирование
- Библиотека `kotlin-logging`
- Сервисы: INFO (создание/обновление), WARN (не найдено)
- `GlobalExceptionHandler`: ERROR (непредвиденные ошибки с трейсом)

### 5. Настройка логов (logback-spring.xml)
- Консольный вывод
- Файловый вывод с ротацией (FILE, 10MB, 30 дней)
- Уровни: проект — DEBUG, Spring — WARN, Hibernate SQL — DEBUG