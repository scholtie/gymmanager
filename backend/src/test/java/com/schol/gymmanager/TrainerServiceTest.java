package com.schol.gymmanager;

import static org.junit.Assert.*;

import com.schol.gymmanager.model.user.Trainer;
import com.schol.gymmanager.repository.TrainerRepository;
import com.schol.gymmanager.service.TrainerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainerServiceTest {
    @Autowired
    private TrainerService underTest;
    @Autowired
    private TestUtils testUtils;
    @Mock
    private TrainerRepository trainerRepository;

    @Test
    public void testCreate() {
        //GIVEN
        Trainer expected = testUtils.getTestTrainer();
        //WHEN
        Trainer actual = underTest.create(testUtils.getTestTrainerDto());
        //THEN
        assertEquals(expected.getGym().getId(), actual.getGym().getId());
        assertEquals(expected.getUserName(), actual.getUserName());

    }

}
