CREATE TABLE TO_DO (
  TO_DO_NO IDENTITY,
  TO_DO_UUID uuid default random_uuid(),
  NAME VARCHAR(60),
  DESCRIPTION VARCHAR(255),
  TO_DO_LIST_NO INT,
  FOREIGN KEY (TO_DO_LIST_NO) REFERENCES TO_DO_LIST(TO_DO_LIST_NO)
);
INSERT INTO TO_DO (NAME, DESCRIPTION, TO_DO_LIST_NO)
    VALUES ('Example', 'This is an exmaple todo for exmaple', 1);