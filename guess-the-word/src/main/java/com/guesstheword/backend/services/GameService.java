package com.guesstheword.backend.services;

import com.guesstheword.backend.dao.GameSessionDAO;
import com.guesstheword.backend.dao.WordDAO;
import com.guesstheword.backend.models.GameSession;
import com.guesstheword.backend.models.Word;

import java.util.List;
import java.util.Random;

public class GameService {

    private final GameSessionDAO sessionDAO = new GameSessionDAO();
    private final WordDAO wordDAO = new WordDAO();
    private final Random random = new Random();

    public GameService() {}

    // This is the method called from LoginController
    public GameSession startGame(int userId) throws Exception {
        // 1️⃣ Check daily limit
        int gamesToday = sessionDAO.getGamesPlayedToday(userId);
        if (gamesToday >= 10) {
            throw new IllegalStateException("Daily limit reached. Try again tomorrow.");
        }

        // 2️⃣ Pick a random word
        List<Word> allWords = wordDAO.getAllWords();
        if (allWords.isEmpty()) {
            throw new IllegalStateException("No words available in the database.");
        }
        Word word = allWords.get(random.nextInt(allWords.size()));

        // 3️⃣ Create a new GameSession
        GameSession session = new GameSession(userId, word.getWordId(), "PROGRESS");
        sessionDAO.insertSession(session);

        return session;
    }
}
