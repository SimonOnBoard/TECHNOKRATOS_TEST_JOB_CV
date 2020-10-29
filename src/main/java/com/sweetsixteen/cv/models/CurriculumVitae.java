package com.sweetsixteen.cv.models;

import com.sweetsixteen.cv.forms.CVBasicForm;
import com.sweetsixteen.cv.models.embedded.Education;
import com.sweetsixteen.cv.models.embedded.Language;
import com.sweetsixteen.cv.models.embedded.Work;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cv")
@Builder
public class CurriculumVitae {

    @Id
    private ObjectId id;

    private ObjectId userId;
    @TextIndexed(weight = 10)
    private String title;
    @TextIndexed(weight = 15)
    private String about;
    @TextIndexed(weight = 5)
    private String location;

    private List<String> competences;
    private List<Language> languages;
    private List<Work> previousWorks;
    private List<Education> educations;
    private LocalDateTime createdAt;

    //used before mappers added
    public static CurriculumVitae from(CVBasicForm basicForm) {
        return CurriculumVitae.builder()
                .userId(new ObjectId(basicForm.getUserId()))
                .title(basicForm.getTitle())
                .about(basicForm.getAbout())
                .location(basicForm.getLocation())
                .competences(basicForm.getCompetences())
                .languages(basicForm.getLanguages())
                .previousWorks(basicForm.getPreviousWorks())
                .educations(basicForm.getEducations())
                .createdAt(LocalDateTime.now())
                .build();
    }
}