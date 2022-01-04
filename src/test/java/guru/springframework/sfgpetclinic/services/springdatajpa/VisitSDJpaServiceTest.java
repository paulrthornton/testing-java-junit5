package guru.springframework.sfgpetclinic.services.springdatajpa;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test find all Visits")
    @Test
    void findAll() {

        // given
        Set<Visit> visits = new HashSet<>();
        Visit visit = new Visit(1l);
        visits.add(visit);
        visit = new Visit(2l);
        visits.add(visit);
        given(visitRepository.findAll()).willReturn(visits);

        // when
        Set<Visit> foundVisits = service.findAll();

        // then
        assertThat(foundVisits).hasSize(2);
        then(visitRepository).should().findAll();
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @DisplayName("Test find Visit by Id")
    @Test
    void findById() {
        // given
        Visit visit = new Visit();
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));

        // when
        Visit foundVisit = service.findById(1l);

        // then
        assertThat(foundVisit).isNotNull();
        then(visitRepository).should().findById(anyLong());
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void save() {
        // given
        Visit visit = new Visit();
        given(service.save(visit)).willReturn(visit);

        // when
        Visit saveVisit = service.save(visit);

        // then
        assertThat(saveVisit).isNotNull();
        then(visitRepository).should(times(1)).save(any(Visit.class));
    }

    @Test
    void delete() {
        // given
        Visit visit = new Visit();

        // when
        service.delete(visit);

        then(visitRepository).should(times(1)).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        // when
        service.deleteById(1l);
        then(visitRepository).should(times(1)).deleteById(anyLong());
    }
}