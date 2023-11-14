package com.thewhite.utilitystorage.api.utilitystorage.mapper;

import com.thewhite.utilitystorage.action.argument.UtilityStorage.CreateUtilityArg;
import com.thewhite.utilitystorage.api.utilitystorage.dto.CreateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UpdateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UtilityStorageDto;
import com.thewhite.utilitystorage.models.UtilityStorage;
import com.thewhite.utilitystorage.action.argument.UtilityStorage.UpdateUtilityArg;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UtilityMapper {

    UtilityStorageDto toDto(UtilityStorage utilityStorage);

    List<UtilityStorageDto> toDtoList(List<UtilityStorage> utilityList);

    CreateUtilityArg toCreate(CreateUtilityDto createUtilityDto);

    UpdateUtilityArg toUpdate(UpdateUtilityDto updateUtilityDto);
}
