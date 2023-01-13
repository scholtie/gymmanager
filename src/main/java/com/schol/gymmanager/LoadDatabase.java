package com.schol.gymmanager;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AddressRepository addressRepository, BusinessHoursRepository businessHoursRepository, CustomerRepository customerRepository, GeoRepository geoRepository, GymRepository gymRepository, PriceRepository priceRepository, SessionOptionRepository sessionOptionRepository, SessionRepository sessionRepository, SubscriptionPlanRepository subscriptionPlanRepository, SubscriptionRepository subscriptionRepository, TrainerRepository trainerRepository) {
        return args -> {
            Geo geo = geoRepository.save(Geo.builder().id(1L).lat(10.1323).lng(22.4421).build());
            Address address = addressRepository.save(Address.builder().id(1L).city("Budapest").street("Moha St.").suite("B").zipcode("1234").geo(geo).build());
            Gym gym = gymRepository.save(Gym.builder().id(1L).address(address).name("TestGym").build());
            Trainer trainer = trainerRepository.save(Trainer.builder().id(1L).email("trainer1@test.com").userName("test").passwordHash("1234325fgerwg").firstName("Jane").lastName("Doe").createTime(Timestamp.from(Instant.parse("2023-01-13T13:58:33.364+00:00"))).gender("Female").gym(gym).build());
            Customer customer = customerRepository.save(Customer.builder().id(1L).email("customer1@test.com").userName("test").passwordHash("1234325fgerwg").firstName("John").lastName("Doe").build());
            SessionOption sessionOption = sessionOptionRepository.save(SessionOption.builder().id(1L).maxPeople(1).name("Deadlifting").lengthMinutes(30L).price(BigDecimal.valueOf(4000)).build());
            //Session session = sessionRepository.save(Session.builder().id(1L).option(sessionOption).trainer(trainer).customer(customer)..build());
        };
    }

}
