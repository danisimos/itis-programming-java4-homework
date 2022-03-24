package ru.itis.app;

import ru.itis.models.City;
import ru.itis.repository.impl.CityRepository;
import ru.itis.repository.impl.CountryRepository;

public class Application {
    public static void main(String[] args) {
        CountryRepository countryRepository = new CountryRepository();
        CityRepository cityRepository = new CityRepository();

        City city = City.builder()
                .name("Kazan")
                .population(123123L)
                .country(countryRepository.findById(5L).get())
                .build();

        System.out.println(cityRepository.save(city));
        System.out.println(cityRepository.findAll());
        System.out.println(countryRepository.findAll());
    }
}
