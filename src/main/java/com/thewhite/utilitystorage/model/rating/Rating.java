package com.thewhite.utilitystorage.model.rating;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    @NotNull(message = "не указано id записи")
    UUID id;

    @Column()
    @JdbcTypeCode(SqlTypes.UUID)
    @NotBlank(message = "не указан комментарий")
    UUID utilityId;

    @Column()
    NumberPoints point;
}
///рфрфррфрывадложфывдалофыжвал