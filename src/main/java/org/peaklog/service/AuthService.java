package org.peaklog.service;

import org.peaklog.model.dto.CreateUserDto;
import org.peaklog.model.dto.LoginDto;

public interface AuthService {

    void register(CreateUserDto createUserDTO);

    String login(LoginDto loginDTO);

    String refreshToken(String token);

}
