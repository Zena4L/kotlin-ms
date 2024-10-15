-- liquibase formatted sql

-- changeset ClementOwireku-Bogya:1728985225708-1
ALTER TABLE customers
    ADD gender SMALLINT;

-- changeset ClementOwireku-Bogya:1728985225708-2
ALTER TABLE customers
    DROP COLUMN sex;

