/**
 * 
 */
package com.homework.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;
import com.homework.todolist.model.pojo.GetTodoResponse;
import com.homework.todolist.model.pojo.JsonResponse;
import com.homework.todolist.model.pojo.SaveTodo;
import com.homework.todolist.service.TodoService;

/**
 * @author Incheol Jung
 */
@Controller
@CrossOrigin
@RequestMapping(value = "", produces = "application/json")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String getTodoList(GetTodoParameter parameter) {
        return "index";
    }
	
	@ResponseBody
	@RequestMapping(value="/todos", method=RequestMethod.GET)
    public JsonResponse<GetTodoResponse> getTodos(GetTodoParameter parameter) {
		JsonResponse<GetTodoResponse> result = new JsonResponse<GetTodoResponse>();
		result.setData(todoService.getTodos(parameter));
        return result;
    }
	
	@ResponseBody
	@RequestMapping(value="/todos", method=RequestMethod.POST)
    public JsonResponse<Todo> insertTodo(@RequestBody SaveTodo parameter) throws Exception {
		JsonResponse<Todo> result = new JsonResponse<Todo>();
		result.setData(todoService.saveTodo(null, parameter));
        return result;
    }
	
	@ResponseBody
	@RequestMapping(value="/todos/{todoId}", method=RequestMethod.PUT)
    public JsonResponse<Todo> updateTodo(@PathVariable Integer todoId, @RequestBody SaveTodo parameter) throws Exception {
		JsonResponse<Todo> result = new JsonResponse<Todo>();
		result.setData(todoService.saveTodo(todoId, parameter));
        return result;
    }
	
	@ResponseBody
	@RequestMapping(value="/todos/{todoId}", method=RequestMethod.DELETE)
    public JsonResponse<String> deleteTodo(@PathVariable Integer todoId) {
		JsonResponse<String> result = new JsonResponse<String>();
		result.setData(todoService.deleteTodo(todoId));
        return result;
    }
}