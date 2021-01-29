CREATE TYPE IF NOT EXISTS productCondition AS ENUM ('new','like new','used','acceptable');
CREATE TABLE IF NOT EXISTS company
(
  ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS product
(
    ID          BIGINT AUTO_INCREMENT PRIMARY KEY,
    category    VARCHAR(255),
    name        VARCHAR(255) NOT NULL,
    description VARCHAR (255),
    price       DECIMAL,
    quantity    INTEGER DEFAULT 0,
    condition   productCondition DEFAULT 'new'
);
CREATE TABLE IF NOT EXISTS productManufacturer
(
    productID   BIGINT PRIMARY KEY ,
    companyID   BIGINT NOT NULL REFERENCES company(id),
    FOREIGN KEY (productID) REFERENCES product(ID)
);
CREATE TABLE IF NOT EXISTS basedOn
(
    productID       BIGINT,
    basedOnCompanyID BIGINT,
    PRIMARY KEY (productID,basedOnCompanyID),
    FOREIGN KEY (productID) REFERENCES product (ID),
    FOREIGN KEY (basedOnCompanyID) REFERENCES company (ID)
);
CREATE TABLE IF NOT EXISTS companyDetailInformation
(
    ID              BIGINT PRIMARY KEY,
    website         VARCHAR(255),
    description     VARCHAR(255),
    basedIn         VARCHAR(255),
    FOREIGN KEY (ID) REFERENCES company(ID)
);
