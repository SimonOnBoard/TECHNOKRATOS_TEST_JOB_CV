package com.sweetsixteen.cv.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sweetsixteen.cv.models.CurriculumVitae;
import com.sweetsixteen.cv.models.User;
import com.sweetsixteen.cv.models.embedded.Education;
import com.sweetsixteen.cv.models.embedded.Language;
import com.sweetsixteen.cv.models.embedded.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CurriculumVitaeDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;

    private String name;
    private String lastName;
    private String patronymic;
    private String userLocation;
    private String email;
    private int age;
    private String phoneNumber;


    private String title;
    private String about;
    private String CVLocation;


    private List<String> competences;
    private List<Language> languages;
    private List<Work> previousWorks;
    private List<Education> educations;
    private LocalDateTime createdAt;


    //used previously before mapper added
    public static CurriculumVitaeDto from(CurriculumVitae curriculumVitae, User user) {
        return CurriculumVitaeDto.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .userLocation(user.getLocation())
                .email(user.getEmail())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .id(curriculumVitae.getId())
                .userId(curriculumVitae.getUserId())
                .title(curriculumVitae.getTitle())
                .about(curriculumVitae.getAbout())
                .CVLocation(curriculumVitae.getLocation())
                .competences(curriculumVitae.getCompetences())
                .languages(curriculumVitae.getLanguages())
                .previousWorks(curriculumVitae.getPreviousWorks())
                .educations(curriculumVitae.getEducations())
                .createdAt(curriculumVitae.getCreatedAt())
                .build();

    }
}
