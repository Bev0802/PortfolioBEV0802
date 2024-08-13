package me.bev0802.telegrambot.service;

import me.bev0802.telegrambot.repository.GameRepository;
import me.bev0802.telegrambot.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class GameManager {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    public void execute(SendMessage message) {
    }
}
