CREATE TABLE IF NOT EXISTS available_time_slots (id BIGSERIAL PRIMARY KEY,
                                                 start_time TIMESTAMP NOT NULL,
                                                 end_time TIMESTAMP NOT NULL,
                                                 is_reserved BOOLEAN NOT NULL
);
