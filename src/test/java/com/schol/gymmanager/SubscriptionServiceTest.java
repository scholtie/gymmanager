package com.schol.gymmanager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.schol.gymmanager.model.Subscription;
import com.schol.gymmanager.repository.SubscriptionRepository;
import com.schol.gymmanager.service.SubscriptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionServiceTest {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private TestUtils testUtils;
    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Test
    public void findAllReturnsAllStoredSubs() {
        //GIVEN
        Subscription subscription = testUtils.getTestSubscription();
        List<Subscription> expected = new ArrayList<>();
        expected.add(subscription);
        //WHEN
        when(subscriptionRepository.findAll()).thenReturn(expected);
        List<Subscription> actual = subscriptionService.findAll();
        //THEN
        assertEquals(1, actual.size());
        assertTrue(actual.get(0).isOngoing());
    }


}
