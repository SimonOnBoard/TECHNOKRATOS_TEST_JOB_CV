package com.sweetsixteen.cv.services.implementations;

import com.sweetsixteen.cv.dtos.UserDto;
import com.sweetsixteen.cv.forms.UserBasicForm;
import com.sweetsixteen.cv.forms.UserUpdateForm;
import com.sweetsixteen.cv.mappers.UserDtoAndFormMapper;
import com.sweetsixteen.cv.models.User;
import com.sweetsixteen.cv.repositories.UsersRepository;
import com.sweetsixteen.cv.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UsersRepository usersRepository;
    private final UserDtoAndFormMapper userMapper;
    @Override
    public UserDto addUser(UserBasicForm form) {
        User user = userMapper.userFromUserBasicForm(form);
        usersRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    // TODO: 28.10.2020 https://stackoverflow.com/questions/930433/apply-properties-values-from-one-object-to-another-of-the-same-type-automaticall
    @Override
    public String updateUser(UserUpdateForm form) {
        User user = null;
        boolean shouldReturnResourceUrl = false;
        if (form.getId() != null && profileExists(form.getId())) user = this.findUserById(form.getId());
        if (user != null) {
            user.setName(form.getName());
            user.setLastName(form.getLastName());
            user.setPatronymic(form.getPatronymic());
            user.setLocation(form.getLocation());
            user.setEmail(form.getEmail());
            user.setAge(form.getAge());
            user.setPhoneNumber(form.getPhoneNumber());
        } else {
            user = userMapper.userFromUserBasicForm(form);
            if (form.getId() != null) user.setId(new ObjectId(form.getId()));
            shouldReturnResourceUrl = true;
        }
        usersRepository.save(user);
        if (shouldReturnResourceUrl) return "/" + user.getId().toString();

        return null;
    }

    @Override
    public User findUserById(String id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
    }

    @Override
    public UserDto findUserInfoById(String id) {
        return userMapper.userToUserDto(this.findUserById(id));
    }

    @Override
    public boolean profileExists(String id) {
        return usersRepository.existsById(id);
    }
}
