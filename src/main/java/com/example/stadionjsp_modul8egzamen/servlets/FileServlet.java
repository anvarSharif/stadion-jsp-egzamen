package com.example.stadionjsp_modul8egzamen.servlets;


import com.example.stadionjsp_modul8egzamen.entity.AttachmentContent;
import com.example.stadionjsp_modul8egzamen.repo.AttachmentContentRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/file/get")
@MultipartConfig
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int attachmentId = Integer.parseInt(req.getParameter("id"));
        AttachmentContent attachmentContent = AttachmentContentRepo.FindByAttachmentId(attachmentId);
        resp.getOutputStream().write(attachmentContent.getContent());
    }
}
