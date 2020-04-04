package io.argdev.ppmtool.services;

import io.argdev.ppmtool.domain.Project;
import io.argdev.ppmtool.exceptions.DuplicateProjectIdentifierException;
import io.argdev.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {

        //TODO check for duplicate project identifier before saving to DB
        if (this.projectRepository.existsProjectByProjectIdentifier(project.getProjectIdentifier()))
            throw new DuplicateProjectIdentifierException(project.getProjectIdentifier());

        return this.projectRepository.save(project);
    }
}
