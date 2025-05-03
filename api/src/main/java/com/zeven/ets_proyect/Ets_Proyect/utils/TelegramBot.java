package com.zeven.ets_proyect.Ets_Proyect.utils;

import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.MessageFormat;

public class TelegramBot extends TelegramLongPollingBot {

    private final String token;
    private final String userName;
    private final UserService userService;

    public TelegramBot(String token, String userName, UserService userService) {
        this.token = token;
        this.userName = userName;
        this.userService = userService;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (messageText.startsWith("/start")) {
                String[] parts = messageText.split(" ");
                if (parts.length > 1) {
                    String contact = parts[1];
                    userService.updateChatId(contact, chatId);

                    SendMessage confirmation = new SendMessage();
                    confirmation.setChatId(chatId.toString());
                    String msg = MessageFormat.format("✅ Notificaciones activadas para tu cuenta {0}.", contact.replace("ets_proyect_", ""));
                    confirmation.setText(msg);

                    try {
                        execute(confirmation);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void sendMessage(Long chatId, String texto) {
        SendMessage mensaje = new SendMessage();
        mensaje.setChatId(chatId.toString());
        mensaje.setText(texto);
        try {
            execute(mensaje);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Error enviando mensaje a Telegram", e);
        }
    }
}
