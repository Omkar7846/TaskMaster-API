package com.TaskMaster.TaskMaster.Service;

import com.TaskMaster.TaskMaster.Model.DTO.LoginRequestDTO;
import com.TaskMaster.TaskMaster.Model.DTO.RegisterRequestDTO;

public interface AuthService {
    void register(RegisterRequestDTO request);

    String login(LoginRequestDTO request);
}