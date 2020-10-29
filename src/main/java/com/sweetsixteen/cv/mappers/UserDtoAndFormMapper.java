package com.sweetsixteen.cv.mappers;


import com.sweetsixteen.cv.dtos.UserDto;
import com.sweetsixteen.cv.forms.UserBasicForm;
import com.sweetsixteen.cv.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface UserDtoAndFormMapper {
    @Mappings({
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "objectId", source = "user.id"),
            @Mapping(target = "phone", source = "user.phoneNumber"),
            @Mapping(target = "createdAt", source = "user.createdAt")
    })
    UserDto userToUserDto(User user);

    @Mappings({
            @Mapping(target = "name", source = "form.name"),
            @Mapping(target = "lastName", source = "form.lastName"),
            @Mapping(target = "patronymic", source = "form.patronymic"),
            @Mapping(target = "location", source = "form.location"),
            @Mapping(target = "email", source = "form.email"),
            @Mapping(target = "age", source = "form.age"),
            @Mapping(target = "phoneNumber", source = "form.phoneNumber"),
            @Mapping(target = "createdAt",
                    expression = "java(LocalDateTime.now())")
    })
    User userFromUserBasicForm(UserBasicForm form);
}
