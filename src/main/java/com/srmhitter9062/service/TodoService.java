package com.srmhitter9062.service;

import com.srmhitter9062.model.Todo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TodoService {

    public Todo getTodoObjectFromRequestBody(Map<String,Object> body) {
        Todo todoItem = new Todo((Integer) body.get("id"));
        if(body.get("title") != null) {
            todoItem.setTitle((String)body.get("title"));
        }
        if(body.get("status") != null) {
            todoItem.setStatus((String)body.get("status"));
        }
        if(body.get("completed") != null) {
            todoItem.setIsCompleted((boolean)body.get("completed"));
        }
        return todoItem;
    }
}
