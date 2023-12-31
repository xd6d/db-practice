package com.solvd.laba.hospital;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.appointment.AppointmentRepository;
import com.solvd.laba.hospital.dao.repository.appointment.ConclusionRepository;
import com.solvd.laba.hospital.model.appointment.Appointment;
import com.solvd.laba.hospital.model.appointment.Conclusion;
import com.solvd.laba.hospital.model.appointment.Medicine;
import com.solvd.laba.hospital.model.appointment.Service;
import com.solvd.laba.hospital.model.info.*;
import com.solvd.laba.hospital.model.person.EmployeePerson;
import com.solvd.laba.hospital.model.person.PatientPerson;
import com.solvd.laba.hospital.model.person.Position;
import com.solvd.laba.hospital.service.appointment.AppointmentService;
import com.solvd.laba.hospital.service.appointment.ConclusionService;
import com.solvd.laba.hospital.service.appointment.MedicalService;
import com.solvd.laba.hospital.service.appointment.MedicineService;
import com.solvd.laba.hospital.service.appointment.impl.AppointmentServiceImpl;
import com.solvd.laba.hospital.service.appointment.impl.ConclusionServiceImpl;
import com.solvd.laba.hospital.service.appointment.impl.MedicalServiceImpl;
import com.solvd.laba.hospital.service.appointment.impl.MedicineServiceImpl;
import com.solvd.laba.hospital.service.info.*;
import com.solvd.laba.hospital.service.info.impl.*;
import com.solvd.laba.hospital.service.person.PatientService;
import com.solvd.laba.hospital.service.person.impl.EmployeeServiceImpl;
import com.solvd.laba.hospital.service.person.impl.PatientServiceImpl;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Demo {

    public static void main(String[] args) {
        SqlSession session = Config.getSessionFactory().openSession(true);
        AppointmentRepository mapper = session.getMapper(AppointmentRepository.class);
        PatientPerson patient = new PatientPerson();
        patient.setId(2);
        EmployeePerson e = new EmployeePerson();
        e.setId(2);
        Appointment a = new Appointment();
        a.setPatient(patient);
        a.setDoctor(e);
        a.setTime(new Date());

        List<Service> services = new ArrayList<>();
        Service s1 = new Service();
        Service s2 = new Service();
        s1.setId(1);
        s2.setId(2);
        services.add(s1);
        services.add(s2);
        a.setServices(services);
//        new AppointmentRepositoryMybatisImpl().create(a);

        ConclusionRepository conclusionRepository = session.getMapper(ConclusionRepository.class);
        Conclusion conclusion = new Conclusion();
        List<Medicine> medicines = new ArrayList<>();
        Medicine m1 = new Medicine();
        Medicine m3 = new Medicine();
        m1.setId(1);
        m3.setId(3);
        medicines.add(m1);
        medicines.add(m3);
        conclusion.setMedicines(medicines);
//        new ConclusionRepositoryMybatisImpl().create(conclusion, 6);

        Service service = new Service();
        service.setName("bats");
        service.setPrice(21);
        service.setDescription("bats");
//        session.getMapper(MedicalRepository.class).create(service);

        Medicine medicine = new Medicine();
        medicine.setName("some");
        medicine.setPrice(123);
        medicine.setDescription("asd");
        medicine.setPrescribed(true);
//        session.getMapper(MedicineRepository.class).create(medicine);
//        System.out.println(medicine);

        //SERVICE TESTS
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        EmployeePerson employeePerson = new EmployeePerson();
        employeePerson.setName("e1");
        employeePerson.setSurname("e1");
        employeePerson.setPhoneNumber("e1");
        employeePerson.setEmail("e1");
        employeePerson.setDegree("d1");
        employeePerson.setPosition(Position.DOCTOR);
//        employeeService.add(employeePerson);
//        System.out.println(employeePerson);
        employeePerson.setId(3);

        PatientService patientService = new PatientServiceImpl();
        PatientPerson p = new PatientPerson();
        p.setName("p1");
        p.setSurname("p1");
        p.setPhoneNumber("p1");
        p.setEmail("p1");
//        patientService.add(p);
//        System.out.println(p);
        p.setId(3);

        DeclarationService declarationService = new DeclarationServiceImpl();
        Declaration d = new Declaration();
        d.setCreated(new Date());
        d.setExpires(new Date(System.currentTimeMillis() + 5_000_000_000L));
        d.setDoctor(employeePerson);
//        declarationService.add(d, 3);
//        System.out.println(d);

        AnalysisService analysisService = new AnalysisServiceImpl();
        Analysis an = new Analysis();
        an.setName("qwe");
        an.setValue("qwe");
        an.setUnit("qwe");
        an.setHealthyValue("qwe");
//        analysisService.add(an, 3);
//        System.out.println(an);

        VaccinationService vaccinationService = new VaccinationServiceImpl();
        Vaccination vac = new Vaccination();
        vac.setName("vac");
        vac.setDate(new Date());
        vac.setExpires(new Date(System.currentTimeMillis() + 2_000_000_000));
//        vaccinationService.add(vac, 3);
//        System.out.println(vac);

        AllergyService allergyService = new AllergyServiceImpl();
        Allergy al = new Allergy();
        al.setName("ale");
//        allergyService.add(al, 3);
//        System.out.println(al);

        HospitalizationService hospitalizationService = new HospitalizationServiceImpl();
        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setPlace("place");
        hospitalization.setDate(new Date());
        hospitalization.setCondition("good");
        hospitalization.setDescription("smth");
//        hospitalizationService.add(hospitalization, 3);
//        System.out.println(hospitalization);

        MedicalService medicalService = new MedicalServiceImpl();
        Service service1 = new Service();
        service1.setName("service1");
        service1.setPrice(15);
        service1.setDescription("some");
//        medicalService.create(service1);
//        System.out.println(service1);
        service1.setId(5);

        AppointmentService appointmentService = new AppointmentServiceImpl();
        Appointment appointment = new Appointment();
        appointment.setDoctor(employeePerson);
        appointment.setPatient(p);
        appointment.setTime(new Date());
        List<Service> services1 = new ArrayList<>();
        services1.add(service1);
        appointment.setServices(services1);
//        appointmentService.add(appointment);
//        System.out.println(appointment);

        MedicineService medicineService = new MedicineServiceImpl();
//        medicineService.create(medicine);
//        System.out.println(medicine);

        ConclusionService conclusionService = new ConclusionServiceImpl();
        Conclusion conclusion1 = new Conclusion();
        conclusion1.setComplaint("com");
        conclusion1.setDiagnosis("diag");
        conclusion1.setObservation("obs");
        conclusion1.setMedicines(medicines);
        conclusionService.create(conclusion1, 12);

//        System.out.println(patientService.getById(3));
    }
}
