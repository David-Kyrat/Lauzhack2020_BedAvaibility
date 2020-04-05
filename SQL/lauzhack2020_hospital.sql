create table hospital
(
    hospital_id char(200) not null
        primary key,
    StName      char(200) null,
    StNb        int       null,
    NPA         int       null,
    Canton      char(3)   null,
    Town        char(200) null,
    Capacity    int       null,
    FreeBeds    int       null
);

INSERT INTO lauzhack2020.hospital (hospital_id, StName, StNb, NPA, Canton, Town, Capacity, FreeBeds) VALUES ('CHUV', 'Rue de Bugnon', 21, 1011, 'VD', 'Lausanne', 1554, 233);
INSERT INTO lauzhack2020.hospital (hospital_id, StName, StNb, NPA, Canton, Town, Capacity, FreeBeds) VALUES ('eHnv', 'Route de l''Hôpital', 3, 1436, 'VD', 'Chamblon', 330, 0);
INSERT INTO lauzhack2020.hospital (hospital_id, StName, StNb, NPA, Canton, Town, Capacity, FreeBeds) VALUES ('Fédération des Hôpitaux Vaudoix', 'Bois de Cery', 0, 1008, 'VD', 'Prilly', 0, 0);
create table patient
(
    Patient_id     char(15)  not null
        primary key,
    FirstName      char(25)  null,
    LastName       char(25)  null,
    Age            int       null,
    RiskFactor     int       null comment 'probability of having severe complication
when infected',
    HealthState    char(200) null comment 'How is the patient ? Does he need emergency care etc...',
    fk_hospital_id char(200) null,
    fk_UnitType_id char(200) null,
    constraint patient_hospital_hospital_id_fk
        foreign key (fk_hospital_id) references hospital (hospital_id),
    constraint patient_unit_UnitType_id_fk
        foreign key (fk_UnitType_id) references unit (UnitType_id)
);

INSERT INTO lauzhack2020.patient (Patient_id, FirstName, LastName, Age, RiskFactor, HealthState, fk_hospital_id, fk_UnitType_id) VALUES ('0000', 'Jean', 'Michel', 45, 75, 'Grave', 'CHUV', 'Intensive Care');
create table unit
(
    UnitType_id    char(45)  not null comment 'Char(45)
'
        primary key,
    Capacity       int       null comment 'Total nb of beds in the Unit (used or not)',
    UsedBedNb      int       null comment 'Number of bed used overall in the Unit',
    FreeBedNb      int       null comment 'Number of Free beds in the Unit
',
    CovidBedNb     int       null comment 'Number of bed used by covid-infected people
',
    OtherBedNb     int       null comment 'Number of beds used by people that don''t have covid',
    fk_hospital_id char(200) null comment 'hospital to which the unit belongs',
    constraint tbl_unit_hospital_id_fk
        foreign key (fk_hospital_id) references hospital (hospital_id)
);

INSERT INTO lauzhack2020.unit (UnitType_id, Capacity, UsedBedNb, FreeBedNb, CovidBedNb, OtherBedNb, fk_hospital_id) VALUES ('Intensive Care', 0, 0, 0, 0, 0, 'CHUV');