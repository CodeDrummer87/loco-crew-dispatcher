package x.team.loco_crew_dispatcher.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import x.team.loco_crew_dispatcher.security.dto.AuthRequest;
import x.team.loco_crew_dispatcher.security.dto.AuthResponse;
import x.team.loco_crew_dispatcher.security.dto.RegisterRequest;
import x.team.loco_crew_dispatcher.security.entity.Account;
import x.team.loco_crew_dispatcher.security.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (accountRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already in use");
        }

        var account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("ROLE_" + request.getRole())
                .build();

        account = accountRepository.save(account);

        var token = jwtService.generateToken(account);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var token = jwtService.generateToken(account);
        return AuthResponse.builder().token(token).build();
    }
}
