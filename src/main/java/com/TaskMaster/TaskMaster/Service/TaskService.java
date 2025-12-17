package com.TaskMaster.TaskMaster.Service;
import com.TaskMaster.TaskMaster.Model.DTO.TaskRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(Long projectId, TaskRequestDTO request, String username);

    List<TaskResponseDTO> getTasksByProject(Long projectId, String username);

    TaskResponseDTO updateTask(Long taskId, TaskRequestDTO request, String username);

    void deleteTask(Long taskId, String username);

    List<TaskResponseDTO> searchTasks(String keyword, String sortBy, String username);
}
