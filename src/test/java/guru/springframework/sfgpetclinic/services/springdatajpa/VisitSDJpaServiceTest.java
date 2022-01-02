package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test find all")
    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>();
        Visit visit = new Visit(1l);
        visits.add(visit);
        visit = new Visit(2l);
        visits.add(visit);

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = service.findAll();

        assertThat(foundVisits).hasSize(2);

        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        Visit visit = new Visit();

        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1l);

        assertThat(foundVisit).isNotNull();

        verify(visitRepository).findById(anyLong());
    }

    @Test
    void save() {
        Visit visit = new Visit();
        when(service.save(visit)).thenReturn(visit);

        Visit saveVisit = service.save(visit);

        assertThat(saveVisit).isNotNull();

        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    void delete() {
        service.delete(new Visit());
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1l);

        verify(visitRepository).deleteById(anyLong());
    }
}