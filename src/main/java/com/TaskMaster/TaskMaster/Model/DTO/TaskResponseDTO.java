package com.TaskMaster.TaskMaster.Model.DTO;
import java.time.LocalDateTime;

import com.TaskMaster.TaskMaster.Model.TaskEnums.TaskPriority;
import com.TaskMaster.TaskMaster.Model.TaskEnums.TaskStatus;

public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long projectId;
	public TaskResponseDTO(Long id, String title, String description, TaskStatus status, TaskPriority priority,
			LocalDateTime dueDate, LocalDateTime createdAt, LocalDateTime updatedAt, Long projectId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.dueDate = dueDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.projectId = projectId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public TaskPriority getPriority() {
		return priority;
	}
	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	@Override
	public String toString() {
		return "TaskResponseDTO [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", priority=" + priority + ", dueDate=" + dueDate + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", projectId=" + projectId + "]";
	} 
    
}