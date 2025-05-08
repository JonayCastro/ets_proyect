package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteChangedDTO;
import com.zeven.ets_proyect.Ets_Proyect.services.NotificationServices;
import com.zeven.ets_proyect.Ets_Proyect.utils.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class TelegramNotificationService implements NotificationServices <FavoriteChangedDTO> {

    @Value("${supplier.sneaker.base.product.url}")
    private String baseSupplierUrl;

    private final TelegramBot telegramBot;

    @Override
    public void sendNotification() {

    }

    @Override
    public void sendNotification(List<FavoriteChangedDTO> favoriteChangedDTO) {
        Map<Long, String> messages = new HashMap<>();

        favoriteChangedDTO.forEach((favoriteChanged) -> {
            Long chatId = favoriteChanged.getChatId();
            String message = this.buildMessage(favoriteChanged);
            messages.put(chatId, message);
        });

        messages.forEach(this.telegramBot::sendMessage);

    }

    @Override
    public String buildMessage(FavoriteChangedDTO favoriteChangedDTO) {
        String userName = favoriteChangedDTO.getUserName();
        String sneakerName = favoriteChangedDTO.getSneakerName();
        String link = favoriteChangedDTO.getLink();
        String oldPrice = Integer.toString(favoriteChangedDTO.getOldPrice());
        String newPrice = Integer.toString(favoriteChangedDTO.getNewPrice());

        return MessageFormat.format(
                "Hola, {0}.\n⚠\uFE0F Tu zapatilla \"{1}\"\nha bajado de {2}€ a {3}€.\nVer aquí: {4}{5}",
                userName, sneakerName, oldPrice, newPrice, this.baseSupplierUrl, link
        );
    }

    @Override
    public String getUserContact() {
        return "";
    }
}
