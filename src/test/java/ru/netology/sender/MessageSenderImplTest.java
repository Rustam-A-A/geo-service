package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.*;


public class MessageSenderImplTest {

    @Test
    void sendMessageIfRussiaTest(){
        LocalizationServiceImpl localisationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localisationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
    }



}