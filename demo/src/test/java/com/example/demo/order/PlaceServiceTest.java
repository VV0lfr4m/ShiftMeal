package com.example.demo.order;

import com.example.demo.Utils;
import com.example.demo.order.repository.PlaceRepository;
import com.example.demo.order.service.PlaceService;
import com.example.demo.order.service.PlaceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository;
    @Mock
    private Utils utils;

    @InjectMocks
    private PlaceService placeService = new PlaceServiceImpl(placeRepository, utils);

    private List<Place> placesList;
    private List<Promotion> promotionList;

    @Test
    public void findPlacesNotEmptySuccess() {
        Pageable pageable = PageRequest.of(1, 1);
        when(placeRepository.findAll(pageable)).thenReturn(new PageImpl<>(placesList));

        Page<Place> placesPage = placeService.findPlaces(1, 1);

        assertEquals(placesList, placesPage.getContent());
        verify(placeRepository, times(1)).findAll(pageable);
    }
    @Test
    public void findPlacesIsEmptySuccess() {
        Pageable pageable = PageRequest.of(1, 1);
        when(placeRepository.findAll(pageable)).thenReturn(null);

        Page<Place> placesPage = placeService.findPlaces(1, 1);

        assertNull(placesPage);
        verify(placeRepository, times(1)).findAll(pageable);
    }

    @BeforeEach
    public void setUp() {
        List<Meal> meals = Collections.singletonList(
                Meal.builder()
                        .id(1l)
                        .type("Dinner")
                        .name("Spagetti")
                        .image(new byte[]{})
                        .description("Mamma Mia!")
                        .price(200.0)
                        .rating(4.9)
                        .addons(Collections.singletonList(new Addon(1L, "Ktchp", 15.0)))
                        .amount(2)
                        .build());

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 12);
        placesList =  Collections.singletonList(
                Place.builder()
                        .id(1l)
                        .additionalInfo("Welcome!")
                        .addressId(1l)
                        .category("Italian")
                        .deliveryId(1l)
                        .name("Mamma Mia!")
                        .description("We offer the best italian meals!")
                        .rating(4.9)
                        .meals(meals)
                        .workTime(Collections.singletonList(new WorkTime(1l, "Sunday", new Date(), instance.getTime())))
                        .build());

        promotionList = Collections.singletonList(
                new Promotion(1l,
                        "Delivery Discount",
                        placesList,
                        true));

        MockitoAnnotations.openMocks(this); // Initialize mocks

        placeService = new PlaceServiceImpl(placeRepository, utils);

    }
}
