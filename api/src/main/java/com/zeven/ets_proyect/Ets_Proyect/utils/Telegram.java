package com.zeven.ets_proyect.Ets_Proyect.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Telegram {

    @Value("${telegram.bot.user.prefix}")
    private String userContactPrefix;

    @Value("${telegram.bot.url.bot.register}")
    private String botRegisterBaseUrl;

    public String generateContact(String userName){
        return this.userContactPrefix + userName;
    }

    public String generateRegisterUrl(String userContact){
        return this.botRegisterBaseUrl + userContact;
    }
}
