# Лабораторная работа №4: Spring Boot + Flyway + PostgreSQL
Выполнил Цвиль Павел ФИТ-231
---
## Статус
- Приложение запускается  
- Все эндпоинты реализованы  
- Тестирование в Postman пройдено
---
## Что сделано

### Задание 1: PostgreSQL и Docker
- Настроен Docker Compose с PostgreSQL
- Подключение к БД в `application.yml`

### Задание 2: Flyway миграции
- 5 миграций: users, restaurants, dishes, orders, order_dishes
- Автоматическое применение при запуске

### Задание 3: JPA сущности и связи
- `User`, `Restaurant`, `Dish`, `Order` + `OrderStatus`
- Связи: `@OneToMany`, `@ManyToOne`, `@ManyToMany`

### Задание 4: Эндпоинты по spec.yaml
- CRUD для ресторанов (`/api/v1/restaurants`)
- CRUD для блюд (`/api/v1/dishes`)
- Получение меню ресторана (`GET /restaurants/{id}/dishes`)
- Создание и получение заказов (`/api/v1/orders`)

### Задание 5: @EntityGraph
- Оптимизация запросов: загрузка заказа с пользователем и блюдами
- Загрузка меню ресторана
---