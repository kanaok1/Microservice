package ru.itmentor.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmentor.crud.model.User;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username ")
    Optional<User> findByUsername(String username);
}
