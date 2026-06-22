package x.team.loco_crew_dispather.rest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import x.team.loco_crew_dispather.dto.UserDto;
import x.team.loco_crew_dispather.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<Page<UserDto>> findAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(defaultValue = "fullName") String sortBy) {

        if (page < 0) page = 0;
        if (size < 1 || size > 50) size = 7;

        return ResponseEntity.ok((userService.getUserPage(page, size, sortBy)));
    }
}
