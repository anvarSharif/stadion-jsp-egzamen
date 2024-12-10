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

@WebServlet("/stadium/update")
@MultipartConfig
public class StadiumUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StadiumRepo stadiumRepo = new StadiumRepo();

        Part photo = req.getPart("photo");
        Attachment photoAttachment=null;
        Integer stadiumId = Integer.parseInt(req.getParameter("stadiumId"));
        if (photo.getSize()!=0){
            photoAttachment=AttachmentRepo.saveFile(photo.getSubmittedFileName(),photo.getInputStream().readAllBytes());
        }else {
            Optional<Stadium> optionalStadium = stadiumRepo.findById(stadiumId);
            if (optionalStadium.isPresent()) {
                photoAttachment=optionalStadium.get().getAttachment();
            }
        }

        String name = req.getParameter("name");
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer startTime = Integer.parseInt(req.getParameter("startTime"));
        Integer endTime = Integer.parseInt(req.getParameter("endTime"));
        String description = req.getParameter("description");
        Integer regionId = Integer.parseInt(req.getParameter("regionId"));

        RegionRepo regionRepo = new RegionRepo();
        Region region = regionRepo.findById(regionId).get();

        Stadium stadium = Stadium.builder()
                .id(stadiumId)
                .name(name)
                .startTime(startTime)
                .endTime(endTime)
                .region(region)
                .price(price)
                .description(description)
                .attachment(photoAttachment)
                .build();
        stadiumRepo.update(stadium);
        resp.sendRedirect("/admin/admin.jsp");
    }
}
