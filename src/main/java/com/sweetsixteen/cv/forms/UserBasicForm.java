package com.sweetsixteen.cv.forms;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicForm {
    private String name;
    private String lastName;
    private String patronymic;
    private String location;
    private String email;
    private int age;
    private String phoneNumber;
}
