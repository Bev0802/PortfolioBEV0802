package me.bev0802.telegrambot.service;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    public void startGame(long chatId) {
    }

    public String processMove(String messageText, long chatId) {
        return "Your move: " + messageText;
    }
}
