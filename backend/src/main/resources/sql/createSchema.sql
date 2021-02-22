CREATE TYPE productCondition AS ENUM ('new','like new','used','acceptable');

CREATE TABLE IF NOT EXISTS company
(
    ID          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS product
(
    ID          SERIAL PRIMARY KEY,
    category    VARCHAR(255),
    name        VARCHAR(255) NOT NULL,
    description VARCHAR (255),
    price       DECIMAL (19,2),
    quantity    INTEGER DEFAULT 0,
    condition   productCondition DEFAULT 'new'
    );
CREATE TABLE IF NOT EXISTS productManufacturer
(
    productID   SERIAL PRIMARY KEY ,
    companyID   SERIAL NOT NULL REFERENCES company(id),
    FOREIGN KEY (productID) REFERENCES product(ID)
    );
CREATE TABLE IF NOT EXISTS basedOn
(
    productID       SERIAL,
    basedOnCompanyID SERIAL,
    PRIMARY KEY (productID,basedOnCompanyID),
    FOREIGN KEY (productID) REFERENCES product (ID),
    FOREIGN KEY (basedOnCompanyID) REFERENCES company (ID)
    );
CREATE TABLE IF NOT EXISTS companyDetailInformation
(
    ID              SERIAL PRIMARY KEY,
    website         VARCHAR(255),
    description     VARCHAR(255),
    basedIn         VARCHAR(255),
    FOREIGN KEY (ID) REFERENCES company(ID)
    );

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
