package com.example.csvReader.service;

import com.example.csvReader.exception.FolderException;
import com.example.csvReader.model.FolderEntity;
import com.example.csvReader.repository.FolderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {FolderService.class})
class FolderServiceTest {

    @Mock
    private FolderRepository folderRepository;
    @Autowired
    @InjectMocks
    private FolderService folderService;

    @Test
    void whenFolderIdIsDuplicated_thenThrowFolderException() {
        final FolderEntity folderEntity = FolderEntity.builder()
            .id(1L)
            .build();

        Mockito.when(folderRepository.existsById(1L)).thenReturn(true);

        FolderException thrown = assertThrows(
            FolderException.class,
            () -> folderService.save(folderEntity),
            "Expected folderService.save(folderEntity) to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("folder already exists"));
    }

    @Test
    void whenFolderParentDoNotExist_thenThrowFolderException() {
        final FolderEntity folderEntity = FolderEntity.builder()
            .id(1L)
            .parent(FolderEntity.builder().id(2L).build())
            .build();

        Mockito.when(folderRepository.existsById(1L)).thenReturn(false);
        Mockito.when(folderRepository.existsById(2L)).thenReturn(false);

        FolderException thrown = assertThrows(
            FolderException.class,
            () -> folderService.save(folderEntity),
            "Expected folderService.save(folderEntity) to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("could not find parent"));
    }

    @Test
    void whenFolderIsComplete_thenShouldReturnFolder() {
        final FolderEntity folderEntity = FolderEntity.builder()
            .id(1L)
            .parent(FolderEntity.builder().id(2L).build())
            .build();

        Mockito.when(folderRepository.existsById(1L)).thenReturn(false);
        Mockito.when(folderRepository.existsById(2L)).thenReturn(true);
        Mockito.when(folderRepository.save(folderEntity)).thenReturn(folderEntity);

        final FolderEntity save = folderService.save(folderEntity);
        assertEquals(folderEntity, save);
    }

}