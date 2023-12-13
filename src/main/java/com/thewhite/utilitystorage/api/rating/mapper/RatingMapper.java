package com.thewhite.utilitystorage.api.rating.mapper;

import com.thewhite.utilitystorage.action.rating.add.AddRatingActionArgument;
import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.api.rating.dto.SearchRatingDto;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.service.rating.argument.SearchRatingArgument;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RatingMapper {

    RatingDto toDto(Rating addRatingDto);

    AddRatingActionArgument toCreateRatingArgument(AddRatingDto addRatingDto);

    SearchRatingArgument toCreateRatingArgument(SearchRatingDto searchRatingDto);

    List<RatingDto> toDtoRatingList(List<Rating> ratingList);

}
