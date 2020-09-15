package ru.netology.patient.service.medical;

import java.math.BigDecimal;

import ru.netology.patient.entity.BloodPressure;

public interface MedicalService {

    boolean checkBloodPressure(String patientId, BloodPressure bloodPressure);

    boolean checkTemperature(String patientId, BigDecimal temperature);
}
