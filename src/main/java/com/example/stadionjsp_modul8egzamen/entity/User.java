package com.example.stadionjsp_modul8egzamen.entity;

import com.example.stadionjsp_modul8egzamen.entity.abs.BaseEntity;
import com.example.stadionjsp_modul8egzamen.entity.enums.RoleName;
import jakarta.persistence.*;
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
    private String phone;
    private String password;
    private String fullName;

    public User(String phone, String password, String fullName) {
        this.phone = phone;
        this.password = password;
        this.fullName = fullName;
    }
}
