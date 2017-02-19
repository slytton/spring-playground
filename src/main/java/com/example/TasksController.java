package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gschool on 2/18/17.
 */

@RestController
@RequestMapping("/tasks")
public class TasksController {
    @GetMapping(value={"/", ""})
    public String getTask() {
        return "This is a task";
    }

//    @PostMapping("/tasks")
    @PostMapping(value={"/", ""})
    public String createTask() {
        return "You just POSTed to /tasks";
    }

}
