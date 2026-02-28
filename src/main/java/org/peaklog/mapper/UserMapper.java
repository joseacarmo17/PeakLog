package org.peaklog.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.peaklog.model.domain.UserModelDomain;
import org.peaklog.model.dto.UsuarioDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UsuarioDto toDto(UserModelDomain domain);

  UserModelDomain toDomain(UsuarioDto dto);

  List<UsuarioDto> toDtoList(List<UserModelDomain> domains);

  List<UserModelDomain> toDomainList(List<UsuarioDto> dtos);
}
