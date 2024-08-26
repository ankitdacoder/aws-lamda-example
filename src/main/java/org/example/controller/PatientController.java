package org.example.controller;

import org.example.dto.Patient;
import org.example.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping(produces = "application/json",consumes = "application/json")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patientReq){
        patientService.addPatient(patientReq);
        return new ResponseEntity<>(patientReq, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Patient>> getAllPatients(){
        return new ResponseEntity<>(patientService.getAllPatients(),HttpStatus.OK);
    }

    @GetMapping(value="/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") int id){
       Optional<Patient> optionalPatient= patientService.getPatientById(id);
       return optionalPatient.map(value->new ResponseEntity<>(value,HttpStatus.OK))
               .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") int id){
        Boolean deleted = patientService.deletePatient(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<Patient> updatePatientDetails(@PathVariable("id") int id ,@RequestBody Patient newPatientReq){
        Boolean updated = patientService.updatePatientById(id, newPatientReq);
        if (updated) {
            return new ResponseEntity<>(newPatientReq, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
