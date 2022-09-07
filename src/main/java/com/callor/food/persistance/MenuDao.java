package com.callor.food.persistance;

import java.util.List;

import com.callor.food.model.MenuVO;

public interface MenuDao extends GenericDao<MenuVO, String>{

	public String queryString(String cat, String title);
	public String getJsonString(String queryString);
	public List<MenuVO> getAllRecipes();
	
}
