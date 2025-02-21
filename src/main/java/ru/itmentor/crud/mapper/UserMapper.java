package ru.itmentor.crud.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.itmentor.crud.dto.request.CreateUser;
import ru.itmentor.crud.dto.request.UpdateUser;
import ru.itmentor.crud.dto.response.FindUserResponse;
import ru.itmentor.crud.model.User;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    void updateEntityFromDto(UpdateUser dto, @MappingTarget User entity);
    User toEntityFromCreateDto(CreateUser dto);
    FindUserResponse toFindUserResponseDTOFromEntity(User user);
    List<FindUserResponse> toDtoFindListFromEntityList(List<User> entityList);
}
