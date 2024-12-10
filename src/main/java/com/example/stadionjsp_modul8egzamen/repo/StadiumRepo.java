package com.example.stadionjsp_modul8egzamen.repo;

import com.example.stadionjsp_modul8egzamen.entity.BookingHour;
import com.example.stadionjsp_modul8egzamen.entity.Stadium;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.stadionjsp_modul8egzamen.config.MyListener.emf;

public class StadiumRepo extends BaseRepo<Stadium>{
    public List<Stadium> findAllByRegionId(int regionId) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            return entityManager.createNativeQuery("select * from stadium where region_id=:regionIdjon", Stadium.class)
                    .setParameter("regionIdjon", regionId)
                    .getResultList();
        }
    }

    public Integer findCountGamesForMonth(Integer stadiumId) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            Optional<Stadium> optionalStadium = findById(stadiumId);
            LocalDate now = LocalDate.now();
            LocalDate startOfMonth = now.withDayOfMonth(1);
            LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());

            return  (Integer) entityManager.createNativeQuery(
                            "SELECT COUNT(b.id) FROM bookinghour b WHERE stadium_id = :stadiumId AND date BETWEEN :startDate AND :endDate", Integer.class)
                    .setParameter("stadiumId", stadiumId)
                    .setParameter("startDate", startOfMonth)
                    .setParameter("endDate", endOfMonth)
                    .getSingleResult();
        }
    }
}
