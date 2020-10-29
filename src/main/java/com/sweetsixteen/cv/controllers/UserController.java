package com.sweetsixteen.cv.controllers;

import com.sweetsixteen.cv.dtos.UserDto;
import com.sweetsixteen.cv.forms.UserBasicForm;
import com.sweetsixteen.cv.forms.UserUpdateForm;
import com.sweetsixteen.cv.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/profile")
public class UserController {
    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserBasicForm userForm) {
        return ResponseEntity.ok(profileService.addUser(userForm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(profileService.findUserInfoById(id));
    }


    //Generally, when a PUT request creates a resource the server will respond with a 201
    // (Created), and if the request modifies existing resource
    // the server will return a 200 (OK) or 204 (No Content).
    // Source: https://assertible.com/blog/7-http-methods-every-web-developer-should-know-and-how-to-test-them#:~:text=Repeatedly%20calling%20a%20PUT%20request,should%20return%20the%20correct%20data.
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateForm userForm) {
        String url = profileService.updateUser(userForm);
        //если добавили, то возвращаем адрес ресурса
        if(url != null) return ResponseEntity.created(URI.create(url)).build();
        return ResponseEntity.noContent().build();
    }
}
