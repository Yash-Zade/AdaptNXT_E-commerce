
# 🛒 Simple E-commerce API

A basic E-commerce REST API built using **Java (Spring Boot)** for managing products, cart, and orders with role-based authentication.

---

## ✅ Features

* User roles: `Customer` and `Admin` (secured with JWT)
* Product listing with pagination and search
* Cart management (add, update, remove)
* Place orders from cart
* Admin can manage products (add, update, delete)

---

## 🧪 API Access

* Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🚀 Tech Stack

* Java 21
* Spring Boot
* JWT (Spring Security)
* MySQL
* Docker
* Swagger (OpenAPI)

---

## ⚙️ Run with Docker

```bash
docker build -t yashzade/ecommerce-api .
docker run -p 8080:8080 yashzade/ecommerce-api
```


## 🙋‍♂️ Author

**Yash Zade**
[(https://github.com/Yash-Zade)](https://github.com/Yash-Zade)

---

Let me know if you want to include credentials for testing (like a default admin user).
