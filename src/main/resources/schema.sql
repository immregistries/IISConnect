CREATE TABLE VACCINATION(
  VaccinationID BIGINT AUTO_INCREMENT PRIMARY KEY,
  PatientID BIGINT NOT NULL,
  AdministrationDate DATE,
  VaccineLabel VARCHAR(255),
  VaccineCvx VARCHAR(255),
  ManufacturerLabel VARCHAR(255),
  ManufacturerMvx VARCHAR(255),
  ActionCode VARCHAR(255),
  Completion VARCHAR(255),
  InformationSource VARCHAR(255),
  InformationSourceLabel VARCHAR(50),
  RefusalReason VARCHAR(255),
  LotNumber VARCHAR(255),
  AdministeredAmount VARCHAR(255),
  AdministeredAmountUnit VARCHAR(255),
  LastModifiedBy VARCHAR(250),
  LastModified DATE
);




CREATE TABLE Patient(
  PatientID BIGINT AUTO_INCREMENT PRIMARY KEY,
  PatientIdentifier VARCHAR(255),
  PatientIdentifierType VARCHAR(10),
  DOB DATE,
  Authority VARCHAR(50),
  FirstName VARCHAR(100),
  LastName VARCHAR(100),
  MiddleName VARCHAR(100),
  Sex VARCHAR(25),
  MotherFirstName VARCHAR(100),
  MotherLastName VARCHAR(100),
  MotherMiddleName VARCHAR(100),
  MaidenName VARCHAR(100),
  AddressLine1 VARCHAR(100),
  AddressLine2 VARCHAR(100),
  City VARCHAR(100),
  State VARCHAR(10),
  ZipCode VARCHAR(15),
  Country VARCHAR(50),
  AreaCode VARCHAR(10),
  PhoneNumber VARCHAR(25),
  LastModifiedBy VARCHAR(250),
  LastModified DATE
);



--ALTER TABLE Vaccination ADD FOREIGN KEY (PatientID) REFERENCES Patient(PatientID);
--CREATE INDEX IDX_PATIENT_ID ON Vaccination(PatientID);
