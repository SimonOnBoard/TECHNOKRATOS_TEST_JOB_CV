package com.sweetsixteen.cv.models;


import com.sweetsixteen.cv.forms.UserBasicForm;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "profile")
public class User {
    @Id
    private ObjectId id;

    private String name;
    private String lastName;
    private String patronymic;


    @TextIndexed(weight = 2)
    private String location;

    private String email;
    private int age;
    private String phoneNumber;

    private LocalDateTime createdAt;

    //использовался раньше для создания пользователя, пока не было mapstruct
    public static User from(UserBasicForm form) {
        return User.builder()
                .name(form.getName())
                .lastName(form.getLastName())
                .patronymic(form.getPatronymic())
                .location(form.getLocation())
                .email(form.getEmail())
                .age(form.getAge())
                .phoneNumber(form.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
