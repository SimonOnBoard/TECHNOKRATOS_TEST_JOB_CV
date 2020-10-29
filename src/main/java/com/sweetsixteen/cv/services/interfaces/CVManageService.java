package com.sweetsixteen.cv.services.interfaces;

import com.sweetsixteen.cv.dtos.CurriculumVitaeDto;
import com.sweetsixteen.cv.forms.CVBasicForm;
import com.sweetsixteen.cv.forms.CVUpdateForm;
import com.sweetsixteen.cv.models.CurriculumVitae;

import java.util.List;

public interface CVManageService {
    CurriculumVitaeDto addCV(CVBasicForm basicForm);

    CurriculumVitaeDto getCVById(String id);

    List<CurriculumVitaeDto> findAllCV();

    void delete(String id);

    String update(CVUpdateForm cvUpdateForm);
}
