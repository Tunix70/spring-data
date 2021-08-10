package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DepartmentRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.exception.DepartmentAlreadyExistException;
import com.springCrudV2.demo.exception.DepartmentNotFoundException;
import com.springCrudV2.demo.mapperDto.DepartmentMapperDto;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapperDto departmentMapperDto;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void shouldGetAllListDepartment() {
        //given
        List<Department> departmentList = new ArrayList<>();
        Department department = new Department(1L, "Department");
        Department department1 = new Department(2L, "Main Department");

        departmentList.add(department);
        departmentList.add(department1);

        List<DepartmentDto> dtotList = new ArrayList<>();
        DepartmentDto dto = new DepartmentDto(1L, "Department");
        DepartmentDto dto1 = new DepartmentDto(2L, "Main Department");

        dtotList.add(dto);
        dtotList.add(dto1);

        when(departmentRepository.findAll()).thenReturn(departmentList);
        when(departmentMapperDto.mapToDepartmentDto(department)).thenReturn(dto);
        when(departmentMapperDto.mapToDepartmentDto(department1)).thenReturn(dto1);

        //when
        List<DepartmentDto> departmentList1 = departmentService.getAll();

        //than
        assertThat(dtotList).isEqualTo(departmentList1);
        verify(departmentMapperDto, times(1)).mapToDepartmentDto(department);
        verify(departmentMapperDto, times(1)).mapToDepartmentDto(department1);
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void shouldGetDepartmentByIdIfIdValid() {
        //given
        Department department = new Department(1L, "Department");
        DepartmentDto dto = new DepartmentDto(1L, "Department");

        when(departmentRepository.findById(1L)).thenReturn(java.util.Optional.of(department));
        when(departmentMapperDto.mapToDepartmentDto(department)).thenReturn(dto);

        //when
        departmentService.getDepartmentById(1L);

        //than
        verify(departmentRepository, times(1)).findById(1L);
        verify(departmentMapperDto, times(1)).mapToDepartmentDto(department);

    }

    @Test
    //ловим оибки исправить// incorrect test!!!
    void shouldThrowWhenGetDepartmentByIdIfIdInvalid() {
        //when
        Mockito.when(departmentRepository.findById(null)).thenThrow(new DepartmentNotFoundException(null));
        Throwable thrown = assertThrows(DepartmentNotFoundException.class, () -> {
            departmentService.getDepartmentById(null);
        });
        //than
        Mockito.verify(departmentRepository).findById(null);
        assertTrue(thrown.getMessage().contains("Couldn't find department with id"));
    }

    @Test
    void shouldSaveDepartmentAndReturnSaveDepartment() {
        //given
        Department department = new Department(null, "Main Department");
        DepartmentDto dto = new DepartmentDto(null, "Main Department");

        when(departmentMapperDto.mapToDepartmentEntity(dto)).thenReturn(department);
        when(departmentRepository.save(department)).thenReturn(department);

        //when
        departmentService.save(dto);

        //than
        verify(departmentRepository, times(1)).save(department);
        verify(departmentMapperDto, times(1)).mapToDepartmentEntity(dto);
        verify(departmentMapperDto, times(0)).mapToDepartmentDto(any());
        //проверить

    }

    @Test
    void shouldRemoveDepartmentByIdIfItExistInDataBase() {
        //when
        when(departmentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(departmentRepository).deleteById(1L);
        departmentService.deleteById(1L);

        //than
        verify(departmentRepository, times(1)).deleteById(1L);

    }

    @Test
    void shouldFailWhenRemoveDepartmentByIdIfItNotExistInDataBase() {
        //when
        when(departmentRepository.existsById(1L)).thenReturn(false);

        //than
        assertThatThrownBy(() -> departmentService.deleteById(1L))
                .isInstanceOf(DepartmentNotFoundException.class);
    }

    @Test
    void shouldReturnTrueIfDepartmentIsExistById() {
        //when
        when(departmentRepository.existsById(1L)).thenReturn(true);

        //than
        verify(departmentRepository).existsById(1L);
    }
}