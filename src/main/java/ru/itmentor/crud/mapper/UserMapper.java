package ru.itmentor.crud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmentor.crud.dto.request.CreateUserDTO;
import ru.itmentor.crud.dto.request.UpdateUserDTO;
import ru.itmentor.crud.dto.response.FindUserResponseDTO;
import ru.itmentor.crud.model.User;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    void updateEntityFromDto(UpdateUserDTO dto, @MappingTarget User entity);
    User toEntityFromCreateDto(CreateUserDTO dto);
    FindUserResponseDTO toFindUserResponseDTOFromEntity(User user);
    List<FindUserResponseDTO> toDtoFindListFromEntityList(List<User> entityList);
}
