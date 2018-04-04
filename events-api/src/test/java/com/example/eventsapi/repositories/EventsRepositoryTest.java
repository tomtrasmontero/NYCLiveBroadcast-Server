package com.example.eventsapi.repositories;

import com.example.eventsapi.models.Event;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Before
    public void setUp() {
        Event firstEvent = new Event(
                "1",
                "1"
        );

        Event secondEvent = new Event(
                "1",
                "2"
        );

        entityManager.persist(firstEvent);
        entityManager.persist(secondEvent);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllUsers() {
        Iterable<Event> eventsFromDb = eventRepository.findAll();

        assertThat(Iterables.size(eventsFromDb), is(2));
    }

    @Test
    public void findAll_returnsUserId() {
        Iterable<Event> eventsFromDb = eventRepository.findAll();

        String secondEventEventId = Iterables.get(eventsFromDb, 1).getUserId();

        assertThat(secondEventEventId, is("1"));
    }

    @Test
    public void findAll_returnsEventId() {
        Iterable<Event> eventsFromDb = eventRepository.findAll();

        String secondEventEventId = Iterables.get(eventsFromDb, 1).getEventId();

        assertThat(secondEventEventId, is("2"));
    }

}
