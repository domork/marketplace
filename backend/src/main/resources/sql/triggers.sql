DROP TRIGGER IF EXISTS tr ;

CREATE TRIGGER tr BEFORE INSERT
    ON company FOR EACH ROW
BEGIN
      INSERT INTO company (name) VALUES ('xaxaxa');
END ;


