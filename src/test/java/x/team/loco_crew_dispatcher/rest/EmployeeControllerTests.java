package x.team.loco_crew_dispatcher.rest;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import x.team.loco_crew_dispatcher.dto.EmployeeDto;
import x.team.loco_crew_dispatcher.entity.Status;
import x.team.loco_crew_dispatcher.security.service.JwtService;
import x.team.loco_crew_dispatcher.service.EmployeeService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @MockitoBean
    private JwtService jwtService;

    @Test
    @DisplayName("Test get employee count functionality")
    void givenEmployeesExist_whenGetEmployeeCount_thenSuccessResponse() throws Exception {
        //given
        long expectedCount = 5L;
        BDDMockito.given(employeeService.getEmployeeCount())
                .willReturn(expectedCount);

        //when
        ResultActions result = mockMvc.perform(get("/api/v1/employees/count")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(employeeService, times(1)).getEmployeeCount();
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(5)));
    }

    @Test
    @DisplayName("Test get employee count when repository is empty")
    void givenNoEmployees_whenGetEmployeeCount_thenZeroIsReturned() throws Exception {
        //given
        long expectedCount = 0L;
        BDDMockito.given(employeeService.getEmployeeCount())
                .willReturn(expectedCount);

        //when
        ResultActions result = mockMvc.perform(get("/api/v1/employees/count")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(employeeService, times(1)).getEmployeeCount();
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(0)));
    }

    @Test
    @DisplayName("Test get employee page with default params functionality")
    void givenDefaultPageParams_whenFindAllEmployees_thenSuccessResponse() throws Exception {
        //given
        List<EmployeeDto> employees = List.of();
        Page<EmployeeDto> expectedPage = new PageImpl<>(employees, PageRequest.of(0, 8), employees.size());
        BDDMockito.given(employeeService.getEmployeePage(0,8, "fullName"))
                .willReturn(expectedPage);

        //when
        ResultActions result = mockMvc.perform(get("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(employeeService, times(1)).getEmployeePage(0, 8, "fullName");
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test get employee page with custom params functionality")
    void givenCustomPageParams_whenFindAllEmployees_thenSuccessResponse() throws Exception {
        //given
        List<EmployeeDto> employees = List.of();
        Page<EmployeeDto> expectedPage = new PageImpl<>(employees, PageRequest.of(0, 8), employees.size());
        BDDMockito.given(employeeService.getEmployeePage(2,10, "lastName"))
                .willReturn(expectedPage);

        //when
        ResultActions result = mockMvc.perform(get("/api/v1/employees")
                .param("page", "2")
                .param("size", "10")
                .param("sortBy", "lastName")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(employeeService, times(1)).getEmployeePage(2, 10, "lastName");
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test get employee page with invalid params functionality")
    public void givenInvalidPageParams_whenFindAllEmployees_thenParamsAreCorrected() throws Exception {
        //given
        List<EmployeeDto> employees = List.of();
        Page<EmployeeDto> expectedPage = new PageImpl<>(employees, PageRequest.of(0, 8), employees.size());
        BDDMockito.given(employeeService.getEmployeePage(any(int.class), any(int.class), any(String.class)))
                .willReturn(expectedPage);
        //when
        ResultActions result = mockMvc.perform(get("/api/v1/employees")
                .param("page", "-5")
                .param("size", "-100")
                .contentType(MediaType.APPLICATION_JSON));
        //then
        verify(employeeService, times(1)).getEmployeePage(0, 8, "fullName");
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test get employee count by position functionality")
    public void givenPositionId_whenGetEmployeeCountByPosition_thenSuccessResponse() throws Exception {
        //given
        long expectedCount = 9L;
        BDDMockito.given(employeeService.getEmployeesByPosition(1L))
                .willReturn(expectedCount);
        //when
        ResultActions result = mockMvc.perform(get("/api/v1/employees/count/position/1")
                .contentType(MediaType.APPLICATION_JSON));
        //then
        verify(employeeService, times(1)).getEmployeesByPosition(1L);
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(9)));
    }

    @Test
    @DisplayName("Test get employee count by position when no employees")
    public void givenPositionIdWithNoEmployees_whenGetEmployeeCountByPosition_thenZeroIsReturned() throws Exception {
        //given
        BDDMockito.given(employeeService.getEmployeesByPosition(999L))
                .willReturn(0L);
        //when
        ResultActions result = mockMvc.perform(get("/api/v1/employees/count/position/999")
                .contentType(MediaType.APPLICATION_JSON));
        //then
        verify(employeeService, times(1)).getEmployeesByPosition(999L);
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(0)));
    }

    @Test
    @DisplayName("Test get employee count by position and status functionality")
    public void givenPositionIdAndStatusId_whenGetEmployeeCountByPositionAndStatus_thenSuccessResponse() throws Exception {
        //given
        long expectedCount = 2L;
        Status status = Status.WORKING;
        BDDMockito.given(employeeService.getEmployeesByPositionAndStatus(1L, status))
                .willReturn(expectedCount);
        //when
        ResultActions result = mockMvc.perform(get("/api/v1/employees/count/position/1/state/0")
                .contentType(MediaType.APPLICATION_JSON));
        //then
        verify(employeeService, times(1)).getEmployeesByPositionAndStatus(1L, Status.WORKING);
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is(2)));
    }
}