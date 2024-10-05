package ru.fa.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fa.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> { }
