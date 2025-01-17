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
-- Table structure for table `owner_registrations`
--

DROP TABLE IF EXISTS `owner_registrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owner_registrations` (
  `ID` int NOT NULL,
  `Owner_reg_no` varchar(10) GENERATED ALWAYS AS (concat(_utf8mb4'REG',lpad(`ID`,7,_utf8mb4'0'))) STORED NOT NULL,
  `NIC` varchar(12) NOT NULL,
  `First_name` varchar(50) NOT NULL,
  `Last_name` varchar(50) NOT NULL,
  `phone_number` varchar(11) NOT NULL,
  `DOB` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `District` varchar(30) NOT NULL,
  `City` varchar(50) NOT NULL,
  `Street` varchar(150) NOT NULL,
  `Address_no` varchar(5) NOT NULL,
  `Gender` enum('M','F') NOT NULL,
  PRIMARY KEY (`Owner_reg_no`),
  UNIQUE KEY `NIC` (`NIC`),
  UNIQUE KEY `phone_number` (`phone_number`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner_registrations`
--

LOCK TABLES `owner_registrations` WRITE;
/*!40000 ALTER TABLE `owner_registrations` DISABLE KEYS */;
INSERT INTO `owner_registrations` (`ID`, `NIC`, `First_name`, `Last_name`, `phone_number`, `DOB`, `email`, `District`, `City`, `Street`, `Address_no`, `Gender`) VALUES (1,'197401091234','John','Doe','0771234567','1974-01-09','john.doe@example.com','Colombo','Borella','Swarna Road','23','M'),(2,'198502151234','Emma','Smith','0781234567','1985-02-15','emma.smith@example.com','Galle','Ahungalla','Arthurs Pl','10','F'),(3,'199003202345','Liam','Johnson','0791234567','1990-03-20','liam.johnson@example.com','Kandy','Akurana','Beltona Ln','42','M'),(4,'199501101234','Olivia','Brown','0701234567','1995-01-10','olivia.brown@example.com','Colombo','Bambalapitiya','Sea Street','15','F'),(5,'198112051234','Noah','Davis','0779876543','1981-12-05','noah.davis@example.com','Batticaloa','Eravur','Dutugemunu St','56','M'),(6,'199407191234','Sophia','Miller','0789876543','1994-07-19','sophia.miller@example.com','Colombo','Borella','Station Road','10','F'),(7,'200103101234','James','Wilson','0799876543','2001-03-10','james.wilson@example.com','Kegalle','Ambepussa','Hampden Ln','25','M'),(8,'198803151234','Isabella','Moore','0775432109','1988-03-15','isabella.moore@example.com','Jaffna','Chavakachcheri','Lakshman Pl','77','F'),(9,'199211121234','Lucas','Taylor','0785432109','1992-11-12','lucas.taylor@example.com','Colombo','Maradana','Maradana Road','99','M'),(10,'199607051234','Charlotte','Anderson','0709876543','1996-07-05','charlotte.anderson@example.com','Galle','Ahangama','Railway Ave','25','F'),(11,'197419201231','Tharindu','Bandara','0754567891','1988-03-12','tharindu.bandara@email.com','Colombo','Dematagoda','10th Ln','101','M'),(12,'198519601232','Lahiru','Munasinghe','0724568912','1985-07-08','lahiru.munasinghe@email.com','Matara','Weligama ','Main Street','102','M'),(13,'199011501233','Nipuni','Sampath','0745678913','1990-11-15','nipuni.sampath@email.com','Colombo','Bambalapitiya','Alfred House Rd','103','F'),(14,'197715401234','Nuwan','Perera','0777789140','1977-05-30','nuwan.perera@email.com','Kurunegala','Kuliyapitiya','Silkhouse Street','104','M'),(15,'199212301235','Shehara','Dissanayake','0711237891','1992-12-30','shehara.dissanayake@email.com','Gampaha','Ja-Ela','Andarewatta Rd','105','F'),(16,'198303301236','Gihan','Fernando','0713456786','1983-03-30','gihan.fernando@email.com','Colombo','Wellawatta','Arnolda Pl','106','M'),(17,'199711101237','Pawani','Gunaratne','0712457897','1997-11-10','pawani.gunaratne@email.com','Hambantota','Tangalle','Anula Rd','107','F'),(18,'197905301238','Naveen','Attanayake','0774567891','1979-05-30','naveen.attanayake@email.com','Hambantota','Hambantota','School Lane','108','M'),(19,'200005151239','Zubair','Ahamed','0774117891','2000-05-15','zubair.ahamed@email.com','Gampaha','Nittambuwa','Canal Ln','109','M'),(20,'199303021240','Sameera','Jayewardene','0774345678','1993-03-02','sameera.jayewardene@email.com','Hambantota','Tissamaharama','Jayasinghe Rd','110','M'),(21,'198612251241','Pasindu','Kodithuwakku','0771231231','1986-12-25','pasindu.kodithuwakku@email.com','Colombo','Kollupitiya','Lauries Ln','111','M'),(22,'199807071242','Sajith','Thilakaratne','0777123123','1998-07-07','sajith.thilakaratne@email.com','Colombo','Bambalapitiya','Milagiriya Ave','112','M'),(23,'197201011243','Kavindi','Senarath','0717123123','1972-01-01','kavindi.senarath@email.com','Colombo','Slave Island','Fareed Pl','113','F'),(24,'199901091244','Lakmal','Kumara','0744123123','1999-01-09','lakmal.kumara@email.com','Colombo','Grandpass','Panchikawatte Road','114','M'),(25,'198403141245','Kaveesha','Samarasinghe','0756786786','1984-03-14','kaveesha.samarasinghe@email.com','Kandy','Kadugannawa','Dhammarama Rd','115','F'),(26,'199602281246','Chamara','Ekanayake','0742342342','1996-02-28','chamara.ekanayake@email.com','Kandy','Gampola','12th Ln','116','M'),(27,'198707121247','Navodya','Cooray','0777987987','1987-07-12','navodya.cooray@email.com','Badulla','Bandarawela','Davidson Rd','117','F'),(28,'200101011248','Ashen','Fonseka','0751233211','2001-01-01','ashen.fonseka@email.com','Colombo','Bambalapitiya','Bambalapitiya Terrace','118','M'),(29,'199410251249','Thushara','Wikramanayake','0775675675','1994-10-25','thushara.wikramanayake@email.com','Vavuniya','	Nedunkerny','Palmyrath Ave','119','M'),(30,'198609091250','Abishek','Krishnamoorthy','0717171233','1986-09-09','abishek.krishnamoorthy@email.com','Gampaha','Kelaniya','Rajasinghe Rd','120','M'),(31,'198303150101','Sachini','Gamage','0712345678','1983-03-15','sachini.gamage@email.com','Kalutara','Horana','Main St.','201','F'),(32,'199011101102','Thilini','Abeygunawardena','0723456789','1990-11-10','thilini.abeygunawardena@email.com','Polonnaruwa','Kaduruwela','Suranimala Pl','202','F'),(33,'198812250103','Isuru','Ratnayake','0734567890','1988-12-25','isuru.ratnayake@email.com','Colombo','Kirulapona','Old Moor Street','203','M'),(34,'198703210104','Sandun','Samaraweera','0745678901','1987-03-21','sandun.samaraweera@email.com','Matale','Dambulla','Temple Ln','204','M'),(35,'199501100105','Amila','Jayewardene','0756789012','1995-01-10','amila.jayewardene@email.com','Galle','Bentota','School Lane','205','M'),(36,'199303050106','Sumaiya','Gafoor','0767890123','1993-03-05','sumaiya.gafoor@email.com','Kandy','Peradeniya','Sri Damma Mawatha','206','F'),(37,'199402250107','Harshani','Kodithuwakku','0778901234','1994-02-25','harshani.kodithuwakku@email.com','Matara','Mirissa','Udupila Road','207','F'),(38,'199010151108','Hasitha','Madusanka','0789012345','1990-10-15','hasitha.madusanka@email.com','Nuwara Eliya','Hakgala','Badulla Road','208','M'),(39,'199602120109','Ama','Wijesuriya','0720123456','1996-02-12','ama.wijesuriya@email.com','Colombo','Slave Island','New Ferry Ln.','209','F'),(40,'199012150110','Sampath','Pathirana','0721234567','1990-12-15','sampath.pathirana@email.com','Galle','Ambalangoda','Kularatne Rd.','210','M'),(41,'198710080111','Keerthana','Prakash','0742345678','1987-10-08','keerthana.prakash@email.com','Jaffna','Point Pedro','Jaffna-Point Pedro Rd.','211','F'),(42,'199803070112','Gayan','Wijewardene','0773456789','1998-03-07','gayan.wijewardene@email.com','Kandy','Gampola','Hill St.','212','M'),(43,'199211290113','Sahan','Abeysekera','0774567890','1992-11-29','sahan.abeysekera@email.com','Kalutara','Kalutara','Gemunu Mawatha','213','M'),(44,'199705190114','Ashan','Lakmal','0765678901','1997-05-19','ashan.lakmal@email.com','Colombo','Mt. Lavinia','Yasorapura Road.','214','M'),(45,'198509060115','Induwara','Dassanayake','0766789012','1985-09-06','induwara.dassanayake@email.com','Colombo','Fort','Duke Street','215','M'),(46,'199812010120','Nuwan','Mendis','0755678901','1998-12-01','nuwan.mendis@email.com','Kandy','Kandy','Odean Rd.','220','M'),(47,'199502020117','Ashvin','Viswanathan','0712345688','1995-02-02','ashvin.viswanathan@email.com','Colombo','Havelock Town','Chitra Ln.','217','M'),(48,'199303150118','Nimesha','Weeraratne','0773456345','1993-03-15','nimesha.weeraratne@email.com','Matale','Dambulla','Kohombiliwela Rd.','218','F'),(49,'199011080119','Dewmini','Nanayakkara','0744567890','1990-11-08','dewmini.nanayakkara@email.com','Kegalle','Rambukkana','Polgahawela Rd.','219','F'),(50,'199601130121','Tharun','Vijayakumar','0766789112','1996-01-13','tharun.vijayakumar@email.com','Puttalam','Dankotuwa','C. J. Mawatha','221','M');
/*!40000 ALTER TABLE `owner_registrations` ENABLE KEYS */;
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
