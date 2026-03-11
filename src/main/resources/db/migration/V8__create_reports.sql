CREATE TABLE IF NOT EXISTS reports (
    id BIGSERIAL PRIMARY KEY,
    report_to TIMESTAMP NOT NULL,
    reporting_point BIGINT NOT NULL,
    district BIGINT NOT NULL,
    service_type BIGINT NOT NULL,
    is_heavy_train BOOLEAN NOT NULL DEFAULT FALSE,
    is_coupled_train BOOLEAN NOT NULL DEFAULT FALSE,
    notes VARCHAR(50),

    CONSTRAINT fk_reports_reporting_point FOREIGN KEY(reporting_point) REFERENCES reporting_points(id),
    CONSTRAINT fk_reports_district FOREIGN KEY(district) REFERENCES crew_districts(id),
    CONSTRAINT fk_reports_service_type FOREIGN KEY(service_type) REFERENCES service_types(id)
);