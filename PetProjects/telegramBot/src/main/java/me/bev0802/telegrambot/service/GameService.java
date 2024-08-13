package me.bev0802.telegrambot.service;

import me.bev0802.telegrambot.repository.GameRepository;
import me.bev0802.telegrambot.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;
    public void startGame(long chatId) {
    }

    public String processMove(String messageText, long chatId) {
        return "Your move: " + messageText;
    }
}
