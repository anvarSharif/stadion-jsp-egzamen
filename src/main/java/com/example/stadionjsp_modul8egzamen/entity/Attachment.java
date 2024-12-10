package com.example.stadionjsp_modul8egzamen.entity;

import com.example.stadionjsp_modul8egzamen.entity.abs.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Attachment extends BaseEntity {
    private String name;
}
