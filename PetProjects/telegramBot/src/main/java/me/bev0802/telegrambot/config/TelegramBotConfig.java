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
                return "bot_username"; // Замените на имя вашего бота
            }

            @Override
            public String getBotToken() {
                return "bot_token"; // Замените на токен вашего бота
            }
        };
    }

//    @Bean
//    public void registerBot(TelegramLongPollingBot telegramBot) {
//        try {
//            Webhook webhook = new SomeWebhookImplementation();
//            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(SomeBotSessionClass, webhook);
//
//            telegramBotsApi.registerBot(telegramBot);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
}
