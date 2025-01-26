/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

DROP TABLE IF EXISTS `existing_stations`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `existing_stations` (
                                       `dealer_id` varchar(255) NOT NULL,
                                       `address` varchar(255) NOT NULL,
                                       `dealer_name` varchar(255) NOT NULL,
                                       `license_number` varchar(20) NOT NULL,
                                       PRIMARY KEY (`dealer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `existing_stations` WRITE;

/*!40000 ALTER TABLE `existing_stations` DISABLE KEYS */;

INSERT INTO `existing_stations` (`dealer_id`, `address`, `dealer_name`, `license_number`)
VALUES
    ('DE001', '123 Galle Road, Colombo 03', 'Lanka Fuel Station A', 'LIC-SL-001'),
    ('DE002', '56 Kandy Road, Peradeniya', 'Peradeniya Fuel Center', 'LIC-SL-002'),
    ('DE003', '78 Main Street, Jaffna', 'Jaffna Fuel Station', 'LIC-SL-003'),
    ('DE004', '22 Temple Road, Anuradhapura', 'Sacred City Fuel Station', 'LIC-SL-004'),
    ('DE005', '10 Kurunegala Road, Dambulla', 'Dambulla Fuel Depot', 'LIC-SL-005'),
    ('DE006', '88 Galle Road, Matara', 'Southern Fuel Station', 'LIC-SL-006'),
    ('DE007', '35 Lake Road, Nuwara Eliya', 'Highland Fuel Station', 'LIC-SL-007'),
    ('DE008', '44 Negombo Road, Katunayake', 'Katunayake Fuel Center', 'LIC-SL-008'),
    ('DE009', '60 Batticaloa Road, Trincomalee', 'Eastern Coast Fuel Station', 'LIC-SL-009'),
    ('DE010', '12 Main Street, Hambantota', 'Hambantota Fuel Stop', 'LIC-SL-010'),
    ('DE011', '8 Matale Road, Kurunegala', 'Kurunegala Fuel Point', 'LIC-SL-011'),
    ('DE012', '34 High Street, Badulla', 'Badulla Fuel Station', 'LIC-SL-012'),
    ('DE013', '55 Temple Street, Polonnaruwa', 'Heritage Fuel Station', 'LIC-SL-013'),
    ('DE014', '21 Chilaw Road, Puttalam', 'Puttalam Fuel Depot', 'LIC-SL-014'),
    ('DE015', '19 Sea View Avenue, Galle', 'Galle Bay Fuel Center', 'LIC-SL-015'),
    ('DE016', '90 A9 Road, Kilinochchi', 'Northern Province Fuel Station', 'LIC-SL-016'),
    ('DE017', '67 Central Road, Batticaloa', 'Batticaloa Fuel Stop', 'LIC-SL-017'),
    ('DE018', '12 Fort Road, Trincomalee', 'Trinco Fuel Hub', 'LIC-SL-018'),
    ('DE019', '50 Airport Road, Ratmalana', 'Ratmalana Fuel Center', 'LIC-SL-019'),
    ('DE020', '100 Colombo Road, Avissawella', 'Avissawella Fuel Depot', 'LIC-SL-020'),
    ('DE021', '80 Coast Road, Kalutara', 'Kalutara Fuel Station', 'LIC-SL-021'),
    ('DE022', '25 Hill Street, Hatton', 'Hatton Highland Fuel', 'LIC-SL-022'),
    ('DE023', '75 Garden Road, Kegalle', 'Kegalle Fuel Stop', 'LIC-SL-023'),
    ('DE024', '33 City Road, Ratnapura', 'Gem City Fuel Center', 'LIC-SL-024'),
    ('DE025', '45 Park Avenue, Ampara', 'Ampara Fuel Hub', 'LIC-SL-025'),
    ('DE026', '28 Bazaar Street, Mannar', 'Mannar Fuel Point', 'LIC-SL-026'),
    ('DE027', '61 Lake Drive, Bandarawela', 'Bandarawela Fuel Station', 'LIC-SL-027'),
    ('DE028', '18 Riverside Road, Chilaw', 'Chilaw Fuel Depot', 'LIC-SL-028'),
    ('DE029', '92 A9 Highway, Mullaitivu', 'Mullaitivu Fuel Center', 'LIC-SL-029'),
    ('DE030', '70 Town Road, Monaragala', 'Monaragala Fuel Depot', 'LIC-SL-030');

/*!40000 ALTER TABLE `existing_stations` ENABLE KEYS */;
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