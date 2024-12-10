package com.example.stadionjsp_modul8egzamen.servlets;


import com.example.stadionjsp_modul8egzamen.entity.AttachmentContent;
import com.example.stadionjsp_modul8egzamen.entity.BookingHour;
import com.example.stadionjsp_modul8egzamen.entity.Stadium;
import com.example.stadionjsp_modul8egzamen.entity.User;
import com.example.stadionjsp_modul8egzamen.repo.AttachmentContentRepo;
import com.example.stadionjsp_modul8egzamen.repo.BookingHourRepo;
import com.example.stadionjsp_modul8egzamen.repo.StadiumRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@WebServlet("/stadium/booking")
@MultipartConfig
public class BookingStadiumServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StadiumRepo stadiumRepo = new StadiumRepo();
        BookingHourRepo bookingHourRepo = new BookingHourRepo();
        HttpSession session = req.getSession();
        LocalDate date = LocalDate.now();

        User currentUser = (User) session.getAttribute("currentUser");
        String stadiumIdStr = req.getParameter("stadiumId");
        String[] bookedHours = req.getParameterValues("bookedHours");



        if (currentUser == null) {
            session.setAttribute("bookedHours", bookedHours);
            session.setAttribute("stadiumId", stadiumIdStr);
            resp.sendRedirect("/auth/login.jsp");
            return;
        }
        if (stadiumIdStr==null){
            stadiumIdStr=(String) req.getAttribute("stadiumId");
            bookedHours =(String[]) req.getAttribute("bookedHours");
        }
        Integer stadiumId = Integer.parseInt(stadiumIdStr);
        Optional<Stadium> optionalStadium = stadiumRepo.findById(stadiumId);
        if (bookedHours==null){
            resp.sendRedirect("/home.jsp");
            return;
        }
        for (String hour : bookedHours) {
            BookingHour bookingHour = new BookingHour(
                    optionalStadium.get(),
                    currentUser,
                    Integer.parseInt(hour),
                    date
            );
            bookingHourRepo.save(bookingHour);
        }


        resp.sendRedirect("/home.jsp");
    }


    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StadiumRepo stadiumRepo = new StadiumRepo();
        BookingHourRepo bookingHourRepo = new BookingHourRepo();
        HttpSession session = req.getSession();
        LocalDate date = LocalDate.now();

        User currentUser = (User) session.getAttribute("currentUser");
        String stadiumIdStr = req.getParameter("stadiumId");
        String[] bookedHours = req.getParameterValues("bookedHours");

        if (currentUser == null) {
            session.setAttribute("bookedHours", bookedHours);
            session.setAttribute("stadiumId", stadiumIdStr);
            resp.sendRedirect("/auth/login.jsp");
            return;
        }

        String[] bookedHoursSession = (String[]) session.getAttribute("bookedHours");
        String stadiumIdSession = (String) session.getAttribute("stadiumId");

        if (stadiumIdSession!=null){
            Integer stadiumId = Integer.parseInt(stadiumIdSession);
            Optional<Stadium> optionalStadium = stadiumRepo.findById(stadiumId);
            for (String hour : bookedHoursSession) {
                BookingHour bookingHour = new BookingHour(
                        optionalStadium.get(),
                        currentUser,
                        Integer.parseInt(hour),
                        date
                );
                bookingHourRepo.save(bookingHour);
            }
        }

        resp.sendRedirect("/home.jsp");
    }*/


}
