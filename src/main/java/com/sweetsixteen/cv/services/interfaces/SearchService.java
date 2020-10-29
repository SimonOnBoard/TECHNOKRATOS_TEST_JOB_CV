package com.sweetsixteen.cv.services.interfaces;

import com.sweetsixteen.cv.dtos.CurriculumVitaeDto;

import java.util.List;

public interface SearchService {
    List<CurriculumVitaeDto> findAllWithCountCompetencesAndContainsListOfCompetences(int count, List<String> competences);

    List<CurriculumVitaeDto> findByTitleAndAboutAndLocationAllIgnoreCase(String title, String about, String location);
}
