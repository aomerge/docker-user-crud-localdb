package com.example.dockerusercrud.controller;

import com.example.dockerusercrud.entity.User;
import com.example.dockerusercrud.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);

        log.info("Iniciando creación de usuario: {}", user.getUsername());
        User savedUser = userService.save(user);
        log.info("Usuario creado con ID {}", savedUser.getId());

        MDC.clear();
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);

        log.info("Recuperando todos los usuarios");
        List<User> users = userService.findAll();
        log.info("Se encontraron {} usuarios", users.size());

        MDC.clear();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);

        log.info("Buscando usuario con ID {}", id);
        Optional<User> userOpt = userService.findById(id);

        if (userOpt.isPresent()) {
            log.info("Usuario encontrado: {}", userOpt.get().getUsername());
        } else {
            log.warn("Usuario con ID {} no encontrado", id);
        }

        MDC.clear();
        return userOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);

        log.info("Eliminando usuario con ID {}", id);
        userService.deleteById(id);
        log.info("Usuario con ID {} eliminado", id);

        MDC.clear();
        return ResponseEntity.noContent().build();
    }

}
