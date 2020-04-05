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

        if (this.projectRepository.existsProjectByProjectIdentifier(project.getProjectIdentifier().toUpperCase()))
            throw new ProjectIdException("Project Identifier '" + project.getProjectIdentifier().toUpperCase() + "' already exists!");

        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        return this.projectRepository.save(project);
    }

    public Project findProjectByIdentifier(String projectIdentifier) {

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if (project == null)
            throw new ProjectIdException("Project Identifier '" + projectIdentifier.toUpperCase() + "' doesn't exist!");

        return projectRepository.findByProjectIdentifier(projectIdentifier);
    }

    public Iterable<Project> findAllProjects() {
        return this.projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier) {

        Project project = this.projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if (project == null)
            throw new ProjectIdException("Can't delete Project with ID: '" + projectIdentifier.toUpperCase() + "'.It doesn't exist!");

        this.projectRepository.delete(project);
    }
}
