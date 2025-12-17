package com.TaskMaster.TaskMaster.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TaskMaster.TaskMaster.Entity.Project;
import com.TaskMaster.TaskMaster.Entity.Task;
import com.TaskMaster.TaskMaster.Entity.User;
import com.TaskMaster.TaskMaster.Exceptions.ResourceNotFoundException;
import com.TaskMaster.TaskMaster.Model.DTO.TaskRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.TaskResponseDTO;
import com.TaskMaster.TaskMaster.Repository.ProjectRepository;
import com.TaskMaster.TaskMaster.Repository.TaskRepository;
import com.TaskMaster.TaskMaster.Repository.UserRepository;
import com.TaskMaster.TaskMaster.Service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
    private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;

    private TaskResponseDTO mapToResponse(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getProject().getId()
        );
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
    }

    @Override
    public TaskResponseDTO createTask(Long projectId, TaskRequestDTO request, String username) {
        User user = getUser(username);
        
        Project project = projectRepository.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found or access denied for ID: " + projectId));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setProject(project);

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    @Override
    public List<TaskResponseDTO> getTasksByProject(Long projectId, String username) {
        User user = getUser(username);
        Project project = projectRepository.findByIdAndUserId(projectId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found or access denied for ID: " + projectId));

        return taskRepository.findByProject_Id(projectId, Pageable.unpaged()).getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskResponseDTO updateTask(Long taskId, TaskRequestDTO request, String username) {
        User user = getUser(username);
        
        Task task = taskRepository.findByIdAndProject_User(taskId, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found or access denied for ID: " + taskId));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());

        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    @Override
    public void deleteTask(Long taskId, String username) {
        User user = getUser(username);
        Task task = taskRepository.findByIdAndProject_User(taskId, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found or access denied for ID: " + taskId));
        
        taskRepository.delete(task);
    }

    @Override
    public List<TaskResponseDTO> searchTasks(String keyword, String sortBy, String username) {
        User user = getUser(username);
        
        Sort sort;
        if ("dueDate".equalsIgnoreCase(sortBy)) {
            sort = Sort.by(Sort.Direction.ASC, "dueDate");
        } else if ("priority".equalsIgnoreCase(sortBy)) {
            sort = Sort.by(Sort.Direction.DESC, "priority");
        } else {
            sort = Sort.by(Sort.Direction.ASC, "createdAt");
        }

        Pageable pageable = PageRequest.of(0, 100, sort); 

        Page<Task> tasks;
        if (keyword != null && !keyword.trim().isEmpty()) {
            String searchPattern = "%" + keyword.toLowerCase() + "%";
            tasks = taskRepository.searchTasksByUserAndKeyword(user, searchPattern, pageable); 
        } else {
            return List.of(); 
        }
        return tasks.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
