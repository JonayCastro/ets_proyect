package com.zeven.ets_proyect.Ets_Proyect.services.impl;

import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakerDTO;
import com.zeven.ets_proyect.Ets_Proyect.dto.sneakers.SneakersDataDTO;
import com.zeven.ets_proyect.Ets_Proyect.entities.FavoriteSneaker;
import com.zeven.ets_proyect.Ets_Proyect.entities.SneakerEntity;
import com.zeven.ets_proyect.Ets_Proyect.entities.User;
import com.zeven.ets_proyect.Ets_Proyect.repositories.FavoriteSneakersRepository;
import com.zeven.ets_proyect.Ets_Proyect.services.SupplierCatalogServices;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceImplTest {

    @Mock
    private FavoriteSneakersRepository favoriteSneakersRepositoryMock;

    @Mock
    private ModelMapper mapperMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private SupplierCatalogServices<SneakersDataDTO, SneakerDTO, SneakerEntity> supplierCatalogServicesMock;

    @InjectMocks
    private FavoriteServiceImpl favoriteService;

    @Test
    @DisplayName("getFavoriteById: Devuelve FavoriteSneaker cuando el ID existe")
    void cuandoGetFavoriteById_conIdExistente_entoncesRetornaFavoriteSneaker() {
        Long favoriteIdExistente = 1L;
        FavoriteSneaker sneakerEsperadoMock = Mockito.mock(FavoriteSneaker.class);

        when(sneakerEsperadoMock.getIdFavorite()).thenReturn(favoriteIdExistente);

        when(favoriteSneakersRepositoryMock.findById(favoriteIdExistente)).thenReturn(Optional.of(sneakerEsperadoMock));

        FavoriteSneaker resultado = favoriteService.getFavoriteById(favoriteIdExistente);

        assertNotNull(resultado);
        assertEquals(favoriteIdExistente, resultado.getIdFavorite());

        verify(favoriteSneakersRepositoryMock).findById(favoriteIdExistente);
    }

    @Test
    @DisplayName("getFavoriteById: Lanza RuntimeException cuando el ID no existe")
    void cuandoGetFavoriteById_conIdNoExistente_entoncesLanzaRuntimeException() {
        Long favoriteIdNoExistente = 99L;

        when(favoriteSneakersRepositoryMock.findById(favoriteIdNoExistente)).thenReturn(Optional.empty());

        RuntimeException exceptionLanzada = assertThrows(RuntimeException.class, () -> {
            favoriteService.getFavoriteById(favoriteIdNoExistente);
        });

        assertEquals("Favorite not found", exceptionLanzada.getMessage());

        verify(favoriteSneakersRepositoryMock).findById(favoriteIdNoExistente);
    }

    @Test
    @DisplayName("addFavoriteBySneakerId: guarda el favorito cuando el usuario y sneaker existen")
    void cuandoAddFavoriteBySneakerId_conUsuarioYSneakerExistentes_entoncesGuardaFavorito() {
        Long sneakerStoredId = 10L;
        Long userIdProporcionado = 1L; 

        User mockUser = Mockito.mock(User.class);
        SneakerEntity mockSneakerEntity = Mockito.mock(SneakerEntity.class);
        FavoriteSneaker mockFavoriteSneakerMapeado = Mockito.mock(FavoriteSneaker.class);
        FavoriteSneaker mockFavoriteSneakerGuardado = Mockito.mock(FavoriteSneaker.class);

        when(userServiceMock.findUserById(userIdProporcionado)).thenReturn(mockUser);
        when(supplierCatalogServicesMock.findEntityById(sneakerStoredId)).thenReturn(mockSneakerEntity);
        when(mapperMock.map(mockSneakerEntity, FavoriteSneaker.class)).thenReturn(mockFavoriteSneakerMapeado);
        when(favoriteSneakersRepositoryMock.save(mockFavoriteSneakerMapeado)).thenReturn(mockFavoriteSneakerGuardado);

        
        favoriteService.addFavoriteBySneakerId(sneakerStoredId, userIdProporcionado);

       
        verify(userServiceMock).findUserById(userIdProporcionado);
        verify(supplierCatalogServicesMock).findEntityById(sneakerStoredId);
        verify(mapperMock).map(mockSneakerEntity, FavoriteSneaker.class);
        verify(mockFavoriteSneakerMapeado).setUser(mockUser);
        verify(favoriteSneakersRepositoryMock).save(mockFavoriteSneakerMapeado);
    }
    @Test
    @DisplayName("deleteFavoriteById: elimina el favorito cuando el ID existe")
    void cuandoDeleteFavoriteById_conIdExistente_entoncesEliminaFavorito() {
        Long favoriteSneakerIdExistente = 1L;
        FavoriteSneaker mockFavoriteSneaker = Mockito.mock(FavoriteSneaker.class);

        when(favoriteSneakersRepositoryMock.findById(favoriteSneakerIdExistente))
            .thenReturn(Optional.of(mockFavoriteSneaker));

        assertDoesNotThrow(() -> {
            favoriteService.deleteFavoriteById(favoriteSneakerIdExistente);
        });

        verify(favoriteSneakersRepositoryMock).findById(favoriteSneakerIdExistente);
        verify(favoriteSneakersRepositoryMock).delete(mockFavoriteSneaker);
    }
}