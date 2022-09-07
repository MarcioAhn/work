package com.callor.food.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.callor.food.model.AddressVO;
import com.callor.food.model.SearchPage;
import com.callor.food.persistance.AddressDao;
import com.callor.food.service.AddressService;

@Service
public class AddressServiceImplV1 implements AddressService {

	@Autowired
	private AddressDao addrDao;

	

	@Override
	public List<AddressVO> searchAndPage(SearchPage searchPage) {
		// TODO Auto-generated method stub
		return addrDao.searchAndPage(searchPage);
	}

	// 한 페이지에 보여질 데이터 리스트 개수
	private static final long LIST_PER_PAGE = 10;

	// 화면 하단에 페이지 번호 출력 개수
	private static final long PAGE_NO_COUNT = 10;

	/*
	 * 조건에 맞는 데이터를 가져와서 pagination 을 그리기 위한 SearchPage 클래스의 데이터를 만들 것
	 */

	@Override
	public void searchAndPage(Model model, SearchPage searchPage) {
		// pagination을 구현하기 위하여 전체 데이터 가져오기로
		// 개수를 임시로 세팅
		searchPage.setOffset(0);
		searchPage.setLimit(addrDao.selectAll().size());

		// 1. 전체 데이터 개수가 몇개인지를 알아야 pagination 을 구현할수 있다
		// 화면 하단의 페이지 번호를 자동으로 계산하기 위함이다

		// 검색어가 없이 요청이 될경우 SearchPage 객체의 search 값이 null 이되어
		// 데이터가 검색이 되지 않는다
		// 때문에 SearchPage 객체의 search 변수값을 "" 으로 세팅
		String search = searchPage.getSearch();
		search = search == null ? "" : search;
		searchPage.setSearch(search);

		// 검색어 조건에 맞는 모든 데이터를 일단 select
		List<AddressVO> addrList = addrDao.searchAndPage(searchPage);

		long totalCount = addrList.size();
		if (totalCount < 1)
			return;

		// 전체 데이터의 총 페이지수
		// 전체 데이터 개수를 한 페이지에 보여질 데이터 개수로 나누기
		// 만약 전체 데이터가 37 개이고 한페이지에 10개를 보여준다 면
		// finalPageNo 는 3이 될 것이다
		long finalPageNo = totalCount / LIST_PER_PAGE;

		// 화면 하단의 페이지번호를 클릭했을때 전달되는 값
		long currentPageNo = searchPage.getCurrentPageNo();

		// 삼항 연산자를 사용할때
		currentPageNo = currentPageNo > 1 ? (currentPageNo > finalPageNo ? finalPageNo : currentPageNo) : 1;

		// 일반적인 if 문을 사용할때
		if (currentPageNo > finalPageNo) {
			currentPageNo = finalPageNo;
		}
		if (currentPageNo < 1) {
			currentPageNo = 1;
		}

		// 선택된 페이지번호에 따라 화면하단 번호 리스트를 유동적으로
		// 보여주기 위한 연산
		long startPageNo = (currentPageNo / PAGE_NO_COUNT) * PAGE_NO_COUNT;
		startPageNo = startPageNo < 1 ? 1 : startPageNo;

		long endPageNo = startPageNo + PAGE_NO_COUNT - 1;
		endPageNo = endPageNo > finalPageNo ? finalPageNo : endPageNo;

		searchPage.setStartPageNo(startPageNo);
		searchPage.setEndPageNo(endPageNo);
		searchPage.setLimit(LIST_PER_PAGE);
		searchPage.setPageNoCount(PAGE_NO_COUNT);
		searchPage.setOffset((currentPageNo - 1) * PAGE_NO_COUNT);
		searchPage.setFinalPageNo(finalPageNo);

		// JSP 로 보내기 위해서 model 에 담기
		model.addAttribute("PAGE", searchPage);

	}

	@Override
	public List<AddressVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressVO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(AddressVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AddressVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create_addr_table() {
		// TODO Auto-generated method stub
		addrDao.create_addr_table();
		
	}

}
