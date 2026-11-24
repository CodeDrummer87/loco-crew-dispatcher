package x.team.loco_crew_dispatcher.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import x.team.loco_crew_dispatcher.dto.LocomotiveCrewDto;
import x.team.loco_crew_dispatcher.entity.CrewStatus;
import x.team.loco_crew_dispatcher.repository.LocomotiveCrewRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LocomotiveCrewServiceTests {

    @Mock
    private LocomotiveCrewRepository locomotiveCrewRepository;

    @InjectMocks
    private LocomotiveCrewService serviceUnderTest;

    @Test
    @DisplayName("Test get locomotive crew count functionality")
    void givenLocomotiveCrews_whenGetLocomotiveCrewCount_thenCountIsReturned() {
        //given
        long expectedCount = 5L;
        BDDMockito.given(locomotiveCrewRepository.count())
                .willReturn(expectedCount);

        //when
        long currentCount = serviceUnderTest.getLocomotiveCrewCount();

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(locomotiveCrewRepository).count();
    }

    @Test
    @DisplayName("Test get locomotive crew count returns 0 when repository is empty")
    void givenNoLocomotiveCrews_whenGetLocomotiveCrewCount_thenZeroIsReturned() {
        //given
        long expectedCount = 0L;
        BDDMockito.given(locomotiveCrewRepository.count())
                .willReturn(expectedCount);

        //when
        long currentCount = serviceUnderTest.getLocomotiveCrewCount();

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(locomotiveCrewRepository).count();
    }

    @Test
    @DisplayName("Test get locomotive crew list functionality")
    void givenLocomotiveCrews_whenGetLocomotiveCrewList_thenCrewListIsReturned() {
        //given
        LocomotiveCrewDto locomotiveCrew1 = BDDMockito.mock(LocomotiveCrewDto.class);
        LocomotiveCrewDto locomotiveCrew2 = BDDMockito.mock(LocomotiveCrewDto.class);

        List<LocomotiveCrewDto> expectedLocomotiveCrews = List.of(locomotiveCrew1, locomotiveCrew2);
        BDDMockito.given(locomotiveCrewRepository.getAllLocomotiveCrews())
                .willReturn(expectedLocomotiveCrews);

        //when
        List<LocomotiveCrewDto> currentLocomotiveCrews = serviceUnderTest.getLocomotiveCrewList();

        //then
        assertThat(currentLocomotiveCrews).isNotNull();
        assertThat(currentLocomotiveCrews.size()).isEqualTo(2);
        verify(locomotiveCrewRepository).getAllLocomotiveCrews();
    }

    @Test
    @DisplayName("Test get locomotive crew list returns empty list when repository is empty")
    void givenNoLocomotiveCrews_whenGetLocomotiveCrewList_thenEmptyListIsReturned() {
        //given
        BDDMockito.given(locomotiveCrewRepository.getAllLocomotiveCrews())
                .willReturn(List.of());

        //when
        List<LocomotiveCrewDto> currentLocomotiveCrews = serviceUnderTest.getLocomotiveCrewList();

        //then
        assertThat(currentLocomotiveCrews).isNotNull();
        assertThat(currentLocomotiveCrews.isEmpty()).isTrue();
        verify(locomotiveCrewRepository).getAllLocomotiveCrews();
    }

    @Test
    @DisplayName("Test get single driver count functionality")
    void givenSingleDriverCrews_whenGetSingleDriverCount_thenCountIsReturned() {
        //given
        long expectedCount = 3L;
        BDDMockito.given(locomotiveCrewRepository.getSinglePersonCrewCount())
                .willReturn(expectedCount);

        //when
        long currentCount = serviceUnderTest.getSingleDriverCount();

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(locomotiveCrewRepository).getSinglePersonCrewCount();
    }

    @Test
    @DisplayName("Test get single driver count returns 0 when repository is empty")
    void givenNoSingleDriverCrews_whenGetSingleDriverCount_thenZeroIsReturned() {
        //given
        long expectedCount = 0L;
        BDDMockito.given(locomotiveCrewRepository.getSinglePersonCrewCount())
                .willReturn(expectedCount);

        //when
        long currentCount = serviceUnderTest.getSingleDriverCount();

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(locomotiveCrewRepository).getSinglePersonCrewCount();
    }

    @Test
    @DisplayName("Test get locomotive crew count by status functionality")
    void givenStatus_whenGetCrewCountByStatus_thenCountIsReturned() {
        //given
        CrewStatus status = CrewStatus.WORKING;
        long expectedCount = 4L;

        BDDMockito.given(locomotiveCrewRepository.countByCrewStatus(status))
                .willReturn(expectedCount);

        //when
        long currentCount = serviceUnderTest.getCrewCountByStatus(status);

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(locomotiveCrewRepository).countByCrewStatus(status);
    }

    @Test
    @DisplayName("Test get locomotive crew count by status returns 0")
    void givenStatusWithNoCrews_whenGetCrewCountByStatus_thenZeroIsReturned() {
        //given
        CrewStatus status = CrewStatus.ON_CALL;
        long expectedCount = 0L;

        BDDMockito.given(locomotiveCrewRepository.countByCrewStatus(status))
                .willReturn(expectedCount);

        //when
        long currentCount = serviceUnderTest.getCrewCountByStatus(status);

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(locomotiveCrewRepository).countByCrewStatus(status);
    }
}