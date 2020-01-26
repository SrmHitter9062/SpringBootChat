package com.srmhitter9062.controller;

import com.srmhitter9062.model.Todo;
import com.srmhitter9062.repository.TodoRepository;
import com.srmhitter9062.service.ApiResponse;
import com.srmhitter9062.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todo/{id}")
    public ResponseEntity<Object> getTodoById(@PathVariable String id) {
        int todoId = Integer.parseInt(id);
        try {
            Todo todoItem = todoRepository.findById(todoId);
            if(todoItem == null) {
                return new ResponseEntity<>(new ApiResponse("success",null,"no data", 200), HttpStatus.OK);
            }
            return ResponseEntity.ok(new ApiResponse("success",todoItem,null, 200));

        }catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("failure",e.getLocalizedMessage(),null, 500),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * get all todo list
     * @return
     */
    @GetMapping("/todos")
    public ResponseEntity<ApiResponse> getAllTodos() {
        try {
            List<Todo> allTodos = todoRepository.findAll();
            return ResponseEntity.ok(new ApiResponse("success",allTodos,null, 200));
        } catch(Exception e){
            return new ResponseEntity<>(new ApiResponse("failure",e.getLocalizedMessage(),null, 500),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * create new todo item
     * @param
     * @return
     */
    @PostMapping("/todo")
    public ResponseEntity<ApiResponse> createUser(@RequestBody Map<String, Object>  body) {
        boolean isCompleted = body.get("completed").equals(0) ? false : true;
        Timestamp updateAt = new Timestamp(System.currentTimeMillis());
        Timestamp createdAt = updateAt;
        Todo todoItem = new Todo((String)body.get("title"),isCompleted,(String)body.get("status"),createdAt,updateAt);
        try {
            Todo createdTodo = todoRepository.save(todoItem);
            return ResponseEntity.ok(new ApiResponse("success",createdTodo,null,200));
        } catch (Exception e) {
            // log
            return new ResponseEntity<>(new ApiResponse("failure",e.getLocalizedMessage(),null, 500),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * update existing todo item
     */
    @PutMapping("/todo/{id}")
    public ResponseEntity<ApiResponse> updateTodo(@PathVariable(value = "id") int todoId, @RequestBody Map<String, Object>  body) {
        Todo todoItem = todoRepository.findById(todoId);
        if(todoItem == null) {
            return ResponseEntity.ok(new ApiResponse("success",null,"no data is to update",200));
        }
        body.put("id",todoId);
        TodoService todoService = new TodoService();
        Todo todoObject = todoService.getTodoObjectFromRequestBody(body);
        if(todoObject.getTitle() != null)todoItem.setTitle(todoObject.getTitle());
        if(todoObject.getStatus() != null)todoItem.setStatus(todoObject.getStatus());
        todoItem.setIsCompleted(todoObject.getIsCompleted());
        try {
            final Todo updatedTodo = todoRepository.save(todoItem);
            return ResponseEntity.ok(new ApiResponse("success",updatedTodo,"data updated",200));
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("failure",e.getLocalizedMessage(),null, 500),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * deletes the todo item
     * @param todoId
     * @return
     * @throws Exception
     */
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ApiResponse> deleteTodo(@PathVariable(value = "id") int todoId) throws Exception {
        Todo todoItem = todoRepository.findById(todoId);
        if(todoItem == null) {
            return ResponseEntity.ok(new ApiResponse("success",null,"no data is to delete",200));
        }
        try {
            todoRepository.delete(todoItem);
            return ResponseEntity.ok(new ApiResponse("success","data is deleted",null,200));
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse("failure",e.getLocalizedMessage(),null, 500),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

