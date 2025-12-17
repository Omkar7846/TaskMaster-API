package com.TaskMaster.TaskMaster.ServiceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TaskMaster.TaskMaster.Entity.Project;
import com.TaskMaster.TaskMaster.Entity.User;
import com.TaskMaster.TaskMaster.Exceptions.ResourceNotFoundException;
import com.TaskMaster.TaskMaster.Model.DTO.ProjectRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.ProjectResponseDTO;
import com.TaskMaster.TaskMaster.Repository.ProjectRepository;
import com.TaskMaster.TaskMaster.Repository.UserRepository;
import com.TaskMaster.TaskMaster.Service.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	@Autowired
    private ProjectRepository projectRepository;
	@Autowired
    private UserRepository userRepository;

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
    }

    private ProjectResponseDTO mapToResponse(Project project) {
        return new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO request, String username) {
        User user = getUser(username);

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setUser(user);

        Project savedProject = projectRepository.save(project);
        return mapToResponse(savedProject);
    }

    @Override
    public List<ProjectResponseDTO> getAllProjects(String username) {
        User user = getUser(username);
        List<Project> projects = projectRepository.findByUser(user);
        
        return projects.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDTO getProjectById(Long projectId, String username) {
        User user = getUser(username);
        Project project = projectRepository.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found or access denied for ID: " + projectId));
        return mapToResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponseDTO updateProject(Long projectId, ProjectRequestDTO request, String username) {
        User user = getUser(username);
        Project project = projectRepository.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found or access denied for ID: " + projectId));

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        
        Project updatedProject = projectRepository.save(project);
        return mapToResponse(updatedProject);
    }

    @Override
    public void deleteProject(Long projectId, String username) {
        User user = getUser(username);
        Project project = projectRepository.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found or access denied for ID: " + projectId));
        
        projectRepository.delete(project);
    }
}