INSERT INTO positions(name, is_doctor) VALUES
    ('Nurse', false),
    ('Doctor', true),
    ('Receptionist', false);

INSERT INTO employees(name, surname, phone_number, email, degree, position_name) VALUES
    ('Kathrine', 'White', '+12345678', 'kath@mail.com', 'Highest degree', 'Doctor'),
    ('Emma', 'Green', '+98765432', 'emma@mail.com', null, 'Receptionist'),
    ('Jack', 'Johns', '+32145678', 'jack@mail.com', '1st degree', 'Doctor'),
    ('Dan', 'Jacobs', '+87878787', 'dan@mail.com', null, 'Receptionist');

INSERT INTO declarations(doctor_id, created, expires) VALUES
    (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + interval 1 year),
    (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + interval 2 year),
    (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + interval 1 year);

INSERT INTO patients(name, surname, phone_number, email, declaration_id) VALUES
    ('David', 'Black', '+65432109', 'david@mail.com', 1),
    ('Maria', 'Tree', '+10293847', 'maria@mail.com', 2),
    ('Ryan', 'Lee', '+123', 'ryan@mail.com', null);

INSERT INTO appointments(time, patient_id, doctor_id) VALUES
    (CURRENT_TIMESTAMP + interval 1 day, 1, 3),
    (CURRENT_TIMESTAMP + interval 2 day, 2, 3),
    (CURRENT_TIMESTAMP + interval 2 day, 2, 3);

INSERT INTO recipes(reason) VALUES
    ('High temperature'),
    ('Headache'),
    ('Some bad recipe');

INSERT INTO medicines(name, price, description) VALUES
    ('Aspirin', 10, 'Used to reduce pain, fever, and/or inflammation, and as an antithrombotic.'),
    ('Piaron', 12, 'Paracetamol');

INSERT INTO medicines(name, price, description, is_recepted) VALUES ('Ascophen', 15, 'Therapy for mild to moderate pain.', true);

INSERT INTO recipe_medicines(recipe_id, medicine_id) VALUES
    (1, 1),
    (1, 2),
    (2, 2),
    (2, 1);

INSERT INTO conclusions(complaint, medical_history, observation, diagnosis, recommendations, recipe_id) VALUES
    ('Bad condition', 'None', 'Temperature 38.8, low blood pressure', 'Fever', 'Aspirin 2 times a day', 1),
    (null, null, null, null, null, 1),
    ('Strong headache', 'Paracetamol have not helped', 'No visible observations', null, '1. Do head x-ray. 2. Try Ascophen after breakfast', 2);

UPDATE medicines SET is_recepted = false where id = 2;

UPDATE conclusions SET diagnosis = 'Migraine' WHERE id = 2;

UPDATE patients SET email = 'mmaarriiaa@mail.com', phone_number = '+12312312' WHERE id = 2;

UPDATE declarations SET expires = expires + interval 1 year;

UPDATE declarations SET expires = expires + interval 1 year where id = 2;

UPDATE employees SET degree = 'Highest degree' where phone_number = '+32145678' and email = 'jack@mail.com';

UPDATE employees SET position_name = 'Nurse' where email = 'kath@mail.com';

UPDATE employees SET email = CONCAT(surname, '@hospital.com') where email like '%@mail.com';

UPDATE appointments SET time = time + interval 1 day where id = 2;

UPDATE medicines SET price = 12 where name = 'Aspirin';

DELETE FROM declarations WHERE expires < CURRENT_TIMESTAMP;

DELETE FROM employees WHERE email = 'Jacobs@hospital.com';

DELETE FROM appointments WHERE id = 3;

DELETE FROM recipe_medicines WHERE recipe_id = 2 and medicine_id = 1;

DELETE FROM conclusions WHERE recommendations is null;

DELETE FROM medicines WHERE name in ('Piaron', 'piaron');

DELETE FROM patients WHERE id = 3;

DELETE FROM declarations WHERE doctor_id = 2;

DELETE FROM positions WHERE name = 'Receptionist';

DELETE FROM recipes WHERE id = 3;

SELECT pat.name, pat.surname, e.name AS doc_name, e.surname AS doc_surname, app.time AS app_time, c.recommendations, m.name FROM patients pat
    LEFT JOIN declarations d ON pat.declaration_id = d.id
    LEFT JOIN employees e ON e.id = d.doctor_id
    LEFT JOIN positions pos ON pos.name = e.position_name
    LEFT JOIN analyses a ON pat.id = a.patient_id
    LEFT JOIN vaccinations v ON v.patient_id = pat.id
    LEFT JOIN hospitalizations h ON pat.id = h.patient_id
    LEFT JOIN appointments app ON pat.id = app.patient_id and e.id = app.doctor_id
    LEFT JOIN conclusions c ON c.id = app.conclusion_id
    LEFT JOIN recipes r ON r.id = c.recipe_id
    LEFT JOIN recipe_medicines r_m ON r_m.recipe_id = r.id
    LEFT JOIN medicines m ON m.id = r_m.medicine_id
    LEFT JOIN appointment_services a_s ON a_s.appointment_id = app.id
    LEFT JOIN services s ON s.id = a_s.service_id;

