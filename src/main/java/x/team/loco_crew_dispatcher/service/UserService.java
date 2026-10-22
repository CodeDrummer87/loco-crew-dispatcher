package x.team.loco_crew_dispatcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import x.team.loco_crew_dispatcher.dto.UserDto;
import x.team.loco_crew_dispatcher.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Page<UserDto> getUserPage (int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return userRepository.getAllUsers(pageable);
    }
}
