package com.ms.email.repository;

import com.ms.email.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailModelRepository extends JpaRepository<EmailModel, UUID> {
}