package com.example.carcatalog.init;

import com.example.carcatalog.dto.RoleDTO;
import com.example.carcatalog.dto.UserDTO;
import com.example.carcatalog.service.RoleService;
import com.example.carcatalog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());
    private final RoleService roleService;
    private final UserService userService;
    private final JsonReader<RoleDTO> roleReader;
    private final JsonReader<UserDTO> userReader;


    public DataInitializer(
            RoleService roleService,
            UserService userService,
            JsonReader<RoleDTO> roleReader,
            JsonReader<UserDTO> userReader) {
        this.roleService = roleService;
        this.userService = userService;
        this.roleReader = roleReader;
        this.userReader = userReader;
    }

    @Override
    public void run(String... args) {
        String FOLDER = "init/";

        List<RoleDTO> roles = roleReader.readFromJson(FOLDER + "role.json", RoleDTO[].class);
        logger.info("Loaded roles: " + roles.toString());
        roles.forEach(roleService::add);

        List<UserDTO> users = userReader.readFromJson(FOLDER + "user.json", UserDTO[].class);
        logger.info("Loaded users: " + users.toString());
        users.forEach(userService::add);

        List<UserDTO> admins = userReader.readFromJson(FOLDER + "admin.json", UserDTO[].class);
        logger.info("Loaded admins: " + admins.toString());
        admins.forEach(roleService::assign);


    }

    private void populateData() {
    }
}
