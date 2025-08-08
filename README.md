# 🛒 Store Inventory Manager - Java CRUD App

This is a simple **Java CRUD project** developed using **JPA (Java Persistence API)** and **Hibernate**, designed to manage the **inventory of a store selling electronics and clothing**.

This project is part of my learning journey in the **Web Application Development (DAW)** program.

## 🧠 About the Project

The application allows users to:

- Add new products (electronics or clothing)
- Update product details (price, stock, category)
- Delete products from the inventory
- View the current inventory list
- Search products by name or category

All data is persisted in a **MariaDB** database using **JPA + Hibernate**.

## ⚙️ Technologies Used

- Java 17+
- JPA (Jakarta Persistence)
- Hibernate ORM
- **MariaDB** (as relational database)
- Maven (for dependency management)
- IntelliJ IDEA / Eclipse (recommended IDE)

## 💡 Purpose

This project was created to:

- Practice building a CRUD application in Java
- Understand object-relational mapping (ORM) concepts
- Work with JPA and Hibernate for database persistence
- Apply layered architecture principles (DAO, Entity, Service)
- Gain hands-on experience with relational database operations

## 🏁 Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/store-inventory-jpa.git
2. **Configure the database connection in `persistence.xml:`**
   ````bash
   <property name="javax.persistence.jdbc.url" value="jdbc:mariadb://localhost:3306/store_inventory"/>
   <property name="javax.persistence.jdbc.user" value="your_db_user"/>
   <property name="javax.persistence.jdbc.password" value="your_db_password"/>
3. **Build the project using Maven:**
   ````bash
   mvn clean install
4. **Run the application via your IDE or terminal.**

## 🔮 Future Improvements
- Implement product categories with subcategories
- Add stock alerts for low inventory
- Create a sales module
- Include reporting features (best-selling products, stock value)

*🎓 Built as part of my DAW studies to strengthen my skills in Java, database persistence, and backend application architecture.*

