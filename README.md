# 🛒 Simple E-commerce API

A simple RESTful E-commerce backend built using **Java (Spring Boot)** to manage products, cart, and orders with role-based JWT authentication.

---

## ✅ Features

* JWT-based authentication (`Customer` and `Admin` roles)
* Product listing with **pagination** and **search**
* Add/update/remove items from **cart**
* Place orders from **cart**
* Admin can manage products (add, update, delete)

---

## 🧪 API Access

* Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🚀 Tech Stack

* Java 21
* Spring Boot
* Spring Security (JWT)
* PostgreSQL
* Maven
* Swagger (OpenAPI)

---

## ▶️ How to Run Locally

### 1. Clone the repository

```bash
git clone https://github.com/Yash-Zade/ecommerce-api.git
cd ecommerce-api
```

### 2. Configure `application.properties`

Open `src/main/resources/application.properties` and update the following:

```properties
# Database Config
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# JWT Secret
jwt.secret=your_jwt_secret_key
```

> ✅ Ensure PostgreSQL is running and the `ecommerce` database is created.

### 3. Run the application

```bash
./mvnw spring-boot:run
```

Or, if Maven is installed:

```bash
mvn spring-boot:run
```

---

## 🙋‍♂️ Author

**Yash Zade**
🔗 [https://github.com/Yash-Zade](https://github.com/Yash-Zade)

---
