ALTER TABLE TO_DO
ADD ACTIVE BIT DEFAULT TRUE;

UPDATE TO_DO
SET ACTIVE = TRUE;