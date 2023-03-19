package com.schol.gymmanager.utility;

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
import java.time.LocalDateTime;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AddressRepository addressRepository, BusinessHoursRepository businessHoursRepository, CustomerRepository customerRepository, GeoRepository geoRepository, GymRepository gymRepository, PriceRepository priceRepository, SessionOptionRepository sessionOptionRepository, SessionRepository sessionRepository, SubscriptionPlanRepository subscriptionPlanRepository, SubscriptionRepository subscriptionRepository, TrainerRepository trainerRepository) {
        return args -> {
            Geo geo = geoRepository.save(Geo.builder().id(1L).lat(10.1323).lng(22.4421).build());
            Address address = addressRepository.save(Address.builder().id(1L).city("Budapest").street("Moha St.").suite("B").zipcode("1234").geo(geo).build());
            Gym gym = gymRepository.save(Gym.builder().id(1L).address(address).name("TestGym").build());
            Trainer trainer = trainerRepository.save(Trainer.builder().id(1L).email("trainer1@test.com").userName("test").passwordHash("1234325fgerwg").firstName("Jane").lastName("Doe").createTime(Timestamp.from(Instant.parse("2023-01-13T13:58:33.364+00:00"))).gender(Gender.FEMALE).gym(gym).build());
            Customer customer = customerRepository.save(Customer.builder().id(1L).email("customer1@test.com").userName("test").passwordHash("1234325fgerwg").firstName("John").lastName("Doe").createTime(Timestamp.from(Instant.parse("2023-01-13T13:58:33.364+00:00"))).build());
            SessionOption sessionOption = sessionOptionRepository.save(SessionOption.builder().id(1L).maxPeople(1).name("Deadlifting").lengthMinutes(30L).price(BigDecimal.valueOf(4000)).build());
            Session session = sessionRepository.save(Session.builder().id(1L).start(LocalDateTime.parse("2023-01-13T15:12:12.939766")).end(LocalDateTime.parse("2023-01-13T16:42:12.94202")).option(sessionOption).trainer(trainer).customer(customer).build());
            SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.save(SubscriptionPlan.builder().name("Monthly").price(BigDecimal.valueOf(4000)).durationInDays(30L).description("Standard 30 days subscription").gym(gym).id(1L).build());
            Subscription subscription = subscriptionRepository.save(Subscription.builder().id(1L).gym(gym).price(BigDecimal.valueOf(100)).customer(customer).currentPeriodStart(LocalDateTime.parse("2023-01-13T15:12:12.939766")).currentPeriodEnd(LocalDateTime.parse("2023-02-13T16:42:12.94202")).ongoing(true).cancelAtPeriodEnd(true).defaultPaymentMethod(1).build());
        };
    }

}
