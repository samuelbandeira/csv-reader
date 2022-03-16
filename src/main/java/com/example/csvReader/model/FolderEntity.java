package com.example.csvReader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

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
    public Long id;
    public String name;
    public Long priority;
    @Transient
    public String pathName;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    public FolderEntity parent;
}
