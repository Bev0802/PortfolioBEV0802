package me.bev0802.telegrambot.controller;

import me.bev0802.telegrambot.service.GameService;
import me.bev0802.telegrambot.service.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotController {

    @Autowired
    private GameManager gameManager;

    @Autowired
    private GameService gameService;

    // Метод для обработки входящих сообщений
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            handleUserMessage(messageText, chatId);
        }
    }

    private void handleUserMessage(String messageText, long chatId) {
        // Пример обработки команды /start
        if (messageText.startsWith("/start")) {
            sendResponse(chatId, "Welcome to the Tic-Tac-Toe bot! Type /play to start a new game.");
        } else if (messageText.startsWith("/play")) {
            // Логика начала игры
            gameService.startGame(chatId);
            sendResponse(chatId, "Game started! Type your move.");
        } else {
            // Обработка хода игрока
            String response = gameService.processMove(messageText, chatId);
            sendResponse(chatId, response);
        }
    }

    private void sendResponse(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        gameManager.execute(message);

//        try {
//            gameManager.execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
}