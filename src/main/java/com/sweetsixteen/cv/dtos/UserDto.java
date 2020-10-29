package com.sweetsixteen.cv.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sweetsixteen.cv.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String email;

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId objectId;

    private String phone;
    private LocalDateTime createdAt;
    public static UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .objectId(user.getId())
                .phone(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
