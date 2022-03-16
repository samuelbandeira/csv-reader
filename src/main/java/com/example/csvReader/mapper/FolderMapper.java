package com.example.csvReader.mapper;

import com.example.csvReader.dto.FolderDTO;
import com.example.csvReader.model.FolderEntity;
import org.mapstruct.Mapper;

@Mapper
public interface FolderMapper {

    FolderEntity toEntity(FolderDTO folderDTO);
    FolderDTO toDTO(FolderEntity folderEntity);
}
