package org.example.service;

import org.example.dto.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    ArrayList<Patient> patientsList=new ArrayList<>();

    public void addPatient(Patient patient){
        patientsList.add(patient);

    }

    public Optional<Patient> getPatientById(int id){
      return patientsList.stream().filter(p->p.getPatientId()==id).findFirst();
    }

    public Boolean deletePatient(int id){
          return patientsList.removeIf(p->p.getPatientId()==id);
    }

    public List<Patient> getAllPatients(){
          return patientsList;
    }

    public Boolean updatePatientById(int id,Patient newPatient){
       return getPatientById(id).map(existingPatient -> {  patientsList.remove(existingPatient);
                                                           patientsList.add(newPatient);
                                                     return true;

        }).orElse(false);
    }


}
