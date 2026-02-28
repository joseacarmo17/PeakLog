package org.peaklog.controller;

import lombok.RequiredArgsConstructor;
import org.peaklog.api.UserControllerApi;
import org.peaklog.mapper.UserMapper;
import org.peaklog.model.dto.UsuarioDto;
import org.peaklog.model.dto.UsuarioListDto;
import org.peaklog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerApi {

  private final UserService userService;
  private final UserMapper userMapper;

  @Override
  public ResponseEntity<UsuarioDto> findUserById(Integer idUsuario) {
    return ResponseEntity.ok(userMapper.toDto(userService.findUserById(idUsuario)));
  }

  @Override
  public ResponseEntity<UsuarioListDto> findAllUsers() {
    UsuarioListDto dto = new UsuarioListDto();
    dto.content(this.userMapper.toDtoList(this.userService.findAllUsers()));
    return ResponseEntity.ok(dto);
  }
}
