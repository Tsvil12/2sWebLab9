# Лабораторная работа №6: Тестирование
## Выполнил Цвиль Павел ФИТ-231

---

## Статус
- Unit-тесты проходят  
- Интеграционные тесты написаны  
- Интеграционные тесты не запущены (проблемы с Docker на Windows)

## Что сделано

### 1. Тестовый профиль
- `src/test/resources/application.yaml`
- `ddl-auto: validate`, Flyway включён

### 2. Unit-тесты (MockK)
- `RestaurantServiceTest` — 4 теста
- Проверены: getById, create, исключения
- Результат: **BUILD SUCCESS**

### 3. Интеграционные тесты (Testcontainers + MockMvc)
- `RestaurantIntegrationTest` — код написан
- Покрыты позитивные и негативные сценарии
- Не запущены из-за проблем с Docker окружением

