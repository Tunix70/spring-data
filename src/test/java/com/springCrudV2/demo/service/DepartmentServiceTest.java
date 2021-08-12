package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DepartmentRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.exception.DepartmentNotFoundException;
import com.springCrudV2.demo.mapper.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    private final Long ID1 = 1L;
    private final Long ID2 = 2L;
    private final String NAME1 = "Department";
    private final String NAME2 = "New Department";

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void shouldGetAllListDepartment() {
        //given
        List<Department> departmentList = new ArrayList<>();
        Department department = new Department(ID1, NAME1);
        Department department1 = new Department(ID2, NAME2);
        departmentList.add(department);
        departmentList.add(department1);

        List<DepartmentDto> dtotList = new ArrayList<>();
        DepartmentDto dto = new DepartmentDto(ID1, NAME1);
        DepartmentDto dto1 = new DepartmentDto(ID2, NAME2);
        dtotList.add(dto);
        dtotList.add(dto1);

        //when
        when(departmentRepository.findAll()).thenReturn(departmentList);
        when(departmentMapper.mapToDepartmentDto(department)).thenReturn(dto);
        when(departmentMapper.mapToDepartmentDto(department1)).thenReturn(dto1);
        List<DepartmentDto> result = departmentService.getAll();

        //than
        assertThat(dtotList).isEqualTo(result);
        verify(departmentMapper, times(1)).mapToDepartmentDto(department);
        verify(departmentMapper, times(0)).mapToDepartmentEntity(any());
        verify(departmentMapper, times(1)).mapToDepartmentDto(department1);
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void shouldGetDepartmentByIdIfIdValid() {
        //given
        Department department = new Department(ID1, NAME1);
        DepartmentDto expected = new DepartmentDto(ID1, NAME1);

        //when
        when(departmentRepository.findById(ID1)).thenReturn(java.util.Optional.of(department));
        when(departmentMapper.mapToDepartmentDto(department)).thenReturn(expected);
        DepartmentDto result = departmentService.getDepartmentById(ID1);

        //than
        assertThat(result).isEqualTo(expected);
        verify(departmentRepository, times(1)).findById(ID1);
        verify(departmentMapper, times(1)).mapToDepartmentDto(department);
        verify(departmentMapper, times(0)).mapToDepartmentEntity(any());
    }

    @Test
    void shouldThrowWhenGetDepartmentByIdIfIdInvalid() {
        //when
        when(departmentRepository.findById(ID1)).thenReturn(java.util.Optional.empty());

        //than
        assertThatThrownBy(() -> departmentService.getDepartmentById(ID1))
                .isInstanceOf(DepartmentNotFoundException.class);
        verify(departmentMapper, times(0)).mapToDepartmentDto(any());
        verify(departmentMapper, times(0)).mapToDepartmentEntity(any());
    }

    @Test
    void shouldSaveDepartmentAndReturnSaveDepartment() {
        //given
        Department department = new Department(null, NAME1);
        DepartmentDto expected = new DepartmentDto(null, NAME1);

        //when
        when(departmentMapper.mapToDepartmentEntity(expected)).thenReturn(department);
        when(departmentRepository.save(department)).thenReturn(department);
        DepartmentDto result = departmentService.save(expected);

        //than
        assertThat(result).isEqualTo(expected);
        verify(departmentRepository, times(1)).save(department);
        verify(departmentMapper, times(1)).mapToDepartmentEntity(expected);
        verify(departmentMapper, times(0)).mapToDepartmentDto(any());
    }

    @Test
    void shouldRemoveDepartmentByIdIfItExistInDataBase() {
        //when
        when(departmentRepository.existsById(ID1)).thenReturn(true);
        departmentService.deleteById(ID1);

        //than
        verify(departmentRepository, times(1)).existsById(any());
        verify(departmentRepository, times(1)).deleteById(ID1);
    }

    @Test
    void shouldFailWhenRemoveDepartmentByIdIfItNotExistInDataBase() {
        //when
        when(departmentRepository.existsById(ID1)).thenReturn(false);

        //than
        assertThatThrownBy(() -> departmentService.deleteById(ID1))
                .isInstanceOf(DepartmentNotFoundException.class);
    }

    @Test
    void shouldReturnTrueIfDepartmentIsExistById() {
        //when
        when(departmentRepository.existsById(any())).thenReturn(true);
        departmentService.isExistById(ID1);

        //than
        verify(departmentRepository, times(1)).existsById(any());
    }
}