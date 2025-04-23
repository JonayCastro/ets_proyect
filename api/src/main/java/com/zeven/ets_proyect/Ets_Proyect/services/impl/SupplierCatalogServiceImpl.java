package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.dto.SneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.SneakersResponseDTO;
import com.zeven.ets_proyect.Ets_Proyect.services.SupplierCatalogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Service
public class SupplierCatalogServiceImpl implements SupplierCatalogServices {

    @Value("${supplier.base.url}")
    private String baseUrl;

    @Autowired
    private WebClient webClient;

    @Override
    public SneakersResponseDTO getSneakerList() {
        return webClient.get()
                .uri(this.baseUrl)
                .retrieve()
                .bodyToMono(SneakersResponseDTO.class)
                .block();
    }

    @Override
    public SneakerDTO getSneakerByCollectionId(Long collectionId) {
        return null;
    }
}
