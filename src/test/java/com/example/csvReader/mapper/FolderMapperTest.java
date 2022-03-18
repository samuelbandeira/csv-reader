package com.example.csvReader.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.example.csvReader.CsvReaderApplication;
import com.example.csvReader.dto.FolderDTO;
import com.example.csvReader.helper.CsvHelper;
import com.example.csvReader.model.FolderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
class FolderMapperTest {

  @Autowired
  private FolderMapper folderMapper;

  @Test
  void givenFolderDto_whenMapper_thenFolderEntityParentNull() {
    FolderDTO folderDTO = FolderDTO.builder()
        .id(1L)
        .item_name("name")
        .priority(1L)
        .build();

    final FolderEntity folderEntity = folderMapper.toEntity(folderDTO);

    Assertions.assertEquals(1L, folderEntity.getId());
    Assertions.assertEquals("name", folderEntity.getName());
    Assertions.assertEquals(1L, folderEntity.getPriority());
    Assertions.assertNull(folderEntity.getParent());
  }

  @Test
  void givenFolderDtoParent_whenMapper_thenFolderDto() {
    FolderDTO folderDTO = FolderDTO.builder()
        .id(1L)
        .item_name("name")
        .priority(1L)
        .parent_id(1L)
        .build();

    final FolderEntity folderEntity = folderMapper.toEntity(folderDTO);

    Assertions.assertEquals(1L, folderEntity.getId());
    Assertions.assertEquals("name", folderEntity.getName());
    Assertions.assertEquals(1L, folderEntity.getPriority());
    Assertions.assertEquals(1L, folderEntity.getParent().getId());
  }

}