package com.solvd.laba.hospital.service.person.impl;

import com.solvd.laba.hospital.model.person.PatientPerson;
import com.solvd.laba.hospital.service.Listener;

import java.util.LinkedList;
import java.util.List;

public class PatientListenerHolder {
    private static final List<Listener<PatientPerson>> patientListeners = new LinkedList<>();

    public static void subscribe(Listener<PatientPerson> patientListener) {
        patientListeners.add(patientListener);
    }

    public static void unsubscribe(Listener<PatientPerson> patientListener) {
        patientListeners.remove(patientListener);
    }

    public static void onNewPatient(PatientPerson patient) {
        for (Listener<PatientPerson> listener : patientListeners) {
            listener.onNew(patient);
        }
    }

    public static void onDeletePatient(PatientPerson patient) {
        for (Listener<PatientPerson> listener : patientListeners) {
            listener.onDelete(patient);
        }
    }
}
