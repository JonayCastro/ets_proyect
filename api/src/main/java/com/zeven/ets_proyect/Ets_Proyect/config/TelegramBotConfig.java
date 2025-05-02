package com.zeven.ets_proyect.Ets_Proyect.config;

import com.zeven.ets_proyect.Ets_Proyect.utils.TelegramBot;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@RequiredArgsConstructor
public class TelegramBotConfig {

    private final TelegramBot telegramBot;

    @PostConstruct
    public void startBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
        } catch (Exception e) {
            throw new RuntimeException("Error iniciando el bot de Telegram", e);
        }
    }
}
