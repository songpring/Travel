package com.itwill.domain;

public class PageBean {
	private int count; // 총 게시글 수 - 검색 시 수정해야 하는데 어케하누
	private int pageSize; // 한 페이지에 보여질 게시글 갯수
	private String pageNum; // 현재 보여지는 페이지 번호
	private int currentPage; // 현재 보여지는 페이지 번호
	private int startRow; // 
	private int endRow; // 
	
	private int pageCount; // 총 페이지 수
	private int pageBlock; // 한번에 보여줄 페이지의 갯수
	private int startPage; // 보여지는 페이지의 첫번째 숫자
	private int endPage; // 보여지는 페이지의 마지막 숫자

	private String board_id; // 게시글 종류
	private String etc1;	// 세부게시판 이름
	
	private String searchSelect; // 검색 종류 - 작성자 / 제목
	private String search; // 검색할 스트링
	
	private String regi_id; // 나의 게시판 검색시 필요
	
	
	public String getRegi_id() {
		return regi_id;
	}

	public void setRegi_id(String regi_id) {
		this.regi_id = regi_id;
	}

	public String getEtc1() {
		return etc1;
	}

	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		// 계산하는 메서드 호출
		initPageSet();
	}

	public void initPageSet() {
		pageCount = count/pageSize+(count%pageSize==0?0:1);
		pageBlock=5;
		currentPage = Integer.parseInt(pageNum);
		startPage=(currentPage-1)/pageBlock*pageBlock+1;
		System.out.println(startPage);
		endPage=startPage + pageBlock -1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public String getSearchSelect() {
		return searchSelect;
	}

	public void setSearchSelect(String searchSelect) {
		this.searchSelect = searchSelect;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	

}
