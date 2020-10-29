package com.sweetsixteen.cv.controllers;

import com.sweetsixteen.cv.dtos.CurriculumVitaeDto;
import com.sweetsixteen.cv.forms.CVBasicForm;
import com.sweetsixteen.cv.forms.CVUpdateForm;
import com.sweetsixteen.cv.services.interfaces.CVManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cv")
public class CVManageController {
    private final CVManageService cvManageService;

    @GetMapping("/{id}")
    public ResponseEntity<CurriculumVitaeDto> getCVById(@PathVariable("id") String id) {
        return ResponseEntity.ok(cvManageService.getCVById(id));
    }

    @PostMapping
    public ResponseEntity<CurriculumVitaeDto> addCV(@RequestBody CVBasicForm cvBasicForm) {
        return ResponseEntity.ok(cvManageService.addCV(cvBasicForm));
    }

    @PutMapping
    public ResponseEntity<?> updateCV(@RequestBody CVUpdateForm cvUpdateForm) {
        String url = cvManageService.update(cvUpdateForm);
        //если добавили, то возвращаем адрес ресурса
        if(url != null) return ResponseEntity.created(URI.create(url)).build();
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<CurriculumVitaeDto>> getAllCV() {
        return ResponseEntity.ok(cvManageService.findAllCV());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCV(@PathVariable("id") String id) {
        cvManageService.delete(id);
        return ResponseEntity.ok().build();
    }
}
