package com.spring.hospital.service.Implementation;

import com.spring.hospital.entity.Consultation;
import com.spring.hospital.repository.AppointmentRepository;
import com.spring.hospital.repository.ConsultationRepository;
import com.spring.hospital.service.IConsultationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Transactional
public class ConsultationService implements IConsultationService {
    @Autowired
    private final ConsultationRepository consultationRepository;
    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation editConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public void deleteConsultation(Long consultationId) {
        consultationRepository.deleteById(consultationId);
    }

    @Override
    public List<Consultation> getConsultations() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation getOneConsultation(Long consultationId) {
        return consultationRepository.findById(consultationId).orElseThrow(EntityNotFoundException::new);
    }
}
