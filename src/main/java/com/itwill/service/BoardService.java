package com.itwill.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itwill.domain.BoardBean;
import com.itwill.domain.BoardCommentBean;
import com.itwill.domain.FileSettingBean;
import com.itwill.domain.PageBean;

public interface BoardService {

	public int write(BoardBean bb);

	public List<BoardBean> getBoardList(PageBean pageBean);
	
	public BoardBean getArticle(int seq); 
	
	public void fileUpload(FileSettingBean fs);
	
	public String getAjaxData(Map<String, Object> map) throws JsonProcessingException;

	public List<FileSettingBean> getFile();

	public List<BoardBean> getBoardFile(String board_id);

	public Integer getBoardCount(PageBean pageBean);

	public void updateReadcount(int seq);

	public List<BoardBean> getSubBoard(PageBean pageBean);

	public int getSubBoardCount(String board_id, String etc1);

	public int getBoardCount(String string);

	public int delete(int seq);

	public List<FileSettingBean> getFileById(String regi_id);

	public List<BoardBean> getBoardFileById(String board_id, String regi_id);

	public int filenameCheck(String filename);

	public void insertComment(BoardCommentBean commentBean);

	public List<BoardCommentBean> getCommentList(int seq);

	public List<BoardBean> getBoardListById(PageBean pageBean);

	public int getBoardCountById(PageBean pageBean);

	public int contentUpdate(BoardBean bb);

	public List<BoardBean> getMainList(Map<String, Object> map);

	public List<FileSettingBean> getFileBySeq(int board_seq);

	public int imageDeleteFromFS(int seq);
	
	public int imageDeleteFromBoard(int seq);

	public List<BoardBean> getBoardFileByOrder(String string);

//	public List<BoardBean> getBoardList();
	
	
}
