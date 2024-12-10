package com.example.stadionjsp_modul8egzamen.repo;

import com.example.stadionjsp_modul8egzamen.entity.BookingHour;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.stadionjsp_modul8egzamen.config.MyListener.emf;

public class BookingHourRepo extends BaseRepo<BookingHour>{

    public static List<Integer> findByStadiumIdAndCurrentUserId(Integer stadiumId) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            LocalDate now = LocalDate.now();
            List<BookingHour> bookingHourList = entityManager.createNativeQuery("select * from bookinghour where stadium_id=:stadiumId and date =:selectDate", BookingHour.class)
                    .setParameter("stadiumId", stadiumId)
                    .setParameter("selectDate", now)
                    .getResultList();
            return bookingHourList.stream()
                    .map(BookingHour::getBookedHour)
                    .collect(Collectors.toList());
        }
    }
}










