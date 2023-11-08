# MysqlDataMonitor
<p align="center" width="100%">
    <img width="33%" src="https://github.com/Pavel7171/MysqlDataMonitor/assets/106702029/758f9061-aff2-4fb2-8ed4-cb4fc3ef17b7">
</p>

JavaFX приложение для просмотра :
- Баз
- Таблиц
- Данных в таблицах

Базы отображаются сразу при открытии приложения (с учетом конифгурирования учетных данных в классе ConnectSql)

Выбираем базу - сразу идет запрос на отобрадение таблиц.
Выбираем таблицу - отображаем данные в ней.
- Колонки формируются динамически, привязок к определенной таблице нет.

Запросы к SQL идут через "голый" JDBC
