package io.argdev.ppmtool.services;

import io.argdev.ppmtool.domain.Project;
import io.argdev.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {
        return this.projectRepository.save(project);
    }
}
