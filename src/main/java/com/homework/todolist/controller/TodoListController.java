/**
 * 
 */
package com.homework.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Incheol Jung
 */
@Controller
public class TodoListController {
	@RequestMapping(value="/", method=RequestMethod.GET)
    public String getTodoList() {
        return "shortener";
    }
}