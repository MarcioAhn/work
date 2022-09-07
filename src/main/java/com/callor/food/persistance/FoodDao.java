package com.callor.food.persistance;

import java.util.List;

import com.callor.food.model.FoodVO;
import com.callor.food.model.SearchPage;

public interface FoodDao extends GenericDao<FoodVO, Long>{
	
	// username 을 매개변수로 받아
	// username 사용자의 todoList 를 return
	public List<FoodVO> findByUsername(String username);
	public FoodVO findByFood(Long id);
	public void create_food_table();
	
	
}
