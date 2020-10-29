package com.sweetsixteen.cv.mappers;


import com.sweetsixteen.cv.dtos.CurriculumVitaeDto;
import com.sweetsixteen.cv.forms.CVBasicForm;
import com.sweetsixteen.cv.models.CurriculumVitae;
import com.sweetsixteen.cv.models.User;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, ObjectId.class})
public interface CVFormAndDtoMapper {
    @Mappings({
            @Mapping(target = "title", source = "form.title"),
            @Mapping(target = "about", source = "form.about"),
            @Mapping(target = "location", source = "form.location"),
            @Mapping(target = "competences", source = "form.competences"),
            @Mapping(target = "languages", source = "form.languages"),
            @Mapping(target = "previousWorks", source = "form.previousWorks"),
            @Mapping(target = "educations", source = "form.educations"),
            @Mapping(target = "userId",expression = "java(new ObjectId(form.getUserId()))"),
            @Mapping(target = "createdAt",
                    expression = "java(LocalDateTime.now())")

    }
    )
    CurriculumVitae cvFromCVBasicForm(CVBasicForm form);

    @Mappings({

            @Mapping(target = "title", source = "cv.title"),
            @Mapping(target = "about", source = "cv.about"),
            @Mapping(target = "CVLocation", source = "cv.location"),
            @Mapping(target = "competences", source = "cv.competences"),
            @Mapping(target = "languages", source = "cv.languages"),
            @Mapping(target = "previousWorks", source = "cv.previousWorks"),
            @Mapping(target = "educations", source = "cv.educations"),
            @Mapping(target = "createdAt",source = "cv.createdAt"),
            @Mapping(target = "id",source = "cv.id"),
            @Mapping(target = "userId",source = "cv.userId"),
            @Mapping(target = "name", source = "user.name"),
            @Mapping(target = "lastName", source = "user.lastName"),
            @Mapping(target = "patronymic", source = "user.patronymic"),
            @Mapping(target = "userLocation", source = "user.location"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "age", source = "user.age"),
            @Mapping(target = "phoneNumber", source = "user.phoneNumber")
    })
    CurriculumVitaeDto cvDtoFromCVAndUser(CurriculumVitae cv, User user);
}
