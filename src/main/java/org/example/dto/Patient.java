package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    private int patientId;
    private String patientName;
    private int age;
    private String gender;
    private String address;
    private String mobile;
    private String email;
}
