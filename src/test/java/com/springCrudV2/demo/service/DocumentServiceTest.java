package com.springCrudV2.demo.service;

import com.springCrudV2.demo.dao.DocumentRepository;
import com.springCrudV2.demo.dto.DocumentDto;
import com.springCrudV2.demo.entity.Document;
import com.springCrudV2.demo.exception.DocumentNotFoundException;
import com.springCrudV2.demo.mapper.DocumentMapper;
import com.springCrudV2.demo.validator.DocumentDtoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {
    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @Mock
    private DocumentDtoValidator documentDtoValidator;

    @InjectMocks
    private DocumentService documentService;

    private Document document;
    private Document document1;
    private DocumentDto dto;
    private DocumentDto dto1;

    @BeforeEach
    void setUp() {
        document = new Document("wer-123-fsd", new Date(12335L));
        document1 = new Document("qwe-456-hff", new Date(909822L));
        dto = new DocumentDto("wer-123-fsd", new Date(12335L));
        dto1 = new DocumentDto("qwe-456-hff", new Date(909822L));
    }

    @Test
    void shouldGetAllListDocument() {
        //given
        List<Document> documentList = new ArrayList<>();
        documentList.add(document);
        documentList.add(document1);

        List<DocumentDto> dtotList = new ArrayList<>();
        dtotList.add(dto);
        dtotList.add(dto1);

        when(documentRepository.findAll()).thenReturn(documentList);
        when(documentMapper.mapToDocumentDto(document)).thenReturn(dto);
        when(documentMapper.mapToDocumentDto(document1)).thenReturn(dto1);

        //when
        List<DocumentDto> resultList = documentService.getAll();

        //than
        assertThat(dtotList).isEqualTo(resultList);
        verify(documentMapper, times(1)).mapToDocumentDto(document);
        verify(documentMapper, times(1)).mapToDocumentDto(document1);
        verify(documentRepository, times(1)).findAll();
    }

    @Test
    void shouldGetDocumentByIdIfIdValid() {
        when(documentRepository.findById("wer-123-fsd")).thenReturn(java.util.Optional.of(document));
        when(documentMapper.mapToDocumentDto(document)).thenReturn(dto);

        //when
        documentService.getDocumentById("wer-123-fsd");

        //than
        verify(documentRepository, times(1)).findById("wer-123-fsd");
        verify(documentMapper, times(1)).mapToDocumentDto(document);
    }

    @Test
    void shouldThrowWhenGetDocumentByIdIfIdInvalid() {
        //when
        when(documentRepository.findById("qwe-111")).thenThrow(new DocumentNotFoundException("qwe-111"));

        //than
        assertThatThrownBy(() -> documentService.getDocumentById("qwe-111"))
                .isInstanceOf(DocumentNotFoundException.class);
        verify(documentMapper, times(0)).mapToDocumentEntity(any());
        verify(documentMapper, times(0)).mapToDocumentDto(any());
    }

    @Test
    void shouldSaveDocumentAndReturnSaveDocument() {
        //given
        when(documentMapper.mapToDocumentEntity(dto)).thenReturn(document);
        when(documentRepository.save(document)).thenReturn(document);

        //when
        documentService.save(dto);

        //than
        verify(documentRepository, times(1)).save(document);
        verify(documentMapper, times(1)).mapToDocumentEntity(dto);
        verify(documentMapper, times(0)).mapToDocumentDto(any());
    }

    @Test
    void shouldRemoveDocumentByIdIfItExistInDataBase() {
        when(documentRepository.existsById("123-eqw-33")).thenReturn(true);
        doNothing().when(documentRepository).deleteById("123-eqw-33");

        //when
        documentService.deleteByNumber("123-eqw-33");

        //than
        verify(documentRepository, times(1)).existsById(any());
        verify(documentRepository, times(1)).deleteById("123-eqw-33");
    }

    @Test
    void shouldFailWhenRemoveDepartmentByIdIfItNotExistInDataBase() {
        //when
        when(documentRepository.existsById("876-asd")).thenReturn(false);

        //than
        assertThatThrownBy(() -> documentService.deleteByNumber("876-asd"))
                .isInstanceOf(DocumentNotFoundException.class);
    }

    @Test
    void validate() {
//        //given
//        DataBinder dataBinder = new DataBinder(dto);
//        dataBinder.addValidators(documentDtoValidator);
//        dataBinder.validate();
//
//        assertTrue(dataBinder.getBindingResult().hasErrors());
//
//        if(dataBinder.getBindingResult().hasErrors()) {
//            dataBinder.getBindingResult().getAllErrors().
//                    forEach(e -> System.out.println(messageSource
//                            .getMessage(e, Locale.getDefault())));
//        }
    }


    @Test
    void shouldReturnTrueIfDocumentIsExistById() {
        //when
        when(documentRepository.existsById(any())).thenReturn(true);
        documentService.isExistById(any());

        //than
        verify(documentRepository, times(1)).existsById(any());
    }
}