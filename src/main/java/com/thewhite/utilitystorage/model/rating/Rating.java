package com.thewhite.utilitystorage.model.rating;

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
    UUID id;

    @Column()
    @JdbcTypeCode(SqlTypes.UUID)
    UUID utilityId;

    @Column()
    NumberPoints point;
}