package me.bev0802.telegrambot.config;

import me.bev0802.telegrambot.controller.TelegramBotController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.Webhook;

@Configuration
public class TelegramBotConfig {

    @Bean
    public TelegramLongPollingBot telegramBot(TelegramBotController telegramBotController) {
        return new TelegramLongPollingBot() {
            @Override
            public void onUpdateReceived(Update update) {
                telegramBotController.onUpdateReceived(update);
            }

            @Override
            public String getBotUsername() {
                return "@bev0802_tictactoe_java_bot"; // Заменить на имя бота
            }

            @Override
            public String getBotToken() {
                return "7464652750:AAHEG0CVcIZkBmTBjrSE1rAsmKZhfun8Z0M"; // Заменить на токен бота
            }
        };
    }
}
