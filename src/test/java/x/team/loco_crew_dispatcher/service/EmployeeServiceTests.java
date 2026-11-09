package x.team.loco_crew_dispatcher.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import x.team.loco_crew_dispatcher.dto.EmployeeDto;
import x.team.loco_crew_dispatcher.entity.Status;
import x.team.loco_crew_dispatcher.repository.EmployeeRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeServiceUnderTest;

    @Test
    @DisplayName("Test get employee count functionality")
    void givenEmployeeCount_whenGetEmployeeCount_thenEmployeeCountIsReturned() {
        // given
        long expectedCount = 5L;
        BDDMockito.given(employeeRepository.count()).willReturn(expectedCount);

        //when
        long currentCount = employeeServiceUnderTest.getEmployeeCount();

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(employeeRepository).count();
    }

    @Test
    @DisplayName("Test get employee count returns 0 when repository is empty")
    void givenNoEmployees_whenGetEmployeeCount_thenZeroIsReturned() {
        // given
        long expectedCount = 0L;
        BDDMockito.given(employeeRepository.count()).willReturn(expectedCount);

        // when
        long currentCount = employeeServiceUnderTest.getEmployeeCount();

        // then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(employeeRepository).count();
    }

    @Test
    @DisplayName("Test get employee page functionality")
    void givenEmployeePageWithParams_whenGetEmployeePage_thenEmployeePageIsReturned() {
        //given
        int page = 2;
        int size = 10;
        String sortBy = "lastName";

        Page<EmployeeDto> expectedPage = BDDMockito.mock(Page.class);
        BDDMockito.given(employeeRepository.getAllEmployees(any(PageRequest.class)))
                .willReturn(expectedPage);

        //when
        Page<EmployeeDto> currentPage = employeeServiceUnderTest.getEmployeePage(page, size, sortBy);

        //then
        assertThat(currentPage).isSameAs(expectedPage);
        verify(employeeRepository).getAllEmployees(PageRequest.of(page, size, Sort.by(sortBy).ascending()));
    }

    @Test
    @DisplayName("Test get employees by position functionality")
    void givenPositionId_whenGetEmployeesByPosition_thenEmployeeCountIsReturned() {
        // given
        long positionId = 1L;
        long expectedCount = 9L;

        BDDMockito.given(employeeRepository.countByPositionId(positionId))
                .willReturn(expectedCount);

        //when
        long currentCount = employeeServiceUnderTest.getEmployeesByPosition(positionId);

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(employeeRepository).countByPositionId(positionId);
    }

    @Test
    @DisplayName("Test get employees by position returns 0 when no employees with this position")
    void givenPositionIdWithNoEmployees_whenGetEmployeesByPosition_thenZeroIsReturned() {
        // given
        long positionId = 100L;
        long expectedCount = 0L;

        BDDMockito.given(employeeRepository.countByPositionId(positionId))
                .willReturn(expectedCount);

        // when
        long currentCount = employeeServiceUnderTest.getEmployeesByPosition(positionId);

        // then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(employeeRepository).countByPositionId(positionId);
    }

    @Test
    @DisplayName("Test get employees by position and status functionality")
    void givenPositionIdAndStatus_whenGetEmployeesByPositionAndStatus_thenEmployeeCountIsReturned() {
        //given
        long positionId = 1L;
        Status status = Status.WORKING;
        long expectedCount = 2L;

        BDDMockito.given(employeeRepository.countByPositionIdAndStatus(positionId, status))
                .willReturn(expectedCount);

        //when
        long currentCount = employeeServiceUnderTest.getEmployeesByPositionAndStatus(positionId, status);

        //then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(employeeRepository).countByPositionIdAndStatus(positionId, status);
    }

    @Test
    @DisplayName("Test get employees by position and status returns 0 when no matches")
    void givenPositionAndStatusWithNoMatches_whenGetEmployeesByPositionAndStatus_thenZeroIsReturned() {
        // given
        long positionId = 1L;
        Status status = Status.SESSION;
        long expectedCount = 0L;

        BDDMockito.given(employeeRepository.countByPositionIdAndStatus(positionId, status))
                .willReturn(expectedCount);

        // when
        long currentCount = employeeServiceUnderTest.getEmployeesByPositionAndStatus(positionId, status);

        // then
        assertThat(currentCount).isEqualTo(expectedCount);
        verify(employeeRepository).countByPositionIdAndStatus(positionId, status);
    }
}
