package com.example.stadionjsp_modul8egzamen.entity;

import com.example.stadionjsp_modul8egzamen.entity.abs.BaseEntity;
import com.example.stadionjsp_modul8egzamen.entity.enums.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @ManyToMany
    private List<Role> roles=new ArrayList<>();
    @NotBlank(message = "phone bo'sh!")
    @Size(min = 9, max = 9, message = "Telefon raqami 9 ta raqamdan iborat bo'lishi kerak!")
    private String phone;
    @Size(min = 8, max = 16,message = "password 8 tadan 16 tagacha bo'lishi kerak!")
    private String password;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
