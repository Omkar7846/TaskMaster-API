package com.TaskMaster.TaskMaster.Service;

import java.util.List;

import com.TaskMaster.TaskMaster.Model.DTO.ProjectRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.ProjectResponseDTO;

public interface ProjectService {

    ProjectResponseDTO createProject(ProjectRequestDTO request, String username);

    List<ProjectResponseDTO> getAllProjects(String username);

    ProjectResponseDTO getProjectById(Long projectId, String username);

    ProjectResponseDTO updateProject(Long projectId, ProjectRequestDTO request, String username);

    void deleteProject(Long projectId, String username);
}