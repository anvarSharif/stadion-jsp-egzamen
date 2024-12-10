package com.example.stadionjsp_modul8egzamen.servlets;

import com.example.stadionjsp_modul8egzamen.entity.Attachment;
import com.example.stadionjsp_modul8egzamen.entity.Region;
import com.example.stadionjsp_modul8egzamen.entity.Stadium;
import com.example.stadionjsp_modul8egzamen.repo.AttachmentRepo;
import com.example.stadionjsp_modul8egzamen.repo.RegionRepo;
import com.example.stadionjsp_modul8egzamen.repo.StadiumRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/stadium/add")
@MultipartConfig
public class StadiumAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part photo = req.getPart("photo");
        Attachment photoAttachment=AttachmentRepo.saveFile(photo.getSubmittedFileName(),photo.getInputStream().readAllBytes());
        String name = req.getParameter("name");
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer startTime = Integer.parseInt(req.getParameter("startTime"));
        Integer endTime = Integer.parseInt(req.getParameter("endTime"));
        String description = req.getParameter("description");
        Integer regionId = Integer.parseInt(req.getParameter("regionId"));

        RegionRepo regionRepo=new RegionRepo();
        Region region = regionRepo.findById(regionId).get();

        if (startTime>=endTime){
            resp.sendRedirect("/admin/addStadium.jsp");
            return;
        }

        Stadium stadium=new Stadium(
                name,
                startTime,
                endTime,
                region,
                price,
                description,
                photoAttachment
        );
        StadiumRepo stadiumRepo=new StadiumRepo();
        stadiumRepo.save(stadium);
        resp.sendRedirect("/admin/admin.jsp");
    }
}
