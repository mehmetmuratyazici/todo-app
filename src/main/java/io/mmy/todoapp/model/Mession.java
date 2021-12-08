package io.mmy.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mession {
    @Id
    private String id = UUID.randomUUID().toString();

    private String messionContext;

    private LocalDateTime createdAt = LocalDateTime.now();
}
