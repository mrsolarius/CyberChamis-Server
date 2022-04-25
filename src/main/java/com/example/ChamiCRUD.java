package com.example;

import java.util.List;
import java.util.Optional;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/chamis")
public class ChamiCRUD {
    @Autowired
    ChamiRepository chamis;

    @GetMapping("/")
    public ResponseEntity<List<Chami>> index() {
        return ResponseEntity.ok( chamis.findAll() );
    }

    @GetMapping("/{chamiId}")
    ResponseEntity<Chami> read(@PathVariable(value="chamiId") String id) {
        Optional<Chami> c = chamis.findById(id);
        System.out.println(id);
        if (c.isPresent()) {
            return ResponseEntity.ok(c.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/{chamiId}")
    public ResponseEntity<Chami> create(@PathVariable(value="chamiId") String login, @RequestHeader("Authorization") String jwt, @RequestBody Chami c) {
        // already exists ?
        if (!login.equals(c.login)) {
            System.err.println( "login != c.login" );
            System.err.println( "  login = " + login );
            System.err.println( "c.login = " + c.login );
            return ResponseEntity.status(412).body(null);
        } else {
            // Authorized ?
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(jwt);
                String uid = decodedToken.getEmail(); // .getUid();
                if (!uid.equals(login)) {
                    return ResponseEntity.status(401).body(null);
                } else {
                    return ResponseEntity.ok( chamis.save(c) ); 
                }
            } catch (Exception e) {
                System.err.println( e.getMessage() );
                return ResponseEntity.status(401).body( null );
            }
        }
    }
}
