package com.thewhite.utilitystorage.api.rating.mapper;

import com.thewhite.utilitystorage.action.argument.rating.AddRatingArg;
import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.models.Rating;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RatingMapper {

    RatingDto toDto(Rating addRatingDto);

    AddRatingArg toAdd(AddRatingDto addRatingDto);

    List<RatingDto> toDtoList(List<Rating> ratingList);
}
