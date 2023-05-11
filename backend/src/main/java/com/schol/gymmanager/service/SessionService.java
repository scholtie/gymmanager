package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.exception.InsufficientRoleException;
import com.schol.gymmanager.model.*;
import com.schol.gymmanager.model.DTOs.SessionDto;
import com.schol.gymmanager.model.enums.Role;
import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.user.Trainer;
import com.schol.gymmanager.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final SessionOptionService sessionOptionService;
    private final CustomerService customerService;
    private final TrainerService trainerService;
    private final BusinessHoursService businessHoursService;
    private final AuthService authService;

    @Autowired
    public SessionService(SessionRepository sessionRepository,
                          SessionOptionService sessionOptionService,
                          CustomerService customerService,
                          TrainerService trainerService,
                          BusinessHoursService businessHoursService,
                          AuthService authService) {
        this.sessionRepository = sessionRepository;
        this.sessionOptionService = sessionOptionService;
        this.customerService = customerService;
        this.trainerService = trainerService;
        this.businessHoursService = businessHoursService;
        this.authService = authService;
    }

    public Session findById(long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session", id));
    }

    public List<Session> findAllSessionsForDateTrainer(long trainerId, LocalDate date) {
        List<Session> sessionsForDate = new ArrayList<>();
        Trainer trainer = trainerService.findById(trainerId);
        for (Session session : sessionRepository.findAllByTrainerOrderByStartAsc(trainer)) {
            if (session.getStart().toLocalDate().equals(date)) {
                sessionsForDate.add(session);
            }
        }
        return sessionsForDate;
    }

    public List<Session> findAllForLoggedInUser() {
        Optional<BaseUser> optionalLoggedInUser = authService.getLoggedInUser();
        if (optionalLoggedInUser.isPresent()) {
            BaseUser loggedInUser = optionalLoggedInUser.get();
            Role role = loggedInUser.getRole();
            if (role == Role.CUSTOMER) {
                Customer customer = customerService.findByBaseUser(loggedInUser);
                return sessionRepository.findAllByCustomerOrderByStartAsc(customer);
            } else if (role == Role.TRAINER) {
                Trainer trainer = trainerService.findByBaseUser(loggedInUser);
                return sessionRepository.findAllByTrainerOrderByStartAsc(trainer);
            } else {
                throw new InsufficientRoleException(role);
            }
        } else {
            return null;
        }
    }

    public Session create(SessionDto sessionDto) {
        Customer customer;
        Optional<Customer> optionalLoggedInCustomer = customerService.getLoggedInCustomer();
        if (optionalLoggedInCustomer.isPresent()) {
            customer = optionalLoggedInCustomer.get();
        } else {
            throw new InsufficientRoleException();
        }
        Session session = new Session();
        session.setStart(sessionDto.getStart());
        Trainer trainer = trainerService.findById(sessionDto.getTrainerId());
        SessionOption option = sessionOptionService.findById(sessionDto.getOptionId());
        session.setCustomer(customer);
        session.setTrainer(trainer);
        session.setOption(option);
        session.setEnd(sessionDto.getStart().plusMinutes(session.getOption().getLengthMinutes()));
        return sessionRepository.save(session);
    }

    public List<LocalTime> getAvailableTimesForDate(LocalDate date, Long trainerId) {
        List<Session> sessionsForDate = findAllSessionsForDateTrainer(trainerId, date);
        List<LocalTime> availableTimes = new ArrayList<>();
        DayOfWeek day = date.getDayOfWeek();
        BusinessHours businessHours =
                businessHoursService.findBusinessHourForTrainerByDay(trainerId, day);
        boolean isToday = date.equals(LocalDate.now());
        LocalTime currentTime = LocalTime.now();
        boolean isAvailable;
        LocalTime openTime = businessHours.getOpenTime();
        if (Objects.nonNull(openTime)) {
            for (LocalTime time = openTime;
                 time.isBefore(businessHours.getCloseTime());
                 time = time.plusMinutes(30)) {
                isAvailable = true;
                if (!sessionsForDate.isEmpty()) {
                    for (Session session : sessionsForDate) {
                        if (time.equals(session.getStart().toLocalTime())
                                || time.equals(session.getStart().toLocalTime().plusMinutes(30))) {
                            isAvailable = false;
                            break;
                        }
                        if (time.isAfter(session.getStart().toLocalTime())
                                && time.plusMinutes(30).isBefore(session.getEnd().toLocalTime())) {
                            isAvailable = false;
                            break;
                        }
                        if (time.plusMinutes(30).equals(session.getEnd().toLocalTime())) {
                            isAvailable = false;
                            break;
                        }
                    }
                }
                if (isAvailable) {
                    if (!isToday || time.isAfter(currentTime)) {
                        availableTimes.add(time);
                    }
                }
            }
        }
        return availableTimes;
    }

    public void delete(long id) {
        sessionRepository.deleteById(id);
    }
}
