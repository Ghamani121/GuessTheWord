<img width="1790" height="932" alt="3" src="https://github.com/user-attachments/assets/b14c8e16-06be-451d-a6a1-016239d847ba" /># GuessTheWord

GuessTheWord is a JavaFX-based word guessing game built with **Java 21**, **MySQL**, and **JDBC**. The game allows users to register, log in, and play a daily word guessing challenge. Admin users can generate reports on user activity and game performance.  

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


---<img width="1917" height="767" alt="4" src="https://github.com/user-attachments/assets/64cb97ff-f2a4-48a5-aab4-10765602d80a" />
<img width="1790" heig<img width="492" height="658" alt="2" src="https://github.com/user-attachments/assets/97f6fe58-68be-4d00-a230-bb29f5e64623" />
ht="932" alt="3" src="https://github.com/user-attachments/assets/f20976cd-4bb3-4f44-9fe7-382b11136ddd" />
<img width="490" height="655" alt="1" src="https://github.com/user-attachments/assets/69b16d4b-0e08-448d-9f32-48a6f393dede" />

<img width="1917" height="767" alt="4" src="https://github.com/user-attachments/assets/2e191243-a65a-41d8-bc7c-4985409afa8b" />
<img width="1790" height="932" alt="3" src="https://github.com/user-attachments/assets/99515db1-fd62-41b2-aa26-34481bd6f34a" />
<img width="490" height="655" alt="1" src="https://github.com/user-attachments/assets/2864c658-76c8-4b80-a3e9-7bb67c0ff5b9" />
<img width="492" height="658" alt="2" src="https://github.com/user-attachments/assets/550e9a4a-ba52-4756-a059-9aafebbd94da" />
<img width="490" height="655" alt="1" src="https://github.com/user-attachments/assets/d8e5e464-3a25-41e0-99d7-4b1597b44782" />
<img width="492" height="658" alt="2" src="https://github.com/user-attachments/assets/0e7b62d8-edfa-474b-a93a-578797ac6339" />
<img width="1917" height="767" alt="4" src="https://github.com/user-attachments/assets/fa43372d-c443-4f50-9665-a830c7c3e810" />
<img width="1790" height="932" alt="3" src="https://github.com/user-attachments/assets/44b0e502-e1fa-4c59-8f4a-9880f864ee23" />

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


