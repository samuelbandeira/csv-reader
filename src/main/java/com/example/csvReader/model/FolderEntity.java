package com.example.csvReader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "folder")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "folder_seq", sequenceName = "folder_seq")
    private Long id;
    private String name;
    private Long priority;
    @Transient
    private String pathName;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private FolderEntity parent;

    public String getPathName() {
        if (parent == null) {
            return name;
        }
        return parent.getPathName() + "/" + getName();
    }
}
