package com.retronic.business.services.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.hero.impl.RaceService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Race;
import com.retronic.persistence.mocks.hero.RaceDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class RaceServiceTest {

    private IGenericService<Race, Integer> raceService;
    private IGenericDao<Race, Integer> raceDao;

    @Before
    public void setUp() {
        raceService = new RaceService();
        raceDao = RaceDaoMock.getMock();
        ReflectionTestUtils.setField(raceService, "genericDao", raceDao);
    }

    @Test
    public void testGet() {
        Race race = raceService.get(1);
        verify(raceDao).read(1);
        assertTrue(race.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Race> races = raceService.getAll();
        verify(raceDao).list();
        assertTrue(races.size() > 0);
    }
}