package com.sweetsixteen.cv.forms;


import com.sweetsixteen.cv.models.embedded.Education;
import com.sweetsixteen.cv.models.embedded.Language;
import com.sweetsixteen.cv.models.embedded.Work;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  CVBasicForm {
    private String userId;
    private String title;
    private String about;
    private String location;
    private List<String> competences;
    private List<Language> languages;
    private List<Work> previousWorks;
    private List<Education> educations;
}
