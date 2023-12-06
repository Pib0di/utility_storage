package com.thewhite.utilitystorage.model.rating;

import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    UUID id;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.SMALLINT)
     NumberPoints point;

    @Column(nullable = false)
    String description;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    UtilityStorage utilityStorage;
}