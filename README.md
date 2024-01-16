# Дипломный проект "Тестировщик ПО"
##Описание проекта
Проект - сервис покупки тура, взаимодейтсвующий с СУБД и API Банка.
В базе данных хранится информация каким образом был совершен платеж, в каком статусе, успешный ли был запрос
В рамках проекта необходимо автоматизировать позитивные и негативные сценарии покупки тура двумя способами:
-оплата картой через Payment Gate
-покупка в кредит через Credit Gate

## Запуск проекта на локальном ПК
### Первый этап(установки)
-Установить и запустить IntelliJ IDEA;
-Установить браузер Google Chrome;
-Создать аккаунт на GitHub;
-Установать и запустить Docker Desktop;
-Скопировать репозиторий с Github;
-Открыть проект в IntelliJ IDEA

### Второй этап(запуск программы)
-Запустить MySQL, PostgreSQL, NodeJS командой:  
docker-compose up  
-Запустить джарник база MySQL    
java -jar -"Dspring.datasource.url=jdbc:mysql://localhost:3306/app" artifacts/aqa-shop.jar  
-Запустить джарник база PostgreSQL
java -jar -"Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" artifacts/aqa-shop.jar  
-Открыть сервис в браузере по адресу http://localhost:8080/ (приложение запущено)  

### Третий этап(запуск тестов)  
-Запустить тесты на базе MySQL  
./gradlew clean test -"Ddb.url=jdbc:mysql://localhost:3306/app"

## Лицензии ?