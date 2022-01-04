package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        //given
        Speciality speciality = new Speciality();

        //when
        service.delete(speciality);

        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findById() {

        // given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1l)).willReturn(Optional.of(speciality));

        // when
        Speciality foundSpeciality = service.findById(1l);

        // then
        assertThat(foundSpeciality).isNotNull();
        then(specialtyRepository).should(times(1)).findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        // given - none

        // when
        service.deleteById(1l);
        service.deleteById(1l);

        then(specialtyRepository).should(times(2)).deleteById(anyLong());
    }

    @Test
    void deleteByIdAtLeastOnce() {
        // given - none

        // when
        service.deleteById(1l);
        service.deleteById(1l);

        // then
        then(specialtyRepository).should(atLeastOnce()).deleteById(anyLong());
    }

    @Test
    void deleteByIdAtMost() {
        // given - none

        // when
        service.deleteById(1l);
        service.deleteById(1l);

        // then
        then(specialtyRepository).should(atMost(2)).deleteById(anyLong());
    }

    @Test
    void deleteByIdNever() {
        // when
        service.deleteById(1l);
        service.deleteById(1l);

        // then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1l);
        then(specialtyRepository).should(never()).deleteById(5l);
    }

    @Test
    void testDelete() {
        // when
        service.delete(new Speciality());

        // then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }


    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));

        assertThrows(RuntimeException.class, () -> service.findById(1L));

        then(specialtyRepository).should().findById(1l);
    }

    @Test
    void testDeleteBDD() {
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());

        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));

        then(specialtyRepository).should().delete(any());
    }

}