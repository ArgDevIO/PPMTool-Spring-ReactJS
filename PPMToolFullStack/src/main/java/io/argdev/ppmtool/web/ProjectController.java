package io.argdev.ppmtool.web;

import io.argdev.ppmtool.domain.Project;
import io.argdev.ppmtool.services.MapValidationErrorService;
import io.argdev.ppmtool.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectService projectService;
    private MapValidationErrorService mapValidationErrorService;

    public ProjectController(ProjectService projectService, MapValidationErrorService mapValidationErrorService) {
        this.projectService = projectService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
        if (errorMap != null) return errorMap;
        
        return new ResponseEntity<>(this.projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
    }
}
