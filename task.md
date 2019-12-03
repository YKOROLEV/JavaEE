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