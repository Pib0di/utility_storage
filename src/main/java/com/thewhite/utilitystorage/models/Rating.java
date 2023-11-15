package com.thewhite.utilitystorage.models;

import com.thewhite.utilitystorage.common.variable.NumberPoints;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    UUID id;
    UUID utilityId;
    NumberPoints point;
}