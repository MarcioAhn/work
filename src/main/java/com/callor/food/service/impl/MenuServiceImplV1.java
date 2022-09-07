package com.callor.food.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.callor.food.config.MenuConfig;
import com.callor.food.model.DosungCOOK;
import com.callor.food.model.MenuVO;
import com.callor.food.persistance.MenuDao;
import com.callor.food.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MenuServiceImplV1 implements MenuService{

	
	@Autowired
	private MenuDao postDao;
	
	@Override
	public String queryString(String cat, String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJsonString(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuVO> getAllRecipes() {
		
		URI restURI = null;
		try {
			restURI = new URI(MenuConfig.FULL_URL);
		} catch (URISyntaxException e) {
			log.debug("URI 오류 (getAllRecipes)");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("Parameter", headers);

		ResponseEntity<DosungCOOK> resData = null;
		RestTemplate restTemp = new RestTemplate();
		resData = restTemp.exchange(restURI, HttpMethod.GET, entity, DosungCOOK.class);
		return resData.getBody().COOKRCP01.row;
		
	}

	@Override
	public List<MenuVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MenuVO findById(String id) {
		return postDao.findById(id);
	}

	@Override
	public int insert(MenuVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MenuVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
