package org.peaklog.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.peaklog.model.dto.CreateUserDTO;
import org.peaklog.model.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "AuthController")
@RequestMapping("/auth")
public interface AuthControllerApi {
    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping("/register")
    ResponseEntity<String> register(CreateUserDTO createUserDTO);

    @Operation(summary = "Login with user credentials")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ok")
    })
    @PostMapping("/login")
    ResponseEntity<String> login(LoginDTO loginDTO);
}
