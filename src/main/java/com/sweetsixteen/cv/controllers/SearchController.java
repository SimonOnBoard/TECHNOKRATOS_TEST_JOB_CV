package com.sweetsixteen.cv.controllers;


import com.sweetsixteen.cv.dtos.CurriculumVitaeDto;
import com.sweetsixteen.cv.services.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cv/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/competences")
    public ResponseEntity<List<CurriculumVitaeDto>> findByCompetences(@RequestParam(name = "count") int count,
                                                                      @RequestParam(name = "competence") List<String> competences) {
        return ResponseEntity.ok(searchService.findAllWithCountCompetencesAndContainsListOfCompetences(count, competences));
    }

    @GetMapping("/mainProperties")
    public ResponseEntity<List<CurriculumVitaeDto>> findByTitleAndAboutAndLocation(@RequestParam(name = "title") String title,
                                                                                   @RequestParam(name = "about") String about,
                                                                                   @RequestParam(name = "location") String location) {
        return ResponseEntity.ok(searchService.findByTitleAndAboutAndLocationAllIgnoreCase(title, about, location));
    }

}
