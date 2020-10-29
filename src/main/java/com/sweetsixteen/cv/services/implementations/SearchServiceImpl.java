package com.sweetsixteen.cv.services.implementations;

import com.sweetsixteen.cv.dtos.CurriculumVitaeDto;
import com.sweetsixteen.cv.mappers.CVFormAndDtoMapper;
import com.sweetsixteen.cv.models.CurriculumVitae;
import com.sweetsixteen.cv.repositories.CurriculumVitaeRepository;
import com.sweetsixteen.cv.services.interfaces.ProfileService;
import com.sweetsixteen.cv.services.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final CurriculumVitaeRepository cvRepository;
    private final ProfileService profileService;
    private final CVFormAndDtoMapper cvMapper;

    // TODO: 29.10.2020 how to add case insensitive index on array (within $in operation) without using regex on request
    @Override
    public List<CurriculumVitaeDto> findAllWithCountCompetencesAndContainsListOfCompetences(int count, List<String> competences) {
        List<CurriculumVitae> result = cvRepository.findAllWithCompetencesSizeGreaterThen(count, competences);
        return createListOfDto(result);
    }

    private List<CurriculumVitaeDto> createListOfDto(List<CurriculumVitae> result) {
        return result.stream().map(curriculumVitae ->
                cvMapper.cvDtoFromCVAndUser(curriculumVitae, profileService.findUserById(curriculumVitae.getUserId().toString())))
                .collect(Collectors.toList());
    }

    @Override
    public List<CurriculumVitaeDto> findByTitleAndAboutAndLocationAllIgnoreCase(String title, String about, String location) {
        List<CurriculumVitae> result = cvRepository.findAllByTitleAndAboutAndLocationAllIgnoreCase(title, about, location);
        return createListOfDto(result);
    }


}
