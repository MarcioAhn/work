package com.callor.food.persistance;

import java.util.List;

import com.callor.food.model.AddressVO;
import com.callor.food.model.FoodVO;
import com.callor.food.model.SearchPage;

public interface AddressDao extends GenericDao<AddressVO, Long>{

	public List<AddressVO> searchAndPage(SearchPage searchPage);

	public void create_addr_table();
}
