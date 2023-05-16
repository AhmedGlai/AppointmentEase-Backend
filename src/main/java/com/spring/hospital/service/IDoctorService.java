package com.spring.hospital.service;

import com.spring.hospital.dto.DoctorDTO;

import java.util.List;

public interface IDoctorService {


        DoctorDTO saveDoctor(DoctorDTO doctor);
        DoctorDTO editDoctor(DoctorDTO doctor);
        void deleteDoctor(Long doctorId);
        List<DoctorDTO>getAllDoctors();
        public List<DoctorDTO> saveDoctors(List<DoctorDTO> doctors);
        DoctorDTO getOneDoctor(Long doctorID);

        List<DoctorDTO> getDoctorsBySpecialtyId(Long specialtyId);


}
