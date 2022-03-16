package com.example.csvReader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FolderDTO {

    public Long id;
    public String name;
    public Long priority;
    public String pathName;

    public FolderDTO parent;
}
