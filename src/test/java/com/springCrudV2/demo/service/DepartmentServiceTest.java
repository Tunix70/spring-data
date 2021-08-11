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
        when(departmentMapper.mapToDepartmentDto(department)).thenReturn(dto);
        when(departmentMapper.mapToDepartmentDto(department1)).thenReturn(dto1);

        //when
        List<DepartmentDto> departmentList1 = departmentService.getAll();

        //than
        assertThat(dtotList).isEqualTo(departmentList1);
        verify(departmentMapper, times(1)).mapToDepartmentDto(department);
        verify(departmentMapper, times(1)).mapToDepartmentDto(department1);
        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    void shouldGetDepartmentByIdIfIdValid() {
        //given
        Department department = new Department(1L, "Department");
        DepartmentDto dto = new DepartmentDto(1L, "Department");

        when(departmentRepository.findById(1L)).thenReturn(java.util.Optional.of(department));
        when(departmentMapper.mapToDepartmentDto(department)).thenReturn(dto);

        //when
        departmentService.getDepartmentById(1L);

        //than
        verify(departmentRepository, times(1)).findById(1L);
        verify(departmentMapper, times(1)).mapToDepartmentDto(department);
    }

    @Test
    void shouldThrowWhenGetDepartmentByIdIfIdInvalid() {
        //when
        when(departmentRepository.findById(1L)).thenThrow(new DepartmentNotFoundException(1L));

        //than
        assertThatThrownBy(() -> departmentService.getDepartmentById(1L))
                .isInstanceOf(DepartmentNotFoundException.class);
        verify(departmentMapper, times(0)).mapToDepartmentDto(any());
        verify(departmentMapper, times(0)).mapToDepartmentEntity(any());
    }

    @Test
    void shouldSaveDepartmentAndReturnSaveDepartment() {
        //given
        Department department = new Department(null, "Main Department");
        DepartmentDto dto = new DepartmentDto(null, "Main Department");

        when(departmentMapper.mapToDepartmentEntity(dto)).thenReturn(department);
        when(departmentRepository.save(department)).thenReturn(department);

        //when
        departmentService.save(dto);

        //than
        verify(departmentRepository, times(1)).save(department);
        verify(departmentMapper, times(1)).mapToDepartmentEntity(dto);
        verify(departmentMapper, times(0)).mapToDepartmentDto(any());
    }

    @Test
    void shouldRemoveDepartmentByIdIfItExistInDataBase() {
        //when
        when(departmentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(departmentRepository).deleteById(1L);
        departmentService.deleteById(1L);

        //than
        verify(departmentRepository, times(1)).existsById(any());
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
        when(departmentRepository.existsById(any())).thenReturn(true);
        departmentService.isExistById(1L);

        //than
        verify(departmentRepository, times(1)).existsById(any());
    }
}