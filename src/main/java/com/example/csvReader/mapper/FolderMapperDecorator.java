package com.example.csvReader.mapper;

import com.example.csvReader.dto.FolderDTO;
import com.example.csvReader.model.FolderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class FolderMapperDecorator implements FolderMapper {

    @Autowired
    @Qualifier("delegate")
    private FolderMapper delegate;

    @Override
    public FolderEntity toEntity(FolderDTO folderDTO) {
        final FolderEntity folderEntity = delegate.toEntity(folderDTO);

        if (folderEntity != null) {
            if (folderEntity.getParent() != null &&  folderEntity.getParent().getId() == null) {
                folderEntity.setParent(null);
            }
        }
        return folderEntity;
    }

}
