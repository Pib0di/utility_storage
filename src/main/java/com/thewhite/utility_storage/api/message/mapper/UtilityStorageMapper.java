package com.thewhite.utility_storage.api.message.mapper;

import com.thewhite.utility_storage.api.message.dto.CreateUtilityDto;
import com.thewhite.utility_storage.api.message.dto.UpdateUtilityDto;
import com.thewhite.utility_storage.api.message.dto.UtilityStorageDto;
import com.thewhite.utility_storage.models.UtilityStorage;
import com.thewhite.utility_storage.servicess.argument.CreateUtilityStorageArg;
import com.thewhite.utility_storage.servicess.argument.UpdateUtilityStorageArg;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UtilityStorageMapper {

    UtilityStorageDto toDto(UtilityStorage utilityStorage);

    List<UtilityStorageDto> toDtoList(List<UtilityStorage> utilityList);

    CreateUtilityStorageArg toCreateUtility(CreateUtilityDto createUtilityDto);

    UpdateUtilityStorageArg toUpdateUtility(UpdateUtilityDto updateUtilityDto);
}
