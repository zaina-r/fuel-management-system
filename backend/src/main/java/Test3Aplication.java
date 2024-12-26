package com.fuel.test3;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public class Test3Application {
    public static void main(String[] args) {
        SpringApplication.run(Test3Application.class, args);
    }
}

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> checkUser(@PathVariable String username) {
        boolean userExists = userService.isUserExists(username);
        if (userExists) {
            return ResponseEntity.ok("User found in the database.");
        } else {
            return ResponseEntity.status(404).body("User not found in the database.");
        }
    }
}

@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }
}

@Repository
interface UserRepository extends JpaRepository<Vehicles, Long> {
    boolean existsByUsername(String username);
}

@Entity
@Table(name = "vehicles")
class Vehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

