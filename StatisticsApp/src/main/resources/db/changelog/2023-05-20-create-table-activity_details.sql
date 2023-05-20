CREATE TABLE IF NOT EXISTS activity_details
(
    id SERIAL PRIMARY KEY,
    event_uuid UUID NOT NULL,
    username VARCHAR(100),
    action VARCHAR(100) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    details JSON,
    service VARCHAR(50) NOT NULL
)
;