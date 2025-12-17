package com.TaskMaster.TaskMaster.Model.DTO;


import jakarta.validation.constraints.NotBlank;

public class ProjectRequestDTO {
    @NotBlank(message = "Project name is required")
    private String name;
    private String description;
    
	public ProjectRequestDTO(@NotBlank(message = "Project name is required") String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProjectRequestDTO [name=" + name + ", description=" + description + "]";
	} 
    
}