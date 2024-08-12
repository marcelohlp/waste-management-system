-- TABLES


CREATE TABLE tbl_districts (
    id      NUMBER(5) 	    NOT NULL,
    name    VARCHAR2(60)    NOT NULL
);

ALTER TABLE tbl_districts ADD CONSTRAINT pk_district PRIMARY KEY ( id );

CREATE TABLE tbl_residents (
    id          NUMBER(9)     NOT NULL,
    id_district NUMBER(5)     NOT NULL,
    first_name  VARCHAR2(30)  NOT NULL,
    last_name   VARCHAR2(30)  NOT NULL,
    email       VARCHAR2(120) NOT NULL
);

ALTER TABLE tbl_residents ADD CONSTRAINT pk_resident PRIMARY KEY ( id );

CREATE TABLE tbl_trash_cans (
    id                NUMBER(9)                NOT NULL,
    id_district       NUMBER(5)                NOT NULL,
    maximum_capacity  NUMBER(7, 2)             NOT NULL,
    used_capacity     NUMBER(7, 2) DEFAULT 0.0 NOT NULL,
    type              VARCHAR2(20)             NOT NULL
);

ALTER TABLE tbl_trash_cans ADD CONSTRAINT pk_trash_can PRIMARY KEY ( id );

CREATE TABLE tbl_trucks (
    id            NUMBER(4)    NOT NULL,
    license_plate VARCHAR2(7)  NOT NULL,
    id_district   NUMBER(5)    NOT NULL,
    type          VARCHAR2(20) NOT NULL
);

ALTER TABLE tbl_trucks ADD CONSTRAINT pk_truck PRIMARY KEY ( id );

ALTER TABLE tbl_trucks ADD CONSTRAINT un_truck_plate UNIQUE ( license_plate );

CREATE TABLE tbl_waste_collection_schedules (
    id                        NUMBER(14)          NOT NULL,
    id_trash_can              NUMBER(9)           NOT NULL,
    id_truck                  NUMBER(4)           NOT NULL,
    scheduled_collection_date DATE                NOT NULL,
    collection_date           DATE     DEFAULT NULL,
    finished                  CHAR(1)  DEFAULT 0  NOT NULL
);

ALTER TABLE tbl_waste_collection_schedules ADD CONSTRAINT pk_waste_collection_schedule PRIMARY KEY ( id );

CREATE TABLE tbl_users (
    id       NUMBER(9)                    NOT NULL,
    name     VARCHAR2(60)                 NOT NULL,
    password VARCHAR2(120)                NOT NULL,
    email    VARCHAR2(120)                NOT NULL,
    role     VARCHAR2(20)  DEFAULT 'USER' NOT NULL
);

ALTER TABLE tbl_users ADD CONSTRAINT pk_user PRIMARY KEY ( id );

ALTER TABLE tbl_users ADD CONSTRAINT un_user_email UNIQUE ( email );


-- FOREIGN KEY


ALTER TABLE tbl_residents
    ADD CONSTRAINT fk_district_resident FOREIGN KEY ( id_district )
        REFERENCES tbl_districts ( id );

ALTER TABLE tbl_trash_cans
    ADD CONSTRAINT fk_district_trash_can FOREIGN KEY ( id_district )
        REFERENCES tbl_districts ( id );

ALTER TABLE tbl_trucks
    ADD CONSTRAINT fk_district_truck FOREIGN KEY ( id_district )
        REFERENCES tbl_districts ( id );

ALTER TABLE tbl_waste_collection_schedules
    ADD CONSTRAINT fk_trash_can_schedule FOREIGN KEY ( id_trash_can )
        REFERENCES tbl_trash_cans ( id );

ALTER TABLE tbl_waste_collection_schedules
    ADD CONSTRAINT fk_truck_schedule FOREIGN KEY ( id_truck )
        REFERENCES tbl_trucks ( id );


--  SEQUENCES


CREATE SEQUENCE sqc_district_id
INCREMENT BY 1
START WITH 1
MAXVALUE 99999
NOCACHE
NOCYCLE;

CREATE SEQUENCE sqc_resident_id
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999
NOCACHE
NOCYCLE;

CREATE SEQUENCE sqc_trash_can_id
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999
NOCACHE
NOCYCLE;

CREATE SEQUENCE sqc_truck_id
INCREMENT BY 1
START WITH 1
MAXVALUE 9999
NOCACHE
NOCYCLE;

CREATE SEQUENCE sqc_scheduled_id
INCREMENT BY 1
START WITH 1
MAXVALUE 99999999999999
NOCACHE
NOCYCLE;

CREATE SEQUENCE sqc_user_id
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999
NOCACHE
NOCYCLE;
