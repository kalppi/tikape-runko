-- laitetaan foreign key supportti päälle
PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS Opiskelija;

CREATE TABLE Opiskelija (id INTEGER PRIMARY KEY, nimi VARCHAR(255));