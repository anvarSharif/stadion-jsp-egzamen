package com.example.stadionjsp_modul8egzamen.auth;


import com.example.stadionjsp_modul8egzamen.entity.User;
import com.example.stadionjsp_modul8egzamen.entity.enums.RoleName;
import com.example.stadionjsp_modul8egzamen.repo.UserRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneInp = req.getParameter("phone_inp");
        if (phoneInp != null) {
            String passwordInp = req.getParameter("password_inp");
            UserRepo userRepo=new UserRepo();
            Optional<User> optionalUser =userRepo.findAll().stream().filter(item -> item.getPassword().equals(passwordInp)
                    && item.getPhone().equals(phoneInp)).findFirst();


            if (optionalUser.isPresent()) {
                User currentUser = userRepo.findUserWithRoles(optionalUser.get().getId());
                HttpSession session = req.getSession();
                session.setAttribute("currentUser",currentUser);

                if (session.getAttribute("stadiumId")!=null){
                    System.out.println(session.getAttribute("stadiumId"));

                    String[] bookedHoursSession = (String[]) session.getAttribute("bookedHours");
                    String stadiumIdSession = (String) session.getAttribute("stadiumId");

                    req.setAttribute("bookedHours",bookedHoursSession);
                    req.setAttribute("stadiumId",stadiumIdSession);

                    RequestDispatcher dispatcher = req.getRequestDispatcher("/stadium/booking");
                    dispatcher.forward(req, resp);
                    return;
                }


                if (currentUser.getRoles().stream().anyMatch(item->item.getRoleName().equals(RoleName.ADMIN))){
                    resp.sendRedirect("/admin/admin.jsp");
                    return;
                }
                resp.sendRedirect("/home.jsp");
                return;
            }
        }
        resp.sendRedirect("/auth/login.jsp");
    }
}
