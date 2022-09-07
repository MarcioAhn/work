package com.callor.food.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callor.food.model.FoodVO;
import com.callor.food.persistance.FoodDao;
import com.callor.food.service.FoodService;

@Service
public class FoodServiceImplV1 implements FoodService{

	@Autowired
	private FoodDao foodDao;
	
	// username 사용자의 foodList Dao 로부터 SELECT 하여
	// 즉시 return
	@Override
	public List<FoodVO> findByUsername(String username) {
		// food Auto-generated method stub
		return foodDao.findByUsername(username);
	}

	@Override
	public List<FoodVO> selectAll() {
		// food Auto-generated method stub
			return foodDao.selectAll();
	}

	@Override
	public FoodVO findById(Long id) {
		// food Auto-generated method stub
		return foodDao.findById(id);
	}

	@Override
	public int insert(FoodVO vo) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeForm = new SimpleDateFormat("HH:mm:SS");
		vo.setT_sdate(dateForm.format(date));
		vo.setT_stime(timeForm.format(date));
		return foodDao.insert(vo);
	}

	@Override
	public int update(FoodVO vo) {
		return foodDao.update(vo);
	}

	@Override
	public int delete(Long id) {
		return foodDao.delete(id);
	}

	@Override
	public void create_food_table() {
		// food Auto-generated method stub
		
	}

	@Override
	public int foodComp(String t_seq) {

		long seq = 0L; 
		try {
			seq = Long.valueOf(t_seq);
		} catch (Exception e) {
			return -1;
		}
		FoodVO foodVO = foodDao.findById(seq);
		if(foodVO == null) {
			return -1;
		}
		
		String edate = foodVO.getT_edate();
		if(edate == null || edate.isEmpty()) {
			// Java 1.8 이상에서 사용하는 새로은 날짜시간 클래스
			LocalDateTime dateTime = LocalDateTime.now();
			DateTimeFormatter dateFormat 
				= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter timeFormat
				= DateTimeFormatter.ofPattern("HH:mm:ss");
			
			foodVO.setT_edate(dateTime.format(dateFormat));
			foodVO.setT_etime(dateTime.format(timeFormat));
		} else {
			foodVO.setT_edate("");
			foodVO.setT_etime("");
		}
		
		/*
		 * VO 의 변수가 boolean type 일 경우
		 * set method 는 일반적인 setter method pattern을 따르는데
		 * get method 는 is변수명() 형태의 pattern으로 변경된다
		 */
		foodVO.setT_complete( !foodVO.isT_complete() );
		int ret = foodDao.update(foodVO);
		
		return ret;
	}

	@Override
	public FoodVO findByFood(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
