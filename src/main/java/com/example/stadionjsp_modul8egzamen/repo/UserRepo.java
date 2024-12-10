package com.example.stadionjsp_modul8egzamen.repo;

import com.example.stadionjsp_modul8egzamen.entity.Attachment;
import com.example.stadionjsp_modul8egzamen.entity.AttachmentContent;
import com.example.stadionjsp_modul8egzamen.entity.BookingHour;
import com.example.stadionjsp_modul8egzamen.entity.User;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.stadionjsp_modul8egzamen.config.MyListener.emf;

public class UserRepo extends BaseRepo<User>{
    public User findUserWithRoles(Integer userId) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            User user = entityManager.find(User.class, userId);
            if (user != null) {
                user.getRoles().size(); // Lazy kolleksiyani yuklash uchun
            }
            return user;
        }
    }
}
