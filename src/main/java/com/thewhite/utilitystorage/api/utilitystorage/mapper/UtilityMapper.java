package com.thewhite.utilitystorage.api.utilitystorage.mapper;

import com.thewhite.utilitystorage.api.utilitystorage.dto.CreateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UpdateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UtilityStorageDto;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.UpdateUtilityArgument;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
public interface UtilityMapper {

    UtilityStorageDto toDto(UtilityStorage utilityStorage);

    List<UtilityStorageDto> toDtoList(List<UtilityStorage> utilityList);

    CreateUtilityArgument toCreate(CreateUtilityDto createUtilityDto);

    UpdateUtilityArgument toUpdate(UpdateUtilityDto updateUtilityDto);

}
