# GuessTheWord

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

_Add screenshots here (login, game screen, reports, etc.)_

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
