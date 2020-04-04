package io.argdev.ppmtool.services;

import io.argdev.ppmtool.domain.Project;
import io.argdev.ppmtool.exceptions.ProjectIdException;
import io.argdev.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {

        if (this.projectRepository.existsProjectByProjectIdentifier(project.getProjectIdentifier()))
            throw new ProjectIdException("Project Identifier '" + project.getProjectIdentifier() + "' already exists!");

        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        return this.projectRepository.save(project);
    }

    public Project findProjectByIdentifier(String projectIdentifier) {

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if (project == null)
            throw new ProjectIdException("Project Identifier '" + projectIdentifier.toUpperCase() + "' doesn't exist!");

        return projectRepository.findByProjectIdentifier(projectIdentifier);
    }
}
