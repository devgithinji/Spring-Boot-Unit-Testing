package com.densoft.springboottesting.repository;

import com.densoft.springboottesting.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        employee = Employee.builder().firstName("Dennis").lastName("Githinji").email("wakahiad@gmail.com").build();
    }

    //junit for save employee
    @DisplayName("Junit for save employee")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        //given precondition or setup

        //when action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);
        //then verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }


    //JUnit test for get all employees operation
    @DisplayName("JUnit for get all employees operation ")
    @Test
    public void givenEmployeesList_whenFindAll_thenReturnEmployeesList() {
        //given setup
        Employee employeeTwo = Employee.builder().firstName("John").lastName("Cena").email("cena@gmail.com").build();

        employeeRepository.saveAll(List.of(employee, employeeTwo));


        //when action or behaviour that we are going to test

        List<Employee> employees = employeeRepository.findAll();

        //then verify the output
        assertThat(employees.size()).isEqualTo(2);

    }

    //JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        //given setup
        employeeRepository.save(employee);

        //when action or behaviour that we are going to test
        Employee retrievedEmployee = employeeRepository.findById(employee.getId()).get();

        //then verify the output
        assertThat(retrievedEmployee).isNotNull();

    }

    //JUnit test for get employee operation
    @DisplayName("JUnit test for get employee operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
        //given setup
        employeeRepository.save(employee);
        //when action or behaviour that we are going to test
        Employee retrievedEmployee = employeeRepository.findByEmail(employee.getEmail()).get();
        //then verify the output
        assertThat(retrievedEmployee).isNotNull();

    }

    //JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        //given setup
        employeeRepository.save(employee);
        //when action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("test@gmail.com");
        savedEmployee.setFirstName("Test");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);
        //then verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("test@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Test");

    }

    //JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        //given setup
        Employee savedEmployee = employeeRepository.save(employee);
        //when action or behaviour that we are going to test
        employeeRepository.delete(savedEmployee);
        Optional<Employee> employeeOptional = employeeRepository.findById(savedEmployee.getId());
        //then verify the output
        assertThat(employeeOptional).isEmpty();

    }

    //JUnit test for custom query using JPQL with index parameters
    @DisplayName("JUnit test for custom query using JPQL with index parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {
        //given setup
        Employee savedEmployee = employeeRepository.save(employee);

        //when action or behaviour that we are going to test
        Employee retrievedEmployee = employeeRepository.findByJPQL(savedEmployee.getFirstName(), savedEmployee.getLastName());

        //then verify the output
        assertThat(retrievedEmployee).isNotNull();

    }

    //JUnit test for custom query using JPQL with named parameters
    @DisplayName("JUnit test for custom query using JPQL with named parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        //given setup
        Employee savedEmployee = employeeRepository.save(employee);

        //when action or behaviour that we are going to test
        Employee retrievedEmployee = employeeRepository.findByJPQLNamedParams(savedEmployee.getFirstName(), savedEmployee.getLastName());

        //then verify the output
        assertThat(retrievedEmployee).isNotNull();

    }

    //JUnit test for custom query using JPQL with named parameters
    @DisplayName("JUnit test for custom query using Native SQL with index parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject() {
        //given setup
        Employee savedEmployee = employeeRepository.save(employee);

        //when action or behaviour that we are going to test
        Employee retrievedEmployee = employeeRepository.findByNativeSQL(savedEmployee.getFirstName(), savedEmployee.getLastName());

        //then verify the output
        assertThat(retrievedEmployee).isNotNull();

    }

    //JUnit test for custom query using JPQL with named parameters
    @DisplayName("JUnit test for custom query using Native SQL with named parameters")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParameters_thenReturnEmployeeObject() {
        //given setup
        Employee savedEmployee = employeeRepository.save(employee);

        //when action or behaviour that we are going to test
        Employee retrievedEmployee = employeeRepository.findByNativeSQLNamedParams(savedEmployee.getFirstName(), savedEmployee.getLastName());

        //then verify the output
        assertThat(retrievedEmployee).isNotNull();

    }
}
