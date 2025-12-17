package com.TaskMaster.TaskMaster.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskMaster.TaskMaster.Model.DTO.TaskRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.TaskResponseDTO;
import com.TaskMaster.TaskMaster.Service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/projects/{projectId}/tasks")
	public ResponseEntity<TaskResponseDTO> createTask(@PathVariable Long projectId,
			@Valid @RequestBody TaskRequestDTO request, @AuthenticationPrincipal UserDetails userDetails) {
		TaskResponseDTO response = taskService.createTask(projectId, request, userDetails.getUsername());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/projects/{projectId}/tasks")
	public ResponseEntity<List<TaskResponseDTO>> getTasksByProject(@PathVariable Long projectId,
			@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(taskService.getTasksByProject(projectId, userDetails.getUsername()));
	}

	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long taskId,
			@Valid @RequestBody TaskRequestDTO request, @AuthenticationPrincipal UserDetails userDetails) {
		TaskResponseDTO response = taskService.updateTask(taskId, request, userDetails.getUsername());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/tasks/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long taskId,
			@AuthenticationPrincipal UserDetails userDetails) {
		taskService.deleteTask(taskId, userDetails.getUsername());
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/tasks/search")
	public ResponseEntity<List<TaskResponseDTO>> searchTasks(@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "createdAt") String sortBy, @AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(taskService.searchTasks(keyword, sortBy, userDetails.getUsername()));
	}
}