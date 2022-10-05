package com.avenues.avenues.controller.user;

import com.avenues.avenues.work.user.dto.*;
import com.avenues.avenues.work.user.repository.IUserRepo;
import com.avenues.avenues.work.user.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController implements IUserController{

    private final IUserRepo repo;

    public UserController(IUserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserRegResponse regUser(UserRegRequest request){
        if(repo.existsByEmail(request.getEmail()) || repo.existsByUsername(request.getUsername())){
            throw new RuntimeException();
        }

        User user = new User("Online", request.getUsername(), request.getEmail(), request.getUri());

        repo.save(user);
        log.info("user saved");

        return new UserRegResponse(user.getId());
    }

    @Override
    public UserProfileResponse userProfile(UserProfileRequest request) {
        Optional<User> optionalUser = repo.findById(request.getId());
        User user;

        if (optionalUser.isPresent()){
            user = optionalUser.get();

            return new UserProfileResponse(user.getImage(), user.getUsername(), user.getEmail());
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public StatusChangeResponse onlineStatus(StatusChangeRequest request) {
        Optional<User> optionalUser = repo.findById(request.getId());
        User user;

        if (optionalUser.isPresent()){
            user = optionalUser.get();
            String previous;

            switch (request.getStatus()){
                case ("Online"):
                    previous = user.getStatus();
                    user.setStatus("Online");
                    break;
                case ("Offline"):
                    previous = user.getStatus();
                    user.setStatus("Offline");
                    break;
                default:
                    throw new RuntimeException();
            }

            repo.save(user);

            return new StatusChangeResponse(user.getId(), previous, user.getStatus());
        } else {
            throw new RuntimeException();
        }
    }
}
