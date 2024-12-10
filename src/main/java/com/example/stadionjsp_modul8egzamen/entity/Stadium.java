package com.example.stadionjsp_modul8egzamen.entity;

import com.example.stadionjsp_modul8egzamen.entity.abs.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Stadium extends BaseEntity {
    private String name;
    private Integer startTime;
    private Integer endTime;
    @ManyToOne
    private Region region;
    private Integer price;
    private String description;
    @ManyToOne
    private Attachment attachment;
}
