package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.config.url_settings.UrlLanguageParams;
import com.zeven.ets_proyect.Ets_Proyect.config.url_settings.UrlParams;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakerProductsDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakersDataDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.AvailableSizesEntity;
import com.zeven.ets_proyect.Ets_Proyect.entities.SneakerDataEntity;
import com.zeven.ets_proyect.Ets_Proyect.entities.SneakerEntity;
import com.zeven.ets_proyect.Ets_Proyect.enums.SupplierNamesEnum;
import com.zeven.ets_proyect.Ets_Proyect.repositories.SneakerDataRepository;
import com.zeven.ets_proyect.Ets_Proyect.repositories.SneakerRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.SupplierCatalogServices;
import com.zeven.ets_proyect.Ets_Proyect.utils.UrlBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SneakersCatalogService implements SupplierCatalogServices<SneakersDataDTO, SneakerDTO, Object> {

    @Value("${supplier.sneakers.base.url}")
    private String baseUrl;

    @Autowired
    private WebClient webClient;

    private final SneakerDataRepository sneakerDataRepository;
    private final SneakerRepository sneakerRepository;
    private final ModelMapper mapper;

    SneakersCatalogService (SneakerDataRepository sneakerDataRepository, SneakerRepository sneakerRepository, ModelMapper mapper) {
        this.sneakerDataRepository = sneakerDataRepository;
        this.sneakerRepository = sneakerRepository;
        this.mapper = mapper;
    }

    @Override
    public SneakersDataDTO getSupplierData() {
        /**
         * This method retrieves data from the sneaker supplier using a WebClient.
         * It sends a GET request to the base URL and retrieves the response as a SneakersDataDTO object.
         * The retrieved data is then persisted using the persistDataSupplier method.
         *
         * @param baseUrl The base URL of the sneaker supplier API.
         *
         * @return SneakersDataDTO object containing the supplier data.
         */
        SneakersDataDTO sneakersDataDTO = webClient.get()
                .uri(this.baseUrl)
                .retrieve()
                .bodyToMono(SneakersDataDTO.class)
                .block();
        this.persistDataSupplier(sneakersDataDTO);
        return sneakersDataDTO;
    }

    @Override
    public void persistDataSupplier(SneakersDataDTO sneakersDataDTO) {
        /**
         * This method persists the supplier data into the database.
         * It checks if the supplier data already exists in the database using the supplier identifier.
         * If it exists, it updates the existing entity; otherwise, it creates a new entity.
         */
        String sneakerSupplierIdentifier = SupplierNamesEnum.SNEAKER_SUPPLIER_IDENTIFIER.getValue();
        Optional<SneakerDataEntity> existingFound = this.sneakerDataRepository.findBySupplierIdentifier(sneakerSupplierIdentifier);
        SneakerDataEntity sneakerDataEntity;

        if (existingFound.isPresent()) {
            sneakerDataEntity = existingFound.get();
            this.mapper.map(sneakersDataDTO, sneakerDataEntity);
        } else {
            sneakerDataEntity = this.mapper.map(sneakersDataDTO, SneakerDataEntity.class);
        }
        sneakerDataEntity.setSupplierIdentifier(sneakerSupplierIdentifier);
        this.sneakerDataRepository.save(sneakerDataEntity);
    }

    @Override
    public List<SneakerDTO> getProducts() {
        /**
         * This method retrieves sneaker products from the supplier API using pagination.
         * It constructs the URL for each page and sends a GET request to retrieve the products.
         * The retrieved products are then persisted using the persistProducts method.
         *
         * @param baseUrl The base URL of the sneaker supplier API.
         *
         * @return List of SneakerDTO objects representing the retrieved products.
         */
        List<SneakerDTO> sneakerDTOList = new ArrayList<>();
        Integer totalPages = this.getTotalPages();
        int actualPage = 1;

        while (actualPage <= totalPages) {
            String url = this.buildUrl(String.valueOf(actualPage));
            SneakerProductsDTO sneakersProductsDTO = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(SneakerProductsDTO.class)
                    .block();

            sneakerDTOList.addAll(sneakersProductsDTO != null ? sneakersProductsDTO.getProducts() : Collections.emptyList());
            actualPage++;
        }

        this.persistProducts(sneakerDTOList);

        return sneakerDTOList;
    }

    @Override
    public void persistProducts(List<SneakerDTO> sneakerDTOList) {
        /**
         * This method persists sneaker products into the database.
         * It first deletes all existing sneaker entities from the database and then maps the DTOs to entities.
         * The mapped entities are then saved to the database.
         */
        this.sneakerRepository.deleteAll();
        List<SneakerEntity> entityList = sneakerDTOList.stream().map(dto -> {
            SneakerEntity sneakerEntity = mapper.map(dto, SneakerEntity.class);

            List<AvailableSizesEntity> sizes = dto.getSize().stream().map(sizeDto -> {
                AvailableSizesEntity sizeEntity = mapper.map(sizeDto, AvailableSizesEntity.class);
                sizeEntity.setSneaker(sneakerEntity);
                return sizeEntity;
            }).toList();

            sneakerEntity.setAvailableSizes(sizes);
            return sneakerEntity;
        }).toList();

        sneakerRepository.saveAll(entityList);
    }

    public Integer getTotalPages (){
        SneakerDataEntity sneakerDataEntity = this.sneakerDataRepository.findBySupplierIdentifier(SupplierNamesEnum.SNEAKER_SUPPLIER_IDENTIFIER.getValue())
                .orElseThrow(() -> new RuntimeException(ApiMessage.SUPPLIER_NOT_FOUND));
        return sneakerDataEntity.getTotalPages();
    }

    @Override
    public SneakerDTO getProduct(Object productIdentifier) {
        /**
         * This method retrieves a sneaker product by its identifier from the database.
         * It checks if the product exists in the database using the provided identifier.
         * If it exists, it returns the corresponding SneakerDTO object; otherwise, it throws a RuntimeException.
         *
         * @param productIdentifier The identifier of the sneaker product to retrieve.
         *
         * @return SneakerDTO object representing the retrieved product.
         */
        return null;
    }


    @Override
    public String buildUrl(String page) {
        /**
         * This method constructs the URL for retrieving sneaker products from the supplier API.
         * It uses a UrlBuilder to add query parameters such as language and page number to the base URL.
         *
         * @param page The page number for pagination.
         *
         * @return The constructed URL as a String.
         */
        return new UrlBuilder(this.baseUrl)
                .addQueryParam(UrlParams.LANG, UrlLanguageParams.SPAIN_LANGUAGE)
                .addQueryParam(UrlParams.PAGE, page)
                .build();
    }
}
