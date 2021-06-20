CREATE SCHEMA IF NOT EXISTS tanks;

DROP TABLE IF EXISTS tanks.users;

CREATE TABLE tanks.users (
    id PRIMARY KEY INT GENERATED ALWAYS AS IDENTITY,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
);

DROP TABLE IF EXISTS tanks.users;

CREATE TABLE tanks.statistics (
    id PRIMARY KEY INT GENERATED ALWAYS AS IDENTITY,
    winner TEXT NOT NULL,
    loser TEXT NOT NULL,
    shots INT,
    misses INT,
    hits INT,
);