package com.example.csvReader.mapper;

import com.example.csvReader.dto.FolderDTO;
import com.example.csvReader.model.FolderEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@DecoratedWith(FolderMapperDecorator.class)
public interface FolderMapper {

    @Mappings({
        @Mapping(target = "parent.id", source = "parent_id"),
        @Mapping(target = "name", source = "item_name"),
        @Mapping(target = "pathName", source = "path_name")
    })
    FolderEntity toEntity(FolderDTO folderDTO);

    @Mappings({
        @Mapping(target = "parent_id", source = "parent.id"),
        @Mapping(target = "item_name", source = "name"),
        @Mapping(target = "path_name", source = "pathName")
    })
    FolderDTO toDTO(FolderEntity folderEntity);
}
