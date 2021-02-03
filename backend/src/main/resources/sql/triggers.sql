CREATE TRIGGER tr
    AFTER INSERT
    ON company
    FOR EACH ROW
EXECUTE PROCEDURE foo();

CREATE OR REPLACE FUNCTION foo() RETURNS TRIGGER AS
$$
DECLARE
    ID INTEGER;
BEGIN
    SELECT c.ID INTO ID FROM company c where c.name=NEW.name;
    INSERT INTO companyDetailInformation (ID,description) VALUES (ID,'success');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

INSERT INTO company (name) VALUES ('z5');
