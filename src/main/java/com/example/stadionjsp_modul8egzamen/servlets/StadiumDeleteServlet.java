package com.example.stadionjsp_modul8egzamen.servlets;

import com.example.stadionjsp_modul8egzamen.repo.StadiumRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stadium/delete")
public class StadiumDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer stadiumId = Integer.parseInt(req.getParameter("stadiumId"));
        StadiumRepo stadiumRepo=new StadiumRepo();
        stadiumRepo.deleteById(stadiumId);
        resp.sendRedirect("/admin/admin.jsp");
    }
}