SELECT pat.name as pat_name, pat.surname as pat_surname, e.name AS doc_name, e.surname AS doc_surname FROM patients pat
    INNER JOIN declarations d ON pat.declaration_id = d.id
    INNER JOIN employees e ON e.id = d.doctor_id;

SELECT e.name as doc_name, e.surname as doc_surname, a.time as time, p.name as pat_name, p.surname as pat_surname FROM employees e
    RIGHT OUTER JOIN appointments a ON e.id = a.doctor_id AND a.time > CURRENT_TIMESTAMP
    LEFT OUTER JOIN patients p ON p.id = a.patient_id;

SELECT p.name as pat_name, p.surname as pat_surname, c.recommendations FROM patients p
    INNER JOIN appointments app ON p.id = app.patient_id
    INNER JOIN conclusions c ON c.id = app.conclusion_id;

SELECT hosp.place, hosp.description, hosp.patients_condition, p.name, p.surname FROM patients p
    RIGHT JOIN hospitalizations hosp ON hosp.patient_id = p.id;

SELECT * FROM patients p
    LEFT JOIN vaccinations v ON p.id = v.patient_id;

ALTER TABLE patients add column birth_date DATE NOT NULL DEFAULT '1000-01-01';

ALTER TABLE patients rename column birth_date to b_date;

ALTER TABLE patients drop column b_date;

ALTER TABLE medicines MODIFY COLUMN description TEXT;

ALTER TABLE services MODIFY COLUMN description TEXT;

SELECT MAX(price) as max_price FROM medicines;

SELECT MIN(price) as max_price FROM medicines;

SELECT AVG(price) as max_price FROM medicines;

SELECT p.name, p.surname, SUM(m.price) as med_total FROM patients p
    INNER JOIN appointments app ON p.id = app.patient_id
    LEFT JOIN conclusions c ON c.id = app.conclusion_id
    INNER JOIN recipes r ON c.recipe_id = r.id
    LEFT JOIN recipe_medicines r_m ON r.id = r_m.recipe_id
    INNER JOIN medicines m ON r_m.medicine_id = m.id
GROUP BY p.name, p.surname;

SELECT e.name, e.surname, COUNT(d.id) AS decls_amount FROM declarations d
    RIGHT JOIN employees e ON d.doctor_id = e.id GROUP BY e.name, e.surname;

SELECT COUNT(*) AS total_appointments FROM appointments;

SELECT p.name, p.surname, COUNT(h.id) as hospitalizations FROM patients p
    LEFT JOIN hospitalizations h ON p.id = h.patient_id
GROUP BY p.name, p.surname;

SELECT e.position_name, COUNT(e.position_name) AS employees FROM employees e GROUP BY e.position_name HAVING employees > 1;

SELECT s.name, SUM(s.price) as money_amount, COUNT(s.name) AS times_used FROM services s
    LEFT JOIN appointment_services a_s ON s.id = a_s.service_id
GROUP BY s.name HAVING times_used <= 10 ORDER BY times_used desc;

SELECT p.name, p.surname, COUNT(app.id) AS appointments FROM patients p
    LEFT JOIN appointments app ON p.id = app.patient_id
GROUP BY p.name, p.surname HAVING appointments > 0 ORDER BY appointments desc;

SELECT p.name, p.surname, COUNT(h.id) as hospitalizations FROM patients p
    LEFT JOIN hospitalizations h ON p.id = h.patient_id
GROUP BY p.name, p.surname HAVING hospitalizations > 0;

SELECT e.name, e.surname, COUNT(d.id) AS decls_amount FROM declarations d
    RIGHT JOIN employees e ON d.doctor_id = e.id GROUP BY e.name, e.surname HAVING decls_amount < 5;

SELECT p.name, p.surname, SUM(m.price) as med_total FROM patients p
    INNER JOIN appointments app ON p.id = app.patient_id
    LEFT JOIN conclusions c ON c.id = app.conclusion_id
    INNER JOIN recipes r ON c.recipe_id = r.id
    LEFT JOIN recipe_medicines r_m ON r.id = r_m.recipe_id
    INNER JOIN medicines m ON r_m.medicine_id = m.id
GROUP BY p.name, p.surname HAVING med_total > 10;

SELECT p.name, p.surname, COUNT(a.id) AS analyses FROM patients p
    LEFT JOIN analyses a ON p.id = a.patient_id
GROUP BY p.name, p.surname HAVING analyses > 0;