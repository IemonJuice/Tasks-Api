package com.lemonjuice.rest.services;
import com.lemonjuice.rest.DTO.TaskDTO;
import com.lemonjuice.rest.models.TaskEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
public class TaskService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void TaskService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<TaskEntity> getAllTasks() {
        return entityManager.createQuery("SELECT task FROM TaskEntity task", TaskEntity.class).getResultList();
    }

    @Transactional
    public TaskEntity getTaskById(Long id) {
        return entityManager.find(TaskEntity.class, id);
    }

    @Transactional
    public TaskEntity createTask(TaskEntity task) {
        entityManager.persist(task);
        return task;
    }

    @Transactional
    public TaskEntity updateTask(Long id, TaskEntity task) {
        TaskEntity existingTask = entityManager.find(TaskEntity.class, id);
        if (existingTask != null) {
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            entityManager.merge(existingTask);
            return existingTask;
        } else {
            return null;
        }
    }

    @Transactional
    public TaskEntity deleteTask(Long id) {
        TaskEntity task = entityManager.find(TaskEntity.class, id);
        if (task != null) {
            entityManager.remove(task);
            return task;
        }
        else{
            return null;
        }
    }
}
