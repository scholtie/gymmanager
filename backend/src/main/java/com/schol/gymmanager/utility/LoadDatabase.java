package com.schol.gymmanager.utility;

import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.enums.Gender;
import com.schol.gymmanager.model.enums.PaymentMethod;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.user.Gym;
import com.schol.gymmanager.model.user.Trainer;
import com.schol.gymmanager.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AddressRepository addressRepository,
                                   BusinessHoursRepository businessHoursRepository,
                                   CustomerRepository customerRepository,
                                   GeoRepository geoRepository,
                                   GymRepository gymRepository,
                                   SessionOptionRepository sessionOptionRepository,
                                   SessionRepository sessionRepository,
                                   SubscriptionPlanRepository subscriptionPlanRepository,
                                   SubscriptionRepository subscriptionRepository,
                                   TrainerRepository trainerRepository,
                                   BaseUserRepository baseUserRepository) {
        return args -> {
            Geo geo = geoRepository.save(Geo.builder().id(1L).lat(10.1323).lng(22.4421).build());
            Geo geo2 = geoRepository.save(Geo.builder().id(1L).lat(65.1323).lng(52.4421).build());
            Address address = addressRepository.save(Address.builder().id(1L).city("Budapest").street("Moha St.").suite("B").zipcode("1234").geo(geo).build());
            Address address2 = addressRepository.save(Address.builder().id(2L).city("Budapest").street("Reitter F").suite("B").zipcode("1270").geo(geo2).build());
            BaseUser baseUserCustomer = baseUserRepository.save(BaseUser.builder().email("testCustomer@email.com").passwordHash("wgwefewfwe2312").role(Role.CUSTOMER).build());
            BaseUser baseUserTrainer = baseUserRepository.save(BaseUser.builder().email("testTrainer@email.com").passwordHash("wgwefewfwe2312").role(Role.TRAINER).build());
            BaseUser baseUserTrainer2 = baseUserRepository.save(BaseUser.builder().email("testTrainer2@email.com").passwordHash("wgwefewfwe2312").role(Role.TRAINER).build());
            BaseUser baseUserGym = baseUserRepository.save(BaseUser.builder().email("testGym@email.com").passwordHash("wgwefewfwe2312").role(Role.GYM).build());
            BaseUser baseUserGym2 = baseUserRepository.save(BaseUser.builder().email("testGym2@email.com").passwordHash("wgwefewfwe2312").role(Role.GYM).build());
            Gym gym = gymRepository.save(Gym.builder().id(1L).baseUser(baseUserGym).address(address).name("TestGym").avatarImgPath("https://robbreport.com/wp-content/uploads/2022/07/Himat_WeightRoom.jpg?w=1000").about("cool gym").build());
            Gym gym2 = gymRepository.save(Gym.builder().id(2L).baseUser(baseUserGym2).address(address2).name("TestGym2").avatarImgPath("https://thumbs.dreamstime.com/b/gym-24699087.jpg").about("cool gym").build());
            Trainer trainer = trainerRepository.save(Trainer.builder().id(1L).baseUser(baseUserTrainer).firstName("Jane").lastName("Doe").gender(Gender.FEMALE).gym(gym).build());
            Trainer trainer2 = trainerRepository.save(Trainer.builder().id(2L).baseUser(baseUserTrainer2).firstName("John").lastName("Smith").gender(Gender.MALE).gym(gym).build());
            BusinessHours businessHours = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.MONDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer).build());
            BusinessHours businessHours1 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.FRIDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer).build());
            BusinessHours businessHours2 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.TUESDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer).build());
            BusinessHours businessHours3 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.WEDNESDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer).build());
            BusinessHours businessHours4 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.THURSDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer).build());
            BusinessHours businessHours5 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.SATURDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer).build());
            BusinessHours businessHours6 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.SUNDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer).build());
            BusinessHours business2Hours = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.MONDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer2).build());
            BusinessHours business2Hours1 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.FRIDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer2).build());
            BusinessHours business2Hours2 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.TUESDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer2).build());
            BusinessHours business2Hours3 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.WEDNESDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer2).build());
            BusinessHours business2Hours4 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.THURSDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer2).build());
            BusinessHours business2Hours5 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.SATURDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer2).build());
            BusinessHours business2Hours6 = businessHoursRepository.save(BusinessHours.builder().day(DayOfWeek.SUNDAY).openTime(LocalTime.of(7,0)).closeTime(LocalTime.of(16,0)).trainer(trainer2).build());
            Customer customer = customerRepository.save(Customer.builder().id(1L).baseUser(baseUserCustomer).firstName("John").lastName("Doe").build());
            SessionOption sessionOption = sessionOptionRepository.save(SessionOption.builder().id(1L).description("test").name("Deadlifting").lengthMinutes(60L).price(BigDecimal.valueOf(4000)).trainer(trainer).build());
            SessionOption sessionOption2 = sessionOptionRepository.save(SessionOption.builder().id(2L).description("test").name("Spinning").lengthMinutes(30L).price(BigDecimal.valueOf(2000)).trainer(trainer2).build());
            SessionOption sessionOption3 = sessionOptionRepository.save(SessionOption.builder().id(3L).description("test").name("Aerobics").lengthMinutes(30L).price(BigDecimal.valueOf(3000)).trainer(trainer).build());
            Session session = sessionRepository.save(Session.builder().id(1L).start(LocalDateTime.parse("2023-01-13T15:12:12.939766")).end(LocalDateTime.parse("2023-01-13T16:42:12.94202")).option(sessionOption).trainer(trainer).customer(customer).build());
            SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.save(SubscriptionPlan.builder().name("Monthly").price(BigDecimal.valueOf(4000)).durationInDays(30L).description("Standard 30 days subscription").gym(gym).id(1L).build());
            SubscriptionPlan subscriptionPlan2 = subscriptionPlanRepository.save(SubscriptionPlan.builder().name("Monthly").price(BigDecimal.valueOf(3000)).durationInDays(30L).description("Students 30 days subscription").gym(gym).id(2L).build());
            SubscriptionPlan subscriptionPlan3 = subscriptionPlanRepository.save(SubscriptionPlan.builder().name("Monthly").price(BigDecimal.valueOf(4000)).durationInDays(30L).description("Standard 30 days subscription").gym(gym2).id(3L).build());
            SubscriptionPlan subscriptionPlan4 = subscriptionPlanRepository.save(SubscriptionPlan.builder().name("Monthly").price(BigDecimal.valueOf(3000)).durationInDays(30L).description("Students 30 days subscription").gym(gym2).id(4L).build());
            Subscription subscription = subscriptionRepository.save(Subscription.builder().id(1L).subscriptionPlan(subscriptionPlan).customer(customer).currentPeriodStart(LocalDate.now()).currentPeriodEnd(LocalDate.now().plusDays(30)).ongoing(true).cancelAtPeriodEnd(true).defaultPaymentMethod(PaymentMethod.CASH).build());
        };
    }

}
