package com.spring.hospital.service;

import com.spring.hospital.entity.Consultation;

import java.util.List;

public interface IConsultationService {


            Consultation saveConsultation(Consultation consultation);
            Consultation editConsultation(Consultation consultation);
            void deleteConsultation(Long consultationId);

            List<Consultation> getConsultations();

            Consultation getOneConsultation(Long consultationId);


}
