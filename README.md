## TASK 1
Написать **CRUD** приложение
Использовать `Tomcat, maven, JSP, сервлеты, JDBC`\
Должен быть класс `User` с произвольными полями (id, name и т.п.)\
В приложении должна быть страница на которую выводятся все юзеры с возможностью добавлять, удалять и изменять юзера
Конфигурация сервлетов через аннотации.

> [committed task1](https://github.com/YKOROLEV/preproject1/tree/task1) 

## TASK 2
Создать интерфейс `UserDAO`
Заимплементировать `UserJdbcDAO от UserDAO`\
Создать реализацию `UserHibernateDAO` и реализовать проект на ней.

> [committed task2](https://github.com/YKOROLEV/preproject1/tree/task2) 

## TASK 3
1) Прочитать про паттерн [Фабрика](https://habr.com/ru/post/465835/)
2) В ресурсах создать файл **.property**, который содержит ключ “тип дао” и значение, соответствующее одномы из дао.
3) `UserDaoFactory` - фабрика которая возвращает реализацию дао 	на основе файла property.
4) `DBHelper` - синглтон, у него есть два метода `getConnection` и `getConfiguration` которые отдают Connection для jdbc dao и Configuration для hibernate dao соответственно.
5) Сервис - синглтон

> [committed task3](https://github.com/YKOROLEV/preproject1/tree/task3) 

## TASK 4

1) Добавить классу **User** поле **role** типа String, которое будет принимать значения user или admin
2) На индексной странице сделать форму логина в которую будет вводиться логин и пароль юзера
3) Добавить сервлет фильтр,  который после логина будет отправлять пользователей с ролью user на страницу /user , а пользователей с ролью admin на страницу /admin на которой будет таблица всех юзеров с возможностью их изменения (она уже сделана)
4) Сделать так чтобы user не мог заходить на все страницы которые начинаются на /admin (т.e. Не мог видеть таблицу пользователей или производить с ней какие-либо действия)
5) Admin может заходить на /user
6) Все запросы админа должны начинаться с /admin/

> [committed task4](https://github.com/YKOROLEV/preproject1/tree/task4) 