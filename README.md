# Classroom

### Full-stack web application for classroom management.

Classroom is a powerful tool that enables universities to manage their academic operations from a single,
centralized platform, providing an efficient and effective solution for academic management.

It is a comprehensive **web application** developed using **Java** and **Spring** framework together with **Angular**,
designed to provide a centralized management system for universities.
With Classroom, students, teachers, and deans can login to their respective accounts and access a wide range of
functionalities to efficiently manage academic activities.
The application is specifically designed to streamline and simplify administrative tasks.

**Feature ideas:**

* course scheduling,
* grade tracking,
* attendance management,
* assignment submission,
* chat for students to interact with teachers and peers, allowing them to collaborate on projects,
  discuss coursework, and receive feedback.

## HOW TO RUN APP LOCALLY:

### 1. Clone the project

```bash
  git clone https://github.com/lyingparachute/Classroom-mono.git
```

Go to project directory

```bash
  cd Classroom-mono
```

### 2. Build project and perform tests

* Open terminal in project directory
* Type:
  `mvn clean install -DskipTests`

### 3. Start application server with docker-compose

```bash
docker compose up --build
```

***note** - it might take a while to pull and build docker images*

* go to endpoints and test app:
    * <a href="http://localhost/" target="_blank">Welcome page</a>

      `http://localhost`
    * <a href="http://localhost/dashboard" target="_blank">Dashboard</a>

      `http://localhost/dashboard`
* Finish running app

  ```
  press CTRL+C in terminal
  ``` 

### 4. Run app - second time

* START APP
  ```bash
  docker-compose start
  ```
* STOP APP
   ```bash
  docker-compose stop
  ```
* REMOVE NETWORK
  ```bash
  docker-compose down
  ```

## Alternative way of running app locally

Create docker image and run project with IntelliJ:

```bash
docker run -p 3307:3306 --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=classroom --rm -d mysql
```

## HOW TO USE APP:

#### Create new account

* Register using email and password
* Sign In to the dashboard and use app

  #### OR

#### Use already existing accounts

1) Student account:
   * Login: student
   * Password: student
2) Teacher account:
   * Login: teacher
   * Password: teacher
3) Dean account:
   * Login: dean
   * Password: dean
4) Admin account:
   * Login: admin
   * Password: admin

