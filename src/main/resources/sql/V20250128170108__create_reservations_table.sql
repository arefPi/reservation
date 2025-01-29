CREATE TABLE IF NOT EXISTS reservations (
    id VARCHAR(255) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    available_time_slot_id BIGINT NOT NULL,
    reserved_at TIMESTAMP NOT NULL,
    state VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (available_time_slot_id) REFERENCES available_time_slots(id));

CREATE INDEX IF NOT EXISTS idx_reservations_id ON reservations(id);