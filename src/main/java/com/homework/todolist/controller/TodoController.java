/**
 * 
 */
package com.homework.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Incheol Jung
 */
@Controller
public class TodoListController {
	
	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String getTodoList() {
        return "TodoList";
    }
}