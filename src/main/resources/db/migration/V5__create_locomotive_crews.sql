CREATE TABLE IF NOT EXISTS locomotive_crews (
    id BIGSERIAL PRIMARY KEY,
    driver_id BIGINT NOT NULL,
    has_assistant BOOLEAN DEFAULT FALSE,
    driver_assistant_id BIGINT,
    has_understudy BOOLEAN DEFAULT FALSE,
    understudy_id BIGINT,
    traction_kind_id BIGINT NOT NULL,
    service_type_id BIGINT NOT NULL,
    notes TEXT,

    CONSTRAINT fk_locomotive_crews_driver FOREIGN KEY (driver_id) REFERENCES employees(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_locomotive_crews_driver_assistant FOREIGN KEY (driver_assistant_id) REFERENCES employees(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_locomotive_crews_understudy FOREIGN KEY (understudy_id) REFERENCES employees(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_locomotive_crews_traction_kind FOREIGN KEY (traction_kind_id) REFERENCES traction_kinds(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_locomotive_crews_service_type FOREIGN KEY (service_type_id) REFERENCES service_types(id)
    ON DELETE RESTRICT,

    CONSTRAINT check_driver_assistant_id CHECK (
        (has_assistant = TRUE AND driver_assistant_id IS NOT NULL) OR
        (has_assistant = FALSE AND driver_assistant_id IS NULL)
    ),

    CONSTRAINT check_understudy_id CHECK (
        (has_understudy = TRUE AND understudy_id IS NOT NULL) OR
        (has_understudy = FALSE AND understudy_id IS NULL)
    )
);

INSERT INTO locomotive_crews (driver_id, has_assistant, driver_assistant_id, has_understudy,
                              understudy_id, traction_kind_id, service_type_id)
VALUES
    (1, TRUE, 20, TRUE, 3, 1, 1),
    (5, TRUE, 2, FALSE, NULL, 1, 1),
    (7, FALSE, NULL, FALSE, NULL, 1, 1);