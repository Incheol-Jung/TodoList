/**
 * 
 */
package com.homework.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;
import com.homework.todolist.service.TodoService;

/**
 * @author Incheol Jung
 */
@Controller 
@RequestMapping("/todo/")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.GET)
    public List<Todo> getTodoList(GetTodoParameter parameter) {
        return todoService.getTodo();
    }
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
    public String saveTodo(@RequestBody Todo todo) {
        return todoService.saveTodo();
    }
	
	@ResponseBody
	@RequestMapping(value="finish/{id}", method=RequestMethod.POST)
    public String finishTodo(@PathVariable Integer id) {
        return todoService.saveTodo();
    }
	
	@ResponseBody
	@RequestMapping(value="unfinish/{id}", method=RequestMethod.POST)
    public String unfinishTodo(@PathVariable Integer id) {
        return todoService.saveTodo();
    }
	
	@ResponseBody
	@RequestMapping(value="{id}/refer/{referId}", method=RequestMethod.POST)
    public String addReferenceId(@PathVariable Integer id, @PathVariable Integer referId) {
        return todoService.saveTodo();
    }
	
	@ResponseBody
	@RequestMapping(value="{id}/refer/{referId}", method=RequestMethod.DELETE)
    public String deleteReferenceId(@PathVariable Integer id, @PathVariable Integer referId) {
        return todoService.saveTodo();
    }
}