##### GuessTheWord
---

##GuessTheWord is a JavaFX-based word guessing game built with **Java 21**, **MySQL**, and **JDBC**. The game allows users to register, log in, and play a daily word guessing challenge. Admin users can generate reports on user activity and game performance.  

---

## Tech Stack

### Frontend (Game UI):
- JavaFX (Java 21)

### Backend:
- Core Java (OOP, JDBC)  
- Apache Maven (build tool)  
- BCrypt (password hashing)  

### Database:
- MySQL 8  
- Schema and seed data included in **schema.sql**

---

## Screenshots

<img width="490" height="655" alt="1" src="https://github.com/user-attachments/assets/19195a50-2671-47c4-b363-7b3f2cf127ea" />
<img width="492" height="658" alt="2" src="https://github.com/user-attachments/assets/14f36b19-06f9-4f73-87da-ec0e5bd9b3b1" />
<img width="1790" height="932" alt="3" src="https://github.com/user-attachments/assets/72b5f29f-0936-475a-821f-5cb23fad23f3" />
<img width="1917" height="767" alt="4" src="https://github.com/user-attachments/assets/1ebf2366-b53a-4c20-b42c-156f4504b3fd" />

---

## Features

- **User Registration & Login**  
  - Username must be at least 5 characters, include both uppercase and lowercase letters  
  - Password must be at least 5 characters, include letters, numbers, and one special character ($, %, *, @)  

- **Word Guessing Game**  
  - Players can attempt up to **3 games per day**  
  - Each game allows **5 guesses**  
  - Letter feedback:  
    - Correct letter in correct position → **Green**  
    - Correct letter in wrong position → **Orange**  
    - Incorrect letter → **Grey**  

- **Game Flow**  
  - Win → congratulatory message  
  - Lose → better luck next time  

- **Database Tracking**  
  - Stores words, guesses, and session details with timestamps  

- **Admin Reports**  
  - **Daily report**: number of users and number of correct guesses  
  - **User report**: date, words tried, correct guesses  

---

## Local Development

### 1. Setup Database

Run the provided **schema.sql** file in your MySQL server:

```bash
cd src/main/java/com/guesstheword/backend
mysql -u root -p < schema.sql
```

### 2. Configure Database Connection

Update your MySQL credentials in **DbConnection.java**:

```java
private static final String URL = "jdbc:mysql://localhost:3306/guess_the_word";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```
### 3. Run the Application

From the project root:

```bash
mvn clean compile exec:java
```


