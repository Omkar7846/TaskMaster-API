package com.TaskMaster.TaskMaster.Model.DTO;


import java.time.LocalDateTime;

import com.TaskMaster.TaskMaster.Model.TaskEnums.TaskPriority;
import com.TaskMaster.TaskMaster.Model.TaskEnums.TaskStatus;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequestDTO {
    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    @NotNull(message = "Status is required (PENDING, IN_PROGRESS, COMPLETED)")
    private TaskStatus status;

    @NotNull(message = "Priority is required (LOW, MEDIUM, HIGH)")
    private TaskPriority priority;

    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDateTime dueDate;

	public TaskRequestDTO(@NotBlank(message = "Task title is required") String title, String description,
			@NotNull(message = "Status is required (PENDING, IN_PROGRESS, COMPLETED)") TaskStatus status,
			@NotNull(message = "Priority is required (LOW, MEDIUM, HIGH)") TaskPriority priority,
			@FutureOrPresent(message = "Due date cannot be in the past") LocalDateTime dueDate) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.dueDate = dueDate;
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

	@Override
	public String toString() {
		return "TaskRequestDTO [title=" + title + ", description=" + description + ", status=" + status + ", priority="
				+ priority + ", dueDate=" + dueDate + "]";
	}
    
}