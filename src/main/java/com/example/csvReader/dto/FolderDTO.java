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
    public String item_name;
    public Long priority;
    public String path_name;

    public Long parent_id;
}
