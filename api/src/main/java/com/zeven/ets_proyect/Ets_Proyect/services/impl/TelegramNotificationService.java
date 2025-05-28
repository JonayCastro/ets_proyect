package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.OffersDTO;
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
public class TelegramNotificationService implements NotificationServices <OffersDTO> {

    @Value("${supplier.sneaker.base.product.url}")
    private String baseSupplierUrl;

    private final TelegramBot telegramBot;

    @Override
    public void sendNotification() {

    }

    @Override
    public void sendNotification(List<OffersDTO> offersDTO) {
        /**
         * This method sends notifications to users about changes in their favorite sneakers.
         * It iterates through the list of OffersDTO objects and builds a message for each user.
         * The messages are then sent to the respective users using the TelegramBot service.
         */
        Map<Long, String> messages = new HashMap<>();

        offersDTO.forEach((favoriteChanged) -> {
            Long chatId = favoriteChanged.getChatId();
            String message = this.buildMessage(favoriteChanged);
            messages.put(chatId, message);
        });

        messages.forEach(this.telegramBot::sendMessage);

    }

    @Override
    public String buildMessage(OffersDTO offersDTO) {
        /**
         * This method builds a message for a user based on the information in the OffersDTO object.
         * It retrieves the user's name, sneaker name, old price, new price, and link to the sneaker.
         * The message is formatted using MessageFormat to create a user-friendly notification.
         *
         * @param offersDTO The OffersDTO object containing the information for the message.
         *
         * @return A formatted message string for the user.
         */
        String userName = offersDTO.getUserName();
        String sneakerName = offersDTO.getSneakerName();
        String link = offersDTO.getLink();
        String oldPrice = Integer.toString(offersDTO.getOldPrice());
        String newPrice = Integer.toString(offersDTO.getNewPrice());

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
