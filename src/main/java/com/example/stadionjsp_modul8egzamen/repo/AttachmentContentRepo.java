package com.example.stadionjsp_modul8egzamen.repo;

import com.example.stadionjsp_modul8egzamen.entity.AttachmentContent;
import jakarta.persistence.EntityManager;

import static com.example.stadionjsp_modul8egzamen.config.MyListener.emf;


public class AttachmentContentRepo extends BaseRepo<AttachmentContent> {


    public static AttachmentContent FindByAttachmentId(int attachmentId) {
        try (
                EntityManager entityManager = emf.createEntityManager()
        ) {
            return entityManager.createQuery("from AttachmentContent where attachment.id=:attachmentId", AttachmentContent.class)
                    .setParameter("attachmentId", attachmentId)
                    .getSingleResult();
        }
    }
}
