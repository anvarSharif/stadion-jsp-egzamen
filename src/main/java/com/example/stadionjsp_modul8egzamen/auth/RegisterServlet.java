package com.example.stadionjsp_modul8egzamen.auth;


import com.example.stadionjsp_modul8egzamen.entity.Role;
import com.example.stadionjsp_modul8egzamen.entity.User;
import com.example.stadionjsp_modul8egzamen.entity.enums.RoleName;
import com.example.stadionjsp_modul8egzamen.repo.RoleRepo;
import com.example.stadionjsp_modul8egzamen.repo.UserRepo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/auth/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phoneInp = req.getParameter("phone_inp");
        if (phoneInp != null) {
            String passwordInp = req.getParameter("password_inp");
            String passwordRepeatInp = req.getParameter("password_repeat_inp");
            if (!passwordRepeatInp.equals(passwordInp)) {
                resp.sendRedirect("/auth/register.jsp");
                return;
            }

            User user = new User(
                    phoneInp,
                    passwordInp
            );

            Map<String,String> map=new HashMap<>();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<User>> validateList = validator.validate(user);
            for (ConstraintViolation<User> violation : validateList) {
                map.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            if (!map.isEmpty()) {
                req.setAttribute("errors",map);
                req.getRequestDispatcher("/auth/register.jsp").forward(req,resp);
            }


            RoleRepo roleRepo=new RoleRepo();
            Role role=roleRepo.findByRoleName(RoleName.CUSTOMER);

            user.setRoles(new ArrayList<>(List.of(role)));
            UserRepo userRepo = new UserRepo();
            userRepo.save(user);

            resp.sendRedirect("/auth/login.jsp");
        }
    }
}
