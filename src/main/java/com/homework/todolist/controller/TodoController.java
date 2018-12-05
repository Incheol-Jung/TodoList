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
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.GET)
    public List<Todo> getTodoList(GetTodoParameter parameter) {
        return todoService.getTodoList(parameter);
    }
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
    public String saveTodo(@RequestBody Todo todo) {
        return todoService.saveTodo(todo);
    }
	
	@ResponseBody
	@RequestMapping(value="/{id}/finish/{isDone}", method=RequestMethod.POST)
    public String finishTodo(@PathVariable Integer id, @PathVariable boolean isDone) {
        return todoService.finishTodo(id, isDone);
    }

	@ResponseBody
	@RequestMapping(value="{id}/refer/{referenceId}", method=RequestMethod.POST)
    public String addReferenceId(@PathVariable Integer id, @PathVariable Integer referenceId) {
        return todoService.addReferenceId(id, referenceId);
    }
	
	@ResponseBody
	@RequestMapping(value="{id}/refer/{referenceId}", method=RequestMethod.DELETE)
    public String deleteReferenceId(@PathVariable Integer id, @PathVariable Integer referenceId) {
        return todoService.deleteReferenceId(id, referenceId);
    }
}