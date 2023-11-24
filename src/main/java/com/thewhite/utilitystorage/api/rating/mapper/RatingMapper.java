package com.thewhite.utilitystorage.api.rating.mapper;

import com.thewhite.utilitystorage.action.rating.add.CreateRatingArgument;
import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.model.rating.Rating;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RatingMapper {

    RatingDto toDto(Rating addRatingDto);

    CreateRatingArgument toCreateRatingArgument(AddRatingDto addRatingDto);

    List<RatingDto> toDtoRatingList(List<Rating> ratingList);
}
