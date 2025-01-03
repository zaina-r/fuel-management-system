-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: dmv_db
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `Registeredvehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Registeredvehicles` (
  `ID` int NOT NULL,
  `Vehicle_reg_no` varchar(10) GENERATED ALWAYS AS (concat(_utf8mb4'VID',lpad(`ID`,7,_utf8mb4'0'))) STORED NOT NULL,
  `Licence_plate_no` varchar(10) NOT NULL,
  `Vehicle_make` varchar(50) NOT NULL,
  `Vehicle_model` varchar(50) NOT NULL,
  `Vehicle_year` int NOT NULL,
  `Vehicle_colour` varchar(50) NOT NULL,
  `Vehicle_type` enum('Car','Truck','Motorcycle','Bus','Van','SUV','Electric Car','Hybrid Car','Scooter','three-wheeler') NOT NULL,
  `Fuel_type` enum('Petrol','Diesel','Electric','Hybrid','Ethanol','Propane') NOT NULL,
  `Registration_status` enum('active','inactive') NOT NULL,
  `Resitration_expiry_date` date DEFAULT NULL,
  `Registered_province` varchar(100) NOT NULL,
  `Owner_ID` varchar(15) NOT NULL,
  PRIMARY KEY (`Vehicle_reg_no`),
  UNIQUE KEY `Licence_plate_no` (`Licence_plate_no`),
  UNIQUE KEY `ID` (`ID`),
  UNIQUE KEY `ID_2` (`ID`),
  KEY `FK_vehicle_owner` (`Owner_ID`),
  CONSTRAINT `FK_vehicle_owner` FOREIGN KEY (`Owner_ID`) REFERENCES `owner_registrations` (`Owner_reg_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `Registeredvehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `Registeredvehicles` (`ID`, `Licence_plate_no`, `Vehicle_make`, `Vehicle_model`, `Vehicle_year`, `Vehicle_colour`, `Vehicle_type`, `Fuel_type`, `Registration_status`, `Resitration_expiry_date`, `Registered_province`, `Owner_ID`) VALUES (1,'CAB1234','Toyota','Corolla',2015,'White','Car','Petrol','active','2025-05-20','Western ','REG0000005'),(2,'XYZ5678','Ford','Ranger',2018,'Blue','Truck','Diesel','active','2024-12-15','Western ','REG0000002'),(3,'CYA3456','Honda','Civic',2020,'Black','Car','Petrol','active','2025-11-01','Central','REG0000003'),(4,'PQR7890','Honda','CBR600RR',2019,'Red','Motorcycle','Petrol','inactive','2023-09-10','Sabaragamuwa','REG0000004'),(5,'DEF1235','Mercedes','Sprinter',2021,'Silver','Van','Diesel','active','2026-01-30','Western ','REG0000003'),(6,'HIJ4567','Nissan','Altima',2022,'Green','Car','Petrol','active','2026-03-15','Southern','REG0000009'),(7,'UVW6789','Volkswagen','Golf',2017,'Yellow','Car','Petrol','inactive','2024-06-01','Western ','REG0000007'),(8,'RST2345','BMW','X5',2019,'White','SUV','Diesel','active','2025-07-22','Southern','REG0000008'),(9,'TUV4568','Yamaha','MT-07',2020,'Blue','Motorcycle','Petrol','active','2025-09-14','Central','REG0000001'),(10,'WXY5670','Chevrolet','Malibu',2023,'Orange','Car','Diesel','active','2026-05-18','Western ','REG0000009'),(11,'ST8901','Hyundai','Sonata',2021,'Silver','Car','Hybrid','active','2026-07-15','Fontvieille','REG0000019'),(12,'UV9012','Jaguar','F-Pace',2023,'Red','SUV','Petrol','inactive','2024-11-30','Monaco Ville','REG0000020'),(13,'WX1234','Porsche','Macan',2020,'Blue','SUV','Diesel','active','2025-03-22','La Condamine','REG0000011'),(14,'XY2345','Honda','HR-V',2021,'Black','SUV','Petrol','active','2025-06-30','Monaco Ville','REG0000021'),(15,'YZ3456','BMW','Z4',2022,'Yellow','Car','Hybrid','inactive','2025-09-10','Fontvieille','REG0000023'),(16,'ZA4567','Mazda','CX-5',2020,'Green','SUV','Diesel','inactive','2025-02-11','Monaco Ville','REG0000024'),(17,'AB5678','Toyota','Land Cruiser',2022,'Red','Truck','Petrol','active','2026-03-31','La Condamine','REG0000018'),(18,'BC6789','Kia','Seltos',2021,'Blue','SUV','Diesel','inactive','2024-08-15','Fontvieille','REG0000020'),(19,'CD7890','Audi','Q5',2023,'Silver','SUV','Petrol','active','2026-12-01','La Condamine','REG0000014'),(20,'DE8901','Ford','Mustang',2022,'Black','Car','Hybrid','inactive','2025-04-11','Fontvieille','REG0000016'),(21,'AB1234','Toyota','Corolla',2020,'Red','Car','Petrol','active','2025-12-31','Monaco Ville','REG0000011'),(22,'BC5678','Honda','Civic',2021,'Blue','Car','Diesel','active','2025-11-30','Fontvieille','REG0000016'),(23,'DE9901','BMW','X5',2019,'Black','SUV','Petrol','inactive','2024-09-15','La Condamine','REG0000013'),(24,'FG2345','Ford','F-150',2022,'White','Truck','Diesel','active','2025-06-30','Fontvieille','REG0000015'),(25,'HI4567','Mercedes','A-Class',2021,'Silver','Car','Electric','active','2026-03-01','Monaco Ville','REG0000020'),(26,'JK8901','Nissan','Altima',2020,'Gray','Car','Hybrid','active','2025-07-15','La Condamine','REG0000014'),(27,'KL2345','Tesla','Model 3',2023,'White','Electric Car','Electric','active','2026-05-20','Monaco Ville','REG0000012'),(28,'MN5678','Chevrolet','Bolt',2021,'Blue','Electric Car','Electric','inactive','2024-04-25','Fontvieille','REG0000017'),(29,'OP6789','Audi','Q7',2018,'Black','SUV','Diesel','active','2025-10-11','La Condamine','REG0000018'),(30,'QR7890','Volkswagen','Golf',2022,'Green','Car','Petrol','inactive','2025-01-01','Monaco Ville','REG0000025');
/*!40000 ALTER TABLE `Registeredvehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-17 20:14:21
