package com.example.stadionjsp_modul8egzamen.repo;

import com.example.stadionjsp_modul8egzamen.entity.Attachment;
import com.example.stadionjsp_modul8egzamen.entity.AttachmentContent;
import com.example.stadionjsp_modul8egzamen.entity.Stadium;
import jakarta.persistence.EntityManager;

import static com.example.stadionjsp_modul8egzamen.config.MyListener.emf;

public class AttachmentRepo extends BaseRepo<Attachment>{
    public static Attachment saveFile(String submittedFileName, byte[] bytes) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            Attachment attachment=new Attachment(submittedFileName);
            AttachmentContent attachmentContent=new AttachmentContent(attachment,bytes);
            entityManager.getTransaction().begin();
            entityManager.persist(attachment);
            entityManager.persist(attachmentContent);
            entityManager.getTransaction().commit();
            return attachment;
        }
    }
}
