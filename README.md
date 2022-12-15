# JavaFX Employee Management Application

Client application for MySQL database made with JavaFX and MySQL 
applying functional programming principles.
It's a simple employee management system that allows you to add, edit, 
and delete employees from the database.

By using a custom architecture, the application is easy to maintain 
and extend.

## Scene Overview

Root scene is notified via callbacks of its children nodes how it should behave on each one. Note the top menu bar:

### Login scene

![image](https://user-images.githubusercontent.com/66980937/207858793-80891e41-7a3e-4894-9dbc-7f76c34356b9.png)

*Users must log in with their administration credentials stored in the database*

### Welcome scene
![image](https://user-images.githubusercontent.com/66980937/207858977-c94395b5-1acd-41e1-9b10-95c47a325eed.png)

*Administrators are greeted by this scene. Could be extended with general data of the DB.*

### Administration / Management scene
![image](https://user-images.githubusercontent.com/66980937/207859211-bcd0f56e-99d2-401d-819e-57311118016e.png)

*This scene allows administrators to add, edit, find and delete employees from the DB. It's bottom log console notifies every change or error on the backend side.*

## Dialog Overview

Every form dialog inherits a FormDialog class to validate its data. Their result converter is then assigned to their result callback, which indicates how the dialog should act after being ended.

### Add user dialog
![image](https://user-images.githubusercontent.com/66980937/207860000-b1ea29ce-a7c3-49da-8aca-9ef9598cdd8b.png)

### Delete user dialog (generic Alert)
![image](https://user-images.githubusercontent.com/66980937/207860603-4e0eac9d-c923-402c-8462-a15b7ec02d06.png)

### Find user dialog
![image](https://user-images.githubusercontent.com/66980937/207860660-bfb92a84-179e-43ef-9fc5-3c3a096c5913.png)

### Update user department number dialog
![image](https://user-images.githubusercontent.com/66980937/207860729-998bd745-8d7a-43ee-a843-cf588bf15f09.png)

## Getting Started

Make sure you have cloned the repository and have Java 17 installed and 
all Maven dependencies are up-to-date.

You'll also need a Docker container running with mySQL and an accepted database loaded on it (careful, this app doesn't check if the database is compatible, you may end with unexpected exceptions!)

The image used for the container running the server is available on https://hub.docker.com/r/ubuntu/mysql.

The sample database data was loaded from https://github.com/datacharmer/test_db (the number of employees was limited to 1,000, as if you try to load the 300,000 employees on the sample script, the application will crash when loading the table!)

## Credits

https://www.flaticon.com, for providing the main app icon and almost all icons of the app
