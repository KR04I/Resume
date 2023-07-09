Задание для разработки приложений базы данных:
БД «Гостиница»
База данных должна содержать сведения о следующих объектах:
Распределение номеров с указанием общего количества мест в номере, количества свободных мест и проживающих.
Гости - фамилия, имя, отчество, пол, адрес, дата рождения, номер паспорта, дата выдачи, учреждение, выдавшее паспорт, номер комнаты, номер стоянки, регистрационный номер автомобиля, дата въезда, дата выезда, список оказанных услуг (наименование услуги, количество, цена).
Адресные данные горничных и расписание их дежурств.

Work 1:
1)	Разработать структуру БД в соответствии с вариантом задания. Задания составлены так, что после приведения к 3NF база данных должна содержать минимум 3 таблицы, отсутствуют ограничения на создание дополнительных таблиц или представлений. 
2)	При помощи средств администрирования PostgreSQL создать пользователя, задать ему пароль и создать принадлежащую ему базу данных.
3)	Разработать программу на языке С++, использующую СУБД PostgreSQL для хранения данных, которая:
a.	При запуске программа подключается при помощи ODBC к базе данных, используя имя пользователя и пароль из п.2;
b.	Проверяет существование всех необходимых таблиц, в случае их отсутствия – создает;
c.	Для отображения разработанных классов использует один из архитектурных шаблонов: Data Mapper, Active Record, Table Data Gateway;
d.	Обслуживает пользовательский интерфейс (gui/cli, на выбор), содержащий набор операций в соответствии с семантикой варианта задания (предметной области), без привязки к реляционной модели. Запрещено использовать суррогатные первичные ключи в интерфейсе;

Work 2:
1.	Для базы данных из лабораторной работы №1 разработать набор классов-сущностей, хранящих свое состояние в базе данных, для описания отображения использовать любой доступный способ включая Fluent;
2.	Реализовать функции пользовательского интерфейса: просмотр/добавление/редактирование/удаление записи, поиск по всем полям, в том числе частичное совпадение. Запросы реализовать при помощи языка HQL

Work 3:
1)	Для базы данных из лабораторной работы №1 разработать приложение с web-интерфейсом на языке JavaScriptс использованием фреймворкаmeteor.
2)	Реализовать на стороне сервера авторизацию пользователей по логину и паролю, предусмотреть роль и web-интерфейс для управления пользователями приложения.
3)	Для хранения данных использовать MongoDB в режиме шардинга (1 сервер mongos, 2 сервера mongod). Аргументировать выбор стратегии разбиения ключа.

Work 4:
1)	Для разработанной объектной модели из лабораторной работы №2 классов-сущностей реализовать сохранение данных объектов в реляционной базе данных посредством разработанного протокола REST API, для чего выполнить разделение на клиентскую и серверную части приложения.
2)	Функции клиентской части приложения: интерфейс пользователя, REST API клиент, объектная модель предметной области.
3)	Функции серверной части приложения: REST API сервер, объектная модель предметной области, взаимодействие с СУБД (допускается использование любой ORM/ODBC/JDBC/libpq).
4)	Для обмена данными между клиентской и серверной частью приложения использовать JSON. Допускается использование gRPC только в комплекте с gRPC-Gateway.
5)	Использовать различные языки программирования в клиентской и серверной части приложения. Исключить из выбора пары Java + Kotlin, C + C++, JS framework + JS framework.
6)	Для создания интерактивной документации использовать Swagger UI на стороне серверной части приложения.
