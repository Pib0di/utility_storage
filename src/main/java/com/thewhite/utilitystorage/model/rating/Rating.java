package com.thewhite.utilitystorage.model.rating;

import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import javax.persistence.*;
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
    UUID id;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    UUID utilityStorageId;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.SMALLINT)
     NumberPoints point;

    @Column(nullable = false)
    String description;
}