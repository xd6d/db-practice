package com.solvd.laba.hospital;

import com.solvd.laba.hospital.dao.Config;
import com.solvd.laba.hospital.dao.repository.info.DeclarationRepository;
import com.solvd.laba.hospital.model.info.Declaration;
import com.solvd.laba.hospital.model.person.EmployeePerson;
import com.solvd.laba.hospital.model.person.Position;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

public class Demo {

    public static void main(String[] args) {
        SqlSession session = Config.getSessionFactory().openSession(true);
        DeclarationRepository mapper = session.getMapper(DeclarationRepository.class);
        EmployeePerson em = new EmployeePerson();
        em.setName("btis");
        em.setSurname("btis");
        em.setPhoneNumber("btis2");
        em.setEmail("btis2");
        em.setId(4);
        em.setPosition(Position.DOCTOR);
        Declaration declaration = new Declaration();
        declaration.setDoctor(em);
        declaration.setCreated(new Date());
        declaration.setExpires(new Date(declaration.getCreated().getTime() + 10_000_000_000L));
        mapper.create(declaration, 2);
//        mapper.create(em);
//        Allergy a = new Allergy();
//        a.setName("bts");
//        mapper.create(a, 2);
//        System.out.println(mapper.findAllByPatientId(2));
//        Vaccination v = new Vaccination();
//        v.setName("bt");
//        v.setDate(new Date());
//        v.setExpires(new Date(v.getDate().getTime() + 10_000_000_000L));
//        System.out.println(mapper.findAllByPatientId(2));
//        System.out.println(v);
//        Hospitalization h = new Hospitalization();
//        h.setPlace("bat");
//        h.setDate(new Date());
//        h.setCondition("bat");
//        h.setDescription("bat");
//        System.out.println(mapper.findAllByPatientId(2));
    }
}
