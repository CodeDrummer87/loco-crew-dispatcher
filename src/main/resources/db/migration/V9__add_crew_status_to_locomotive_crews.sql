ALTER TABLE locomotive_crews
    ADD COLUMN crew_status_id INTEGER NOT NULL DEFAULT 0;

UPDATE locomotive_crews SET crew_status_id = 1 WHERE id = 2;
UPDATE locomotive_crews SET crew_status_id = 2 WHERE id = 3;

INSERT INTO locomotive_crews (
    driver_id, has_assistant, driver_assistant_id,
    has_understudy, understudy_id,
    traction_kind_id, service_type_id, crew_status_id)
VALUES (
           1,
        TRUE,
        10,
        FALSE,
        NULL,
        1,
        1,
        1

       )


    /*Почему всё-таки лучше новая миграция (даже на этапе разработки)
Ты учишься правильно работать с версионированием БД.

Не придётся чистить базу каждый раз, когда что-то забыл.

Можно спокойно накатывать изменения поверх существующих данных.

то профессиональная привычка, которая очень пригодится в реальной работе.
      !!!Здесь я просто обновил таблицу locomotive_crews добавив поле crew_status_id
      и установил значение enum 0,1,2
     */