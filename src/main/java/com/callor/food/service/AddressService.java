package com.callor.food.service;

import org.springframework.ui.Model;

import com.callor.food.model.SearchPage;
import com.callor.food.persistance.AddressDao;

public interface AddressService extends AddressDao {
	
	public void searchAndPage(Model model,SearchPage searchPage);

}
