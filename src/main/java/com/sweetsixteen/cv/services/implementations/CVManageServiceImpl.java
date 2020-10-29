package com.sweetsixteen.cv.services.implementations;

import com.sweetsixteen.cv.dtos.CurriculumVitaeDto;
import com.sweetsixteen.cv.forms.CVBasicForm;
import com.sweetsixteen.cv.forms.CVUpdateForm;
import com.sweetsixteen.cv.mappers.CVFormAndDtoMapper;
import com.sweetsixteen.cv.models.CurriculumVitae;
import com.sweetsixteen.cv.models.User;
import com.sweetsixteen.cv.repositories.CurriculumVitaeRepository;
import com.sweetsixteen.cv.services.interfaces.CVManageService;
import com.sweetsixteen.cv.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CVManageServiceImpl implements CVManageService {
    private final CurriculumVitaeRepository curriculumVitaeRepository;
    private final ProfileService profileService;
    private final CVFormAndDtoMapper cvMapper;

    @Override
    public CurriculumVitaeDto addCV(CVBasicForm basicForm) {
        User user = profileService.findUserById(basicForm.getUserId());
        CurriculumVitae curriculumVitae = cvMapper.cvFromCVBasicForm(basicForm);
        curriculumVitaeRepository.save(curriculumVitae);
        return cvMapper.cvDtoFromCVAndUser(curriculumVitae, user);
    }

    @Override
    public CurriculumVitaeDto getCVById(String id) {
        CurriculumVitae curriculumVitae = this.findCVById(id);
        User user = profileService.findUserById(curriculumVitae.getUserId().toString());
        return cvMapper.cvDtoFromCVAndUser(curriculumVitae, user);
    }

    @Override
    public List<CurriculumVitaeDto> findAllCV() {
        List<CurriculumVitaeDto> resultList = new ArrayList<>();
        List<CurriculumVitae> curriculumVitaes = curriculumVitaeRepository.findAll();
        curriculumVitaes.stream().forEach(cv -> resultList.add(cvMapper.cvDtoFromCVAndUser(cv, profileService.findUserById(cv.getUserId().toString()))));
        return resultList;
    }

    @Override
    public void delete(String id) {
        if (!curriculumVitaeRepository.existsById(id)) throw new IllegalArgumentException("CV not found");
        curriculumVitaeRepository.deleteById(id);
    }

    @Override
    public String update(CVUpdateForm cvUpdateForm) {
        CurriculumVitae curriculumVitae = null;
        boolean shouldReturnResourceUrl = false;
        if (cvUpdateForm.getUserId() == null || !profileService.profileExists(cvUpdateForm.getUserId()))
            throw new IllegalStateException("CV owner does not exist");
        //проверяем есть ли id резюме
        if (cvUpdateForm.getId() != null) {
            curriculumVitae = findCVById(cvUpdateForm.getId());
        }
        if (curriculumVitae != null) {
            //проверяем совпадение айдишников владельцев после чего обновляем сущность
            if (!curriculumVitae.getUserId().toString().equals(cvUpdateForm.getUserId()))
                throw new IllegalArgumentException("Can't update entity which is owned by another user");
            curriculumVitae.setLocation(cvUpdateForm.getLocation());
            curriculumVitae.setTitle(cvUpdateForm.getTitle());
            curriculumVitae.setAbout(cvUpdateForm.getAbout());
            curriculumVitae.setCompetences(cvUpdateForm.getCompetences());
            curriculumVitae.setLanguages(cvUpdateForm.getLanguages());
            curriculumVitae.setPreviousWorks(cvUpdateForm.getPreviousWorks());
            curriculumVitae.setEducations(cvUpdateForm.getEducations());
        } else {
            //создаём новое резюме, сохраняем под поступившим id, если он есть
            curriculumVitae = cvMapper.cvFromCVBasicForm(cvUpdateForm);
            if (cvUpdateForm.getId() != null) curriculumVitae.setId(new ObjectId(cvUpdateForm.getId()));
            shouldReturnResourceUrl = true;
        }
        curriculumVitaeRepository.save(curriculumVitae);
        if (shouldReturnResourceUrl) return "/" + curriculumVitae.getId().toString();

        return null;
    }

    private CurriculumVitae findCVById(String id) {
        return curriculumVitaeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CV not found"));
    }
}
