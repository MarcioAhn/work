package com.callor.food.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.food.model.MenuVO;
import com.callor.food.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	private MenuService post;
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String home(Locale locale, Model model, Principal principal, HttpSession httpSession) {
	
		if(principal != null) {
			model.addAttribute("userID",principal.getName()); 
		}
		
		List<MenuVO> recipeList = new ArrayList<MenuVO>();
		recipeList = post.getAllRecipes();
		httpSession.setAttribute("FullList", recipeList);
		List<MenuVO> randList = new ArrayList<MenuVO>();
		for(int i = 0 ; i < 25 ; i++) {
			int intRan1 = (int) (Math.random()* recipeList.size()) ;
			randList.add(recipeList.get(intRan1));
		}
		model.addAttribute("RECIPES", randList);
		return "home";
	}
	
	@RequestMapping(value="/403",method=RequestMethod.GET)
	public String error403() {
		return "error/403";
	}
	
	
}
