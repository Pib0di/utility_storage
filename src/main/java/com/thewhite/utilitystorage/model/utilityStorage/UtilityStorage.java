package com.thewhite.utilitystorage.model.utilityStorage;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UtilityStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    UUID id;

    @Column(nullable = false)
    @NonNull String name;

    @Column(nullable = false)
    @NonNull String description;

    @Column(nullable = false)
    @ElementCollection
    @NonNull Set<String> link;
}
