package com.example.stadionjsp_modul8egzamen.entity;

import com.example.stadionjsp_modul8egzamen.entity.abs.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookingHour extends BaseEntity {
    @ManyToOne
    private Stadium stadium;
    @ManyToOne
    private User user;
    private Integer bookedHour;
    private LocalDate date;
}
