package io.mmy.todoapp.repo;

import io.mmy.todoapp.model.Mession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessionRepository extends JpaRepository<Mession, String> {
    Optional<Mession> findMessionById(String id);
}
