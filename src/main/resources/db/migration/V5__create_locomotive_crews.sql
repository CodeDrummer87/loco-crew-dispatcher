CREATE TABLE IF NOT EXISTS locomotive_crews (
    id BIGSERIAL PRIMARY KEY,
    driver BIGINT NOT NULL,
    has_assistant BOOLEAN DEFAULT FALSE,
    driver_assistant BIGINT,
    has_understudy BOOLEAN DEFAULT FALSE,
    understudy BIGINT,
    traction_kind BIGINT NOT NULL,
    service_type BIGINT NOT NULL,
    notes TEXT,

    CONSTRAINT fk_locomotive_crews_driver FOREIGN KEY (driver) REFERENCES employees(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_locomotive_crews_driver_assistant FOREIGN KEY (driver_assistant) REFERENCES employees(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_locomotive_crews_understudy FOREIGN KEY (understudy) REFERENCES employees(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_locomotive_crews_traction_kind FOREIGN KEY (traction_kind) REFERENCES traction_kinds(id)
    ON DELETE RESTRICT,

    CONSTRAINT fk_locomotive_crews_service_type FOREIGN KEY (service_type) REFERENCES service_types(id)
    ON DELETE RESTRICT,

    CONSTRAINT check_driver_assistant CHECK (
        (has_assistant = TRUE AND driver_assistant IS NOT NULL) OR
        (has_assistant = FALSE AND driver_assistant IS NULL)
    ),

    CONSTRAINT check_understudy CHECK (
        (has_understudy = TRUE AND understudy IS NOT NULL) OR
        (has_understudy = FALSE AND understudy IS NULL)
    )
);

INSERT INTO locomotive_crews (driver, has_assistant, driver_assistant, has_understudy,
                              understudy, traction_kind, service_type)
VALUES
    (1, TRUE, 20, TRUE, 3, 1, 1),
    (5, TRUE, 2, FALSE, NULL, 1, 1),
    (7, FALSE, NULL, FALSE, NULL, 1, 1);