# Отчёт по итогам тестирования
## Краткое описание
В результате работы было выполнено автоматизацированное тестирование сервиса с двумя формами оплаты, покупки и покупки в кредит, 
взаимодействующих с СУБД и API Банка.

Приложение тестировалось по двум способам оплаты:

1. Оплата по дебетовой карте  
2. Выдача кредита по данным банковской карты    

Заявлена поддержка двух СУБД:  
MySQL  
PostgreSQL  

Приложение корректно работает с MySQL и с PostgreSQL.  

## Количество тест-кейсов  
Всего выполнено 50 автоматизированных тест-кейсов

## Процент успешных и не успешных тест-кейсов  

38 успешных тестов – что составляет 76 %  
12 не успешных тестов – что составляет 24 %

## Общие рекомендации
Пофиксить выявленные баги. Указаны в issue;    
Визуально обозначить какая форма открыта, оплата или оплата в кредит;    
Обозначить поля звездочкой как обязательные к заполнению;  
Валидировать поля на этапе не корректного заполнения, а не после нажатия на кнопу "Продолжить".  

## Отчёты:

Отчёт Gradle

![Screenshot_424](https://github.com/anna270892/diplom/assets/142071185/21e7c7c5-c977-453d-a93a-9fd57c3d2786)

Отчёт Allure

![Screenshot_425](https://github.com/anna270892/diplom/assets/142071185/88c03b1b-f263-486b-8dd2-7b306ba9de0f)
