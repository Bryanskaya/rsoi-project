CREATE TABLE IF NOT EXISTS images
(
    id      UUID         PRIMARY KEY     REFERENCES hotels (hotel_uid),
    url     TEXT
);