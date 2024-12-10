package com.example.stadionjsp_modul8egzamen.repo;

import com.example.stadionjsp_modul8egzamen.entity.Region;
import com.example.stadionjsp_modul8egzamen.entity.Role;
import com.example.stadionjsp_modul8egzamen.entity.User;
import com.example.stadionjsp_modul8egzamen.entity.enums.RoleName;
import jakarta.persistence.EntityManager;

import static com.example.stadionjsp_modul8egzamen.config.MyListener.emf;

public class RoleRepo extends BaseRepo<Role>{
    public Role findByRoleName(RoleName roleName) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
           return (Role) entityManager.createNativeQuery("select * from roles where role=:rolename", Role.class)
                   .setParameter("rolename",roleName.name())
                   .getSingleResult();
        }
    }
}
