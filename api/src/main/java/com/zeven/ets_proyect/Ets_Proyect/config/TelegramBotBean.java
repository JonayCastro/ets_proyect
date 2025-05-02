package com.zeven.ets_proyect.Ets_Proyect.config;

import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import com.zeven.ets_proyect.Ets_Proyect.utils.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotBean {

    @Bean
    public TelegramBot telegramBot(
            @Value("${telegram.bot.token}") String token,
            @Value("${telegram.bot.username}") String userName,
            UserService userService
    ) {
        return new TelegramBot(token, userName, userService);
    }
}
