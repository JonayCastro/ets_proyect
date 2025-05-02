package com.zeven.ets_proyect.Ets_Proyect.utils;

import com.zeven.ets_proyect.Ets_Proyect.dto.FavoriteChangedDTO;
import com.zeven.ets_proyect.Ets_Proyect.services.FavoriteService;
import com.zeven.ets_proyect.Ets_Proyect.services.impl.SneakersCatalogService;
import com.zeven.ets_proyect.Ets_Proyect.services.impl.TelegramNotificationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledAction {

    private final TelegramNotificationService telegramNotificationService;
    private final FavoriteService favoriteService;
    private final SneakersCatalogService sneakersCatalogService;

    ScheduledAction(TelegramNotificationService telegramNotificationService, FavoriteService favoriteService, SneakersCatalogService sneakersCatalogService){
        this.telegramNotificationService = telegramNotificationService;
        this.favoriteService = favoriteService;
        this.sneakersCatalogService = sneakersCatalogService;
    }

    @Scheduled(fixedDelay = 30000 )
    public void checkSneakerPrices(){
        this.sneakersCatalogService.getSupplierData();
        this.sneakersCatalogService.getProducts();

        List<FavoriteChangedDTO> favoriteChangedDTOList = this.favoriteService.getFavoriteChanged();
        if (!favoriteChangedDTOList.isEmpty()){
            this.telegramNotificationService.sendNotification(favoriteChangedDTOList);
        }

    }
}
