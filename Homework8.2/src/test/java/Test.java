import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;
import ru.netology.patient.service.medical.MedicalServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Test {
    @org.junit.jupiter.api.Test
    public void checkBloodPressureTest() {
        PatientInfo patientInfo = new PatientInfo("99384571", "Jon", "Snow", LocalDate.of(1995, 12, 14),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78)));
        PatientInfoFileRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById("99384571")).thenReturn(patientInfo);
        Mockito.when(patientInfoRepository.add(patientInfo)).thenReturn("99384571");

        SendAlertService sendAlertService = new SendAlertServiceImpl();
        String id1 = patientInfoRepository.add(patientInfo);
        BloodPressure bloodPressure = new BloodPressure(124, 70);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        Assertions.assertTrue(medicalService.checkBloodPressure(id1, bloodPressure));
    }

    @org.junit.jupiter.api.Test
    public void checkTemperatureTest() {
        PatientInfo patientInfo = new PatientInfo("99384571", "Jon", "Snow", LocalDate.of(1995, 12, 14),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78)));
        PatientInfoFileRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById("99384571")).thenReturn(patientInfo);
        Mockito.when(patientInfoRepository.add(patientInfo)).thenReturn("99384571");

        SendAlertService sendAlertService = new SendAlertServiceImpl();
        String id1 = patientInfoRepository.add(patientInfo);
        BigDecimal temperature = new BigDecimal("34.9");

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        Assertions.assertTrue(medicalService.checkTemperature(id1, temperature));
    }

    @org.junit.jupiter.api.Test
    public void allClearTest() {
        PatientInfo patientInfo = new PatientInfo("99384571", "Jon", "Snow", LocalDate.of(1995, 12, 14),
                new HealthInfo(new BigDecimal("36.6"), new BloodPressure(125, 78)));
        PatientInfoFileRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById("99384571")).thenReturn(patientInfo);
        Mockito.when(patientInfoRepository.add(patientInfo)).thenReturn("99384571");

        SendAlertService sendAlertService = new SendAlertServiceImpl();
        String id1 = patientInfoRepository.add(patientInfo);
        BigDecimal temperature = new BigDecimal("36.6");
        BloodPressure bloodPressure = new BloodPressure(125, 78);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        boolean testResult = false;
        if (!medicalService.checkBloodPressure(id1, bloodPressure) && !medicalService.checkTemperature(id1, temperature)) {
            testResult = true;
        }

        Assertions.assertTrue(testResult);
    }
}
