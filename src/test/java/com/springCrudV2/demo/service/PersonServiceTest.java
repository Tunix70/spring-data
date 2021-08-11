package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.PersonRepository;
import com.springCrudV2.demo.dto.DepartmentDto;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.dto.LanguageDto;
import com.springCrudV2.demo.dto.PersonDto;
import com.springCrudV2.demo.entity.Department;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.entity.Language;
import com.springCrudV2.demo.entity.Person;
import com.springCrudV2.demo.exception.DepartmentNotFoundException;
import com.springCrudV2.demo.exception.DocumentNotFoundException;
import com.springCrudV2.demo.exception.LanguageNotFoundException;
import com.springCrudV2.demo.exception.PersonNotFoundException;
import com.springCrudV2.demo.mapper.PersonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private DocumentService documentService;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private LanguageService languageService;

    @InjectMocks
    private PersonService personService;


    private Person person;
    private Person person1;
    private PersonDto personDto;
    private PersonDto personDto1;
    private Set<Language> languageSet = new HashSet<>();
    private Set<Language> languageSet1 = new HashSet<>();
    private Set<Long> languageSetDto = new HashSet<>();
    private Set<Long> languageSetDto1 = new HashSet<>();
    private Set<LanguageDto> dtoSet;
    private Department department;
    private Department department1;
    private DepartmentDto departmentDto;
    private Document document;
    private Document document1;
    private DocumentDto documentDto;

    @BeforeEach
    void setUp() {
        department = new Department(1L, "Department");
        department1 = new Department(2L, "Main Department");

        document = new Document("wer-123-fsd", new Date(12335L));
        document1 = new Document("sdf-423-qqq", new Date(12443L));

        Language language = new Language(1L, "RU");
        Language language1 = new Language(2L,"EU");
        Language language2 = new Language(2L,"IT");

        languageSet.add(language);
        languageSet.add(language1);
        languageSet1.add(language1);
        languageSet1.add(language2);

        languageSetDto.add(language.getId());
        languageSetDto.add(language1.getId());
        languageSetDto1.add(language1.getId());
        languageSetDto1.add(language2.getId());

        person = new Person(1L, "Pavel", "Morozov", new Date(1234L), department, languageSet, document);
        person1 = new Person(2L, "Oleg", "Grinkov", new Date(1323L), department1, languageSet1, document1);
        personDto = new PersonDto(1L, "Pavel", "Morozov", new Date(1234L), department.getId(), languageSetDto, document.getId());
        personDto1 = new PersonDto(2L, "Oleg", "Grinkov", new Date(1323L), department1.getId(), languageSetDto1, document1.getId());
    }

    @Test
    void shouldGetAllListPerson() {
        //given
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(person);
        listPerson.add(person1);

        List<PersonDto> listDtoPerson = new ArrayList<>();
        listDtoPerson.add(personDto);
        listDtoPerson.add(personDto1);

        when(personRepository.findAll()).thenReturn(listPerson);
        when(personMapper.mapToPersonDto(person)).thenReturn(personDto);
        when(personMapper.mapToPersonDto(person1)).thenReturn(personDto1);

        //when
        List<PersonDto> resultList = personService.getAll();

        //than
        assertThat(resultList).isEqualTo(listDtoPerson);
        verify(personMapper, times(0)).mapToPersonEntity(any());
        verify(personMapper, times(2)).mapToPersonDto(any());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void shouldGetPeronByIdIfIdValid() {
        //given
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(person));
        when(personMapper.mapToPersonDto(person)).thenReturn(personDto);

        //when
        personService.getPersonById(1L);

        //than
        verify(personRepository, times(1)).findById(1L);
        verify(personMapper, times(1)).mapToPersonDto(person);
    }

    @Test
    void shouldThrowWhenGetPersonByIdIfIdInvalid() {
        //when
        when(personRepository.findById(1L)).thenThrow(new PersonNotFoundException(1L));

        //than
        assertThatThrownBy(() -> personService.getPersonById(1L))
                .isInstanceOf(PersonNotFoundException.class);
        verify(personMapper, times(0)).mapToPersonDto(any());
        verify(personMapper, times(0)).mapToPersonEntity(any());
    }

    @Test
    void shouldSavePersonAndReturnSavePerson() {
        //given
        when(personMapper.mapToPersonEntity(personDto)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(documentService.isExistById(any())).thenReturn(true);
        when(languageService.isExistById(any())).thenReturn(true);
        when(departmentService.isExistById(any())).thenReturn(true);

        //when
        personService.save(personDto);

        //than
        verify(personRepository, times(1)).save(person);
        verify(personMapper, times(1)).mapToPersonEntity(personDto);
        verify(personMapper, times(0)).mapToPersonDto(any());
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(2)).isExistById(any());
        verify(departmentService, times(1)).isExistById(any());
    }

    @Test
    void shouldRemovePersonByIdIfItExistInDataBase() {
        //when
        doNothing().when(personRepository).deleteById(1L);
        personService.deleteById(1L);

        //than
        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldFillAllFieldsToPersonFromDtoAndRetutnPerson() {
        //when
        when(personMapper.mapToPersonEntity(personDto)).thenReturn(person);
        when(documentService.getDocumentById(personDto.getDocument())).thenReturn(documentDto);
        when(documentService.getEntity(documentDto)).thenReturn(document);
        when(departmentService.getDepartmentById(personDto.getDepartment())).thenReturn(departmentDto);
        when(departmentService.getEntity(departmentDto)).thenReturn(department);


        Person person = personService.fillFieldsPerson(personDto);

        //than
        assertThat(person.getId()).isEqualTo(personDto.getId());
        assertThat(person.getFirst_name()).isEqualTo(personDto.getFirst_name());
        assertThat(person.getSecond_name()).isEqualTo(personDto.getSecond_name());
        assertThat(person.getBirthday()).isEqualTo(personDto.getBirthday());
        assertThat(person.getDocument()).isEqualTo(documentService.getEntity(documentDto));
        assertThat(person.getDepartment()).isEqualTo(departmentService.getEntity(departmentDto));

        verify(personMapper,times(0)).mapToPersonDto(person);
        verify(personMapper,times(1)).mapToPersonEntity(personDto);
        verify(documentService, times(1)).getDocumentById(any());
        verify(documentService, times(2)).getEntity(documentDto);
        verify(departmentService, times(1)).getDepartmentById(any());
        verify(departmentService, times(2)).getEntity(departmentDto);
        verify(languageService,times(2)).getLanguageById(any());
        verify(languageService,times(2)).getEntity(any());

    }

    @Test
    void shouldTrueIfAllDtoInPersonIsValid() {
        //when
        when(documentService.isExistById(any())).thenReturn(true);
        when(languageService.isExistById(any())).thenReturn(true);
        when(departmentService.isExistById(any())).thenReturn(true);

        boolean result = personService.isValidDto(personDto);

        //than
        assertThat(result).isEqualTo(true);
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(2)).isExistById(any());
        verify(departmentService, times(1)).isExistById(any());
    }

    @Test
    void shouldThrowExceptionIfDocumentInPersonIsInvalid() {
        //when
        when(documentService.isExistById(any())).thenReturn(false);

        //than
        assertThatThrownBy(() -> personService.isValidDto(personDto))
                .isInstanceOf(DocumentNotFoundException.class);
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(0)).isExistById(any());
        verify(departmentService, times(0)).isExistById(any());
    }

    @Test
    void shouldThrowExceptionIfDepartmentInPersonIsInvalid() {
        //when
        when(documentService.isExistById(any())).thenReturn(true);
        when(departmentService.isExistById(any())).thenReturn(false);

        //than
        assertThatThrownBy(() -> personService.isValidDto(personDto))
                .isInstanceOf(DepartmentNotFoundException.class);
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(0)).isExistById(any());
        verify(departmentService, times(1)).isExistById(any());
    }

    @Test
    void shouldThrowExceptionIfLanguageInPersonIsInvalid() {
        //when
        when(documentService.isExistById(any())).thenReturn(true);
        when(departmentService.isExistById(any())).thenReturn(true);
        when(languageService.isExistById(any())).thenReturn(false);

        //than
        assertThatThrownBy(() -> personService.isValidDto(personDto))
                .isInstanceOf(LanguageNotFoundException.class);
        verify(documentService, times(1)).isExistById(any());
        verify(languageService, times(1)).isExistById(any());
        verify(departmentService, times(1)).isExistById(any());
    }
}