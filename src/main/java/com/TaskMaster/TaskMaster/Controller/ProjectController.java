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
import org.springframework.web.bind.annotation.RestController;

import com.TaskMaster.TaskMaster.Model.DTO.ProjectRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.ProjectResponseDTO;
import com.TaskMaster.TaskMaster.Service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@GetMapping
	public ResponseEntity<List<ProjectResponseDTO>> getAllProjects(@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(projectService.getAllProjects(userDetails.getUsername()));
	}

	@PostMapping
	public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO request,
			@AuthenticationPrincipal UserDetails userDetails) {
		ProjectResponseDTO response = projectService.createProject(request, userDetails.getUsername());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectResponseDTO> getProject(@PathVariable Long id,
			@AuthenticationPrincipal UserDetails userDetails) {
		return ResponseEntity.ok(projectService.getProjectById(id, userDetails.getUsername()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id,
			@Valid @RequestBody ProjectRequestDTO request, @AuthenticationPrincipal UserDetails userDetails) {
		ProjectResponseDTO response = projectService.updateProject(id, request, userDetails.getUsername());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		projectService.deleteProject(id, userDetails.getUsername());
		return ResponseEntity.noContent().build();
	}
}