package com.thewhite.utilityStorage.api.UtilityStorage.mapper;

import com.thewhite.utilityStorage.action.argument.UtilityStorage.CreateUtilityArg;
import com.thewhite.utilityStorage.api.UtilityStorage.dto.CreateUtilityDto;
import com.thewhite.utilityStorage.api.UtilityStorage.dto.UpdateUtilityDto;
import com.thewhite.utilityStorage.api.UtilityStorage.dto.UtilityStorageDto;
import com.thewhite.utilityStorage.models.UtilityStorage;
import com.thewhite.utilityStorage.action.argument.UtilityStorage.UpdateUtilityArg;
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
