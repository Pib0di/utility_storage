package com.thewhite.utilitystorage.model.utilityStorage;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UtilityStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @NotNull(message = "не указано id записи")
    UUID id;

    @Column(nullable = false)
    @NonNull String name;

    @Column(nullable = false)
    @NonNull String description;

    @Column(nullable = false)
    @ElementCollection
    @NonNull Set<String> link;
}
