# Лабораторная работа №8: Документация API, контейнеризация и CI/CD

## Выполнил Цвиль Павел ФИТ-231
---
## Статус
- SpringDoc (Swagger UI) работает  
- Dockerfile создан, образ собирается  
- docker-compose поднимает весь стек (postgres + app + pgadmin)
- Переменные окружения вынесены в .env  
- GitHub Actions (CD) настроен и отрабатывает

## Что сделано

### 1. SpringDoc (Swagger)
- Добавлена зависимость `springdoc-openapi-starter-webmvc-ui`
- Открыт доступ к `/swagger-ui/**`, `/swagger-ui.html`, `/v3/api-docs/**` в SecurityConfig
- Аннотированы эндпоинты в `RestaurantController` и `AuthController` (`@Operation`, `@ApiResponse`, `@Tag`)
- Swagger UI доступен: `http://localhost:8080/swagger-ui.html`

### 2. Dockerfile
- Создан `.dockerignore` (target/, .git/, .idea/, .env, *.md)
- Образ собирается командой `docker build -t spring-auth:latest .`

### 3. docker-compose.yml
- Три сервиса: `postgres`, `app`, `pgadmin`
- Переменные окружения вынесены в `.env`, добавлен `.env.example` в репозиторий
- `.env` добавлен в `.gitignore`

### 4. GitHub Actions (CD)
- Создан `.github/workflows/cd.yaml`
- Триггер: push в ветку `main`
- Образ собирается и пушится в GHCR (GitHub Container Registry)
- Тег: `ghcr.io/tsvil12/spring-auth`

## Так же прошу прощения за просроченный дедлайн по 9 лабе
- https://github.com/Tsvil12/2sWebLab9 извините (