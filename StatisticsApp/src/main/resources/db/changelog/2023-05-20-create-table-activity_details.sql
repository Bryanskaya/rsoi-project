CREATE TABLE IF NOT EXISTS activity_details
(
    id SERIAL PRIMARY KEY,
    event_uuid UUID NOT NULL,
    username VARCHAR(100),
    action VARCHAR(100) NOT NULL,
    event_start TIMESTAMP NOT NULL,
    event_end TIMESTAMP NOT NULL,
    params JSON,
    service VARCHAR(50) NOT NULL
)
;