package com.lemonjuice.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemonjuice.rest.DTO.TaskDTO;
import com.lemonjuice.rest.models.TaskEntity;
import com.lemonjuice.rest.services.TaskService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController()
@RequestMapping("/api/tasks")

public class RESTController {
    private static final Logger logger = LogManager.getLogger(RESTController.class);
    private final TaskService taskService;
    private final ObjectMapper objectMapper;

    public RESTController(TaskService taskService, ObjectMapper objectMapper) {
        this.taskService = taskService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/get/all")
    public String getTasks() {
        String json = "";
        try {
            List<TaskEntity> taskEntities = taskService.getAllTasks();
            json = objectMapper.writeValueAsString(taskEntities);
            return json;
        } catch (Exception exception) {
            logger.error("Error with getting tasks");
            json = ResponseEntity.status(500).toString();
            return json;
        }
    }
    @GetMapping("/get/{id}")
    public String readById(@PathVariable long id) {
        String json = "" ;
        try{
            json = objectMapper.writeValueAsString(taskService.getTaskById(id));
            return json;
        }
        catch (Exception exception){
            logger.error("Error with getting by id task");
            json =  ResponseEntity.status(500).toString();
            return json;
        }
    }

    @PostMapping("/add")
        public String createTask(@RequestBody TaskDTO taskDTO) {
        String json = "";
        try{
            logger.info("switching between DTO and common entity");
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setDescription(taskDTO.getDescription());
            taskEntity.setName(taskDTO.getName());
            taskEntity.setId(taskDTO.getId());
            json = objectMapper.writeValueAsString(taskService.createTask(taskEntity));
            return json;
        }
        catch (Exception exception){
            logger.error("Error with adding task");
            json =  ResponseEntity.status(500).toString();
            return json;
        }
    }


    @PutMapping("/update/{id}")
    public String updateTask(@PathVariable long id, @RequestBody TaskDTO taskDTO) {
        String json = "" ;
        try{
            logger.info("switching between DTO and common entity");
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setDescription(taskDTO.getDescription());
            taskEntity.setName(taskDTO.getName());
            taskEntity.setId(taskDTO.getId());
            json = objectMapper.writeValueAsString(taskService.updateTask(id, taskEntity));
            return json;
        }
        catch (Exception exception){
            logger.error("Error with updating task");
            json =  ResponseEntity.status(500).toString();
            return json;
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        String json = "" ;
        try{
            logger.info("parsing deleted task into json");
            json = objectMapper.writeValueAsString(taskService.deleteTask(id));
            return json;
        }
        catch (Exception exception){
            logger.error("Error with deleting task");
            json =  ResponseEntity.status(500).toString();
            return json;
        }
    }
}
