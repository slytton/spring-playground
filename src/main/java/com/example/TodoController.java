package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gschool on 2/19/17.
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    Map<Integer, Todo> todos = createTodos();

    @GetMapping({"/", ""})
    public String getTodos() {
        String result = "Here are all of your todos:";
        for (Map.Entry<Integer, Todo> todo : todos.entrySet()) {
            result += formatTodo(todo.getValue());
        }
        return result;
    }

    @GetMapping({"/{todoId}", "/{todoId}/"})
    public String getTodo(@PathVariable Integer todoId) {
        Todo todo = todos.get(todoId);
        String result = String.format("You requested todo number %d:", todoId) +
                formatTodo(todo);

        return result;
    }

    @GetMapping({"/{todoId}/subtodos/{subTodoId}", "/{todoId}/subtodos/{subTodoId}/"})
    public String getSubTodo(@PathVariable Map<String, String> pathVars) {
        Integer todoId = Integer.parseInt(pathVars.get("todoId"));
        Integer subTodoId = Integer.parseInt(pathVars.get("subTodoId"));
        Todo todo = todos.get(todoId);
        SubTodo subDo = todo.getSubTodo(subTodoId);

        String result = String.format("You requested todo number %d and subtodo %d:", todoId, subTodoId) +
                formatTodo(subDo);

        return result;
    }

    @PatchMapping({"/{todoId}/subtodos/{subTodoId}", "/{todoId}/subtodos/{subTodoId}/"})
    public String patchSubTodo(TodoRequestPath pathVars) {

        return String.format("Successfully updated todo #%s subtodo #%d", pathVars.getTodoId(), pathVars.getSubTodoId());
    }

    private String formatTodo(Todo todo) {
        String result = "";
        result += String.format("\n%d: %s", todo.getId(), todo.getText());
        List<SubTodo> subTodos = todo.getSubTodos();
        if (subTodos != null) {
            for (SubTodo subDo : subTodos) {
                result += String.format("\n\t%d: %s", subDo.getId(), subDo.getText());
            }
        }

        return result;
    }


    private Map<Integer, Todo> createTodos() {
        Map<Integer, Todo> map = new HashMap<Integer, Todo>();
        map.put(1, new Todo(1, "Buy pants", null));
        map.put(2, new Todo(2, "Put pants on", null));
        map.put(3, new Todo(3, "Look really", new String[]{"really", "good"}));
        return map;
    }


    static public class TodoRequestPath {
        private Integer todoId;
        private Integer subTodoId;

        public TodoRequestPath(){}

        public Integer getTodoId() {
            return todoId;
        }

        public void setTodoId(Integer todoId) {
            this.todoId = todoId;
        }

        public Integer getSubTodoId() {
            return subTodoId;
        }

        public void setSubTodoId(Integer subTodoId) {
            this.subTodoId = subTodoId;
        }
    }

    static public class Todo {
        private Integer id;
        private String text;
        private List<SubTodo> subTodos;

        public Todo() {}

        public Todo(Integer id, String text, String[] subTodos) {
            this.id = id;
            this.text = text;

            if (subTodos != null) {
                this.subTodos = new ArrayList<>();
                for (int i = 0; i < subTodos.length; i++) {
                    int subDoId = i + 1;
                    String subTodoText = subTodos[i];
                    this.subTodos.add(new SubTodo(subDoId, subTodoText, null, this));
                }
            }
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<SubTodo> getSubTodos() {
            return subTodos;
        }

        public void setSubTodos(List<SubTodo> subTodos) {
            this.subTodos = subTodos;
        }

        public void addSubTodo(String subTodo) {
            this.subTodos.add(new SubTodo(this.subTodos.size(), subTodo, null, this));
        }

        public SubTodo getSubTodo(Integer id) {
            return this.subTodos.get(id - 1);
        }

        public void removeSubTodo(Integer subTodoId) {
            this.subTodos.remove(subTodoId);
        }
    }

    static public class SubTodo extends Todo{

        private Todo parent;

        public SubTodo(Integer id, String text, String[] subTodos, Todo parent) {
            super(id, text, subTodos);
            this.parent = parent;
        }


        public Todo getParent() {
            return parent;
        }

        public void setParent(Todo parent) {
            this.parent = parent;
        }
    }
}
