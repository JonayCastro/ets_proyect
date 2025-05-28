package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.dto.FilterDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.FavoriteSneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.OffersDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakersDataDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import com.zeven.ets_proyect.Ets_Proyect.entities.SneakerEntity;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import com.zeven.ets_proyect.Ets_Proyect.repositories.FavoriteSneakersRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.FavoriteService;
import com.zeven.ets_proyect.Ets_Proyect.services.SupplierCatalogServices;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteSneakersRepository favoriteSneakersRepository;
    private final ModelMapper mapper;
    private final UserService userService;
    private final SupplierCatalogServices<SneakersDataDTO, SneakerDTO, SneakerEntity> supplierCatalogServices;

    FavoriteServiceImpl(FavoriteSneakersRepository favoriteSneakersRepository,
                        ModelMapper mapper,
                        UserService userService,
                        SupplierCatalogServices<SneakersDataDTO, SneakerDTO, SneakerEntity> supplierCatalogServices){
        this.favoriteSneakersRepository = favoriteSneakersRepository;
        this.mapper = mapper;
        this.userService = userService;
        this.supplierCatalogServices = supplierCatalogServices;
    }

    @Override
    public FavoriteSneaker getFavoriteById(Long favoriteId) {
        /**
         * This method retrieves a favorite sneaker by its ID from the repository.
         * If the favorite sneaker is not found, it throws a RuntimeException with a message indicating that the favorite was not found.
         * The method returns the found FavoriteSneaker object.
         *
         * @param favoriteId The ID of the favorite sneaker to retrieve.
         *
         * @return The FavoriteSneaker object corresponding to the provided ID.
         */
        return this.favoriteSneakersRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException(ApiMessage.FAVORITE_NOT_FOUND));
    }

    @Override
    public void addFavoriteBySneakerId(Long sneakerStoredId) {
        /**
         * This method adds a favorite sneaker to the user's list of favorite sneakers.
         * It retrieves the user from the database using the user ID from the security context.
         * Then, it maps the FavoriteSneakerDTO to a FavoriteSneaker entity and saves it to the database.
         */
        try {
            Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
            User user = this.userService.findUserById(userId);
            SneakerEntity sneakerEntity = this.supplierCatalogServices.findEntityById(sneakerStoredId);
            FavoriteSneaker favoriteSneaker = this.mapper.map(sneakerEntity, FavoriteSneaker.class);
            favoriteSneaker.setUser(user);
            this.favoriteSneakersRepository.save(favoriteSneaker);
        } catch (Exception e) {
            System.out.println(ApiMessage.USER_NOT_FOUND);
        }
    }

    @Override
    public void deleteFavoriteById(Long favoriteSneakerId) {
        /**
         * This method deletes a favorite sneaker from the user's list of favorite sneakers.
         * It retrieves the favorite sneaker from the database using the provided ID and deletes it.
         */

        FavoriteSneaker favoriteSneaker = this.favoriteSneakersRepository.findById(favoriteSneakerId).orElseThrow(() ->
                new RuntimeException(ApiMessage.FAVORITE_NOT_FOUND));
        this.favoriteSneakersRepository.delete(favoriteSneaker);
    }

    @Override
    public List<OffersDTO> getFavoriteChanged() {
        /**
         * This method retrieves a list of favorite sneakers that have been changed from the repository.
         * It returns a list of OffersDTO objects, which contain information about the changed favorites.
         * @return A list of OffersDTO objects representing the changed favorites.
         */
        return this.favoriteSneakersRepository.findTopFavoriteChanged();
    }

    @Override
    public List<OffersDTO> getFavoriteChangedByName(FilterDTO filterDTO) {
        String brandName = filterDTO.getBrandFilter();
        return this.favoriteSneakersRepository.findFavoriteChangedByName(brandName);
    }

    @Override
    public List<OffersDTO> getFavoriteChangedByPriceRange(FilterDTO filterDTO) {
        Integer minPrice = filterDTO.getMinPriceFilter();
        Integer maxPrice = filterDTO.getMaxPriceFilter();
        return this.favoriteSneakersRepository.findFavoriteChangedByPriceRange(minPrice, maxPrice);
    }

    @Override
    public List<FavoriteSneakerDTO> getFavoritesList() {
        List<FavoriteSneaker> favoriteSneakers = (List<FavoriteSneaker>) this.favoriteSneakersRepository.findAll();

        return favoriteSneakers.stream()
                .map(favoriteEntity -> this.mapper.map(favoriteEntity, FavoriteSneakerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FavoriteSneakerDTO> getFavoritesListByBrand(FilterDTO filterDto) {
        String favoriteBrand = filterDto.getBrandFilter();
        List<FavoriteSneaker> favoriteSneakers = (List<FavoriteSneaker>) this.favoriteSneakersRepository.findByBrandContainingIgnoreCase(favoriteBrand);

        return favoriteSneakers.stream()
                .map(favoriteEntity -> this.mapper.map(favoriteEntity, FavoriteSneakerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FavoriteSneakerDTO> getFavoritesListByPrice(FilterDTO filterDto) {
        Integer minPrice = filterDto.getMinPriceFilter();
        Integer maxPrice = filterDto.getMaxPriceFilter();
        List<FavoriteSneaker> favoriteSneakers = (List<FavoriteSneaker>) this.favoriteSneakersRepository.findByPriceBetween(minPrice, maxPrice);

        return favoriteSneakers.stream()
                .map(favoriteEntity -> this.mapper.map(favoriteEntity, FavoriteSneakerDTO.class))
                .collect(Collectors.toList());
    }
}
