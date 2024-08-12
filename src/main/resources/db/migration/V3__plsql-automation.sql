
CREATE OR REPLACE PROCEDURE sp_schedule_email
(
     p_district_id IN tbl_districts.id%TYPE
)

IS

BEGIN

     FOR resident IN (SELECT * FROM tbl_residents WHERE id_district = p_district_id)
     LOOP
          DBMS_OUTPUT.PUT_LINE ('FRON: system@mail.com');
          DBMS_OUTPUT.PUT_LINE ('TO: ' || resident.email);
          DBMS_OUTPUT.PUT_LINE ('SUBJECT: Scheduled WASTE collection in your region.');
          DBMS_OUTPUT.PUT_LINE ('Mr(s) ' || resident.last_name || ', the trash can in your district is full and your collection has been scheduled for ' || TO_CHAR(sysdate + 1, 'DD/MM/YYYY'));
     END LOOP;

END sp_schedule_email;
/

CREATE OR REPLACE PROCEDURE sp_schedule_collection
(
     p_id_trash_can IN tbl_trash_cans.id%TYPE,
     p_id_truck IN tbl_trucks.id%TYPE
)

IS

BEGIN

     INSERT INTO tbl_waste_collection_schedules (
          id,
          id_trash_can,
          id_truck,
          scheduled_collection_date
     ) VALUES (
          sqc_scheduled_id.nextval,
          p_id_trash_can,
          p_id_truck,
          sysdate +1
     );

EXCEPTION

     WHEN OTHERS THEN

          DBMS_OUTPUT.PUT_LINE ('No trucks found!');

END sp_schedule_collection;
/

CREATE OR REPLACE FUNCTION fn_find_truck
(
     p_id_district IN tbl_districts.id%TYPE,
     p_type IN tbl_trucks.type%TYPE
)

RETURN NUMBER

IS

     v_truck tbl_trucks%ROWTYPE;

BEGIN

     BEGIN

          SELECT * INTO v_truck FROM tbl_trucks WHERE id_district = p_id_district AND type = p_type AND ROWNUM = 1;

     EXCEPTION

          WHEN no_data_found THEN

               DBMS_OUTPUT.PUT_LINE ('No trucks found!');

          WHEN OTHERS THEN

               DBMS_OUTPUT.PUT_LINE ('ERROR!' || SQLERRM);

     END;


     RETURN v_truck.id;

END fn_find_truck;
/

CREATE OR REPLACE FUNCTION fn_exist_schedule
(
     p_trash_can_id IN tbl_trash_cans.id%TYPE
)

RETURN BOOLEAN

IS

     v_exists NUMBER;

BEGIN

     SELECT COUNT(*) INTO v_exists FROM tbl_waste_collection_schedules WHERE id_trash_can = p_trash_can_id AND finished = 0;

     IF (v_exists = 0) THEN
          RETURN FALSE;
     ELSE
          RETURN TRUE;
     END IF;

END fn_exist_schedule;
/

CREATE OR REPLACE TRIGGER tg_collection_schedule
AFTER UPDATE OF used_capacity ON tbl_trash_cans
FOR EACH ROW

WHEN (NEW.used_capacity / OLD.maximum_capacity > 0.7)

DECLARE

     v_scheduled BOOLEAN;
     v_truck_id tbl_trucks.id%TYPE;

BEGIN

     v_scheduled := fn_exist_schedule(:OLD.id);

     IF (v_scheduled = FALSE) THEN

          v_truck_id := fn_find_truck(:OLD.id_district, :OLD.type);

          sp_schedule_collection(:OLD.id, v_truck_id);

          sp_schedule_email(:OLD.id_district);

     END IF;

END;
/

CREATE OR REPLACE PROCEDURE sp_completed_email
(
     p_district_id IN tbl_districts.id%TYPE
)

IS

BEGIN

     FOR resident IN (SELECT * FROM tbl_residents WHERE id_district = p_district_id)
          LOOP
               DBMS_OUTPUT.PUT_LINE ('FRON: system@mail.com');
               DBMS_OUTPUT.PUT_LINE ('TO: ' || resident.email);
               DBMS_OUTPUT.PUT_LINE ('SUBJECT: Waste collection carried out successfully.');
               DBMS_OUTPUT.PUT_LINE ('Mr(s) ' || resident.last_name || ', the trash can in your area has been emptied successfully ' || TO_CHAR(sysdate, 'DD/MM/YYYY'));
          END LOOP;

END sp_completed_email;
/

CREATE OR REPLACE PROCEDURE sp_reset_occupied_volume
(
     p_id_trash_can IN tbl_trash_cans.id%TYPE
)

IS

BEGIN

     UPDATE tbl_trash_cans SET used_capacity = 0 WHERE id = p_id_trash_can;

EXCEPTION

     WHEN OTHERS THEN

          DBMS_OUTPUT.PUT_LINE ('Failed to update trash can!');


END sp_reset_occupied_volume;
/

CREATE OR REPLACE FUNCTION fn_find_trash_can_district
(
     p_id_trash_can  IN tbl_trash_cans.id%TYPE
)

RETURN NUMBER

IS

     v_trash_can tbl_trash_cans%ROWTYPE;

BEGIN

     BEGIN

          SELECT * INTO v_trash_can FROM tbl_trash_cans WHERE id = p_id_trash_can ;

     EXCEPTION

          WHEN no_data_found THEN

               DBMS_OUTPUT.PUT_LINE ('No trash can found with this code!');

          WHEN OTHERS THEN

               DBMS_OUTPUT.PUT_LINE ('ERROR!' || SQLERRM);

     END;


     RETURN v_trash_can.id_district;

END fn_find_trash_can_district;
/

CREATE OR REPLACE TRIGGER tg_finish_collection
AFTER UPDATE OF finished ON tbl_waste_collection_schedules
FOR EACH ROW

WHEN (OLD.finished = 0)

DECLARE

     v_id_district tbl_trash_cans.id_district%TYPE;

BEGIN

     v_id_district := fn_find_trash_can_district(:OLD.id_trash_can);

     IF (:NEW.finished = 1) THEN

          sp_reset_occupied_volume(:OLD.id_trash_can);

          sp_completed_email(v_id_district);

     END IF;

END;
/