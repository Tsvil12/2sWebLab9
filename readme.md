# Лабораторная работа №9: Кэширование (Redis + Spring Cache)

## Выполнил Цвиль Павел ФИТ-231

---
## Статус
- Redis добавлен в docker-compose  
- Зависимости подключены  
- @EnableCaching включён  
- @Cacheable и @CacheEvict расставлены в сервисах  
- CacheConfig настроен  
0 Приложение не запускается из-за бага Spring Boot / Kotlin (бины не внедряются)

## Что сделано

### 1. Redis в docker-compose
- Добавлен сервис redis:7-alpine
- Настроен healthcheck

### 2. Зависимости в pom.xml
- spring-boot-starter-data-redis
- spring-boot-starter-cache

### 3. Конфигурация
- @EnableCaching в главном классе
- Настройки в application.yml (Redis host/port, TTL)
- CacheConfig с JSON-сериализацией

### 4. Кэширование (@Cacheable)
- RestaurantService.findAll() -> кэш "restaurants"
- RestaurantService.findById(id) -> кэш "restaurants" с ключом #id
- DishService.findByRestaurantId(restaurantId) -> кэш "dishes"

### 5. Инвалидация (@CacheEvict)
- create/update/delete ресторана -> evict all entries
- create/update/delete блюда -> evict all entries

## Проблемы при выполнении

1. **Конфликт версий** — Spring Boot 3.1.5 и Kotlin 2.1.10 несовместимы с Java 25
2. **Бины не внедряются** — Spring не видит RestaurantService, хотя аннотации расставлены правильно
3. **Testcontainers не работает** — Docker не подключается (ошибка npipe)
4. **Redis не кэширует** — из-за бага с внедрением зависимостей

## Попытки решения
- Переключение на Java 17
- Добавление @EnableJpaRepositories
- Пересборка с clean compile
- Запуск через Docker Compose
- Установка разных версий Kotlin 
- Переключение между версиями Java
- Настройка DOCKER_HOST переменных

## Но все-равно, спустя около трех часов пыток, ничего не запустилось (

