package com.sweetsixteen.cv.services.interfaces;

import com.sweetsixteen.cv.dtos.UserDto;
import com.sweetsixteen.cv.forms.UserBasicForm;
import com.sweetsixteen.cv.forms.UserUpdateForm;
import com.sweetsixteen.cv.models.User;

public interface ProfileService {
    UserDto addUser(UserBasicForm form);
    String updateUser(UserUpdateForm form);
    boolean profileExists(String id);
    User findUserById(String id);
    UserDto findUserInfoById(String id);
}
