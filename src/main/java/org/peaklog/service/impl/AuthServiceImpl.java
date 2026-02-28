package org.peaklog.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.peaklog.config.security.JwtService;
import org.peaklog.exception.ErrorCode;
import org.peaklog.exception.ServiceException;
import org.peaklog.model.dto.CreateUserDto;
import org.peaklog.model.dto.LoginDto;
import org.peaklog.model.entity.User;
import org.peaklog.repository.UserRepository;
import org.peaklog.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  @Override
  public void register(CreateUserDto createUserDTO) {
    try {
      User user = new User();
      user.setLogin(createUserDTO.getLogin());
      user.setName(createUserDTO.getName());
      user.setBirthDate(LocalDate.parse(createUserDTO.getBirthDate()));
      user.setEmail(createUserDTO.getEmail());
      user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
      userRepository.save(user);
    } catch (Exception e) {
      throw new ServiceException(ErrorCode.INTERNAL_ERROR, e.getMessage());
    }
  }

  @Override
  public String login(LoginDto loginDTO) {
    Optional<User> usuario = userRepository.findByLogin(loginDTO.getLogin());

    if (usuario.isPresent()) {
      if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.get().getPassword())) {
        throw new ServiceException(ErrorCode.INVALID_CREDENTIALS);
      }
      return jwtService.generateAccessToken(usuario.get().getLogin());
    } else {
      throw new ServiceException(ErrorCode.USER_NOT_FOUND);
    }
  }

  @Override
  public String refreshToken(String token) {
    try {
      String accessToken = token.substring(7);

      if (!jwtService.isRefreshTokenValid(accessToken)) {
        throw new ServiceException(ErrorCode.TOKEN_INVALID);
      }

      String login = jwtService.extractLogin(accessToken);

      String refreshToken = jwtService.generateRefreshToken(login);

      return refreshToken;

    } catch (Exception e) {
      throw new ServiceException(ErrorCode.TOKEN_INVALID);
    }
  }
}
