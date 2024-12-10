package com.example.stadionjsp_modul8egzamen.auth;

import com.example.stadionjsp_modul8egzamen.entity.Role;
import com.example.stadionjsp_modul8egzamen.entity.User;
import com.example.stadionjsp_modul8egzamen.entity.enums.RoleName;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class Filter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        List<String> openPages = List.of(
                "/",
                "/home.jsp",
                "/booking.jsp",
                "/file/get",
                "/auth/login.jsp",
                "/auth/login",
                "/auth/register.jsp",
                "/auth/register",
                "/auth/logout",
                "/stadium/booking",
                "/static/bootstrap.min.css"
        );
        List<String> openPagesForCustomer = List.of(

        );
        List<String> openPagesForAdmin = List.of(
                "/admin/admin.jsp",
                "/admin/addStadium.jsp",
                "/admin/monthReport.jsp",
                "/stadium/update",
                "/stadium/delete",
                "/stadium/add"
        );

      /*  if (req.getRequestURI().contains("/admin")) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("currentUser");
            if (user == null || user.getRoles().stream().noneMatch(item -> item.getRoleName().equals(RoleName.ADMIN))) {
                res.sendRedirect("/auth/login.jsp");
                return;
            }
        }*/


        if (openPages.contains(req.getRequestURI())) {
            chain.doFilter(req, res);
            return;
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            if (openPagesForCustomer.contains(req.getRequestURI()) && user.getRoles().stream().anyMatch(item -> item.getRoleName().equals(RoleName.CUSTOMER)) ||
                    openPagesForAdmin.contains(req.getRequestURI()) && user.getRoles().stream().anyMatch(item -> item.getRoleName().equals(RoleName.ADMIN))) {
                chain.doFilter(req, res);
                return;
            }
        }
        res.sendRedirect("/auth/login.jsp");
    }
}
