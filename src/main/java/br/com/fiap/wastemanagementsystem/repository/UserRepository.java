package br.com.fiap.wastemanagementsystem.repository;

import br.com.fiap.wastemanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    UserDetails findByEmail(String email);

    Boolean existsByEmail(String email);

}
