package com.itwill.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.BoardBean;
import com.itwill.domain.BoardCommentBean;
import com.itwill.domain.FileSettingBean;
import com.itwill.domain.JsonDataBean;
import com.itwill.domain.PageBean;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.itwill.mapper.BoardMapper";

	@Override
	public Integer maxNum() {
		int a = 1;
		
		try {
			a = sqlSession.selectOne(namespace+".maxNum");
		} catch (Exception e) {
			System.out.println("에러 : "  + e.getMessage());
		}
		
		// 글이 없으면 1리턴, 아니면 가장 큰 seq 리턴
		return a;
	}

	@Override
	public int write(BoardBean bean) {
		int result = sqlSession.insert(namespace+".write", bean);
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!! DAO write: " + result);
		return result;
	}

//	@Override
//	public List<BoardBean> getBoardList() {
//		return sqlSession.selectList(namespace+".getBoardListAll");
//	}

	@Override
	public BoardBean getArticle(int seq) {
		return sqlSession.selectOne(namespace+".getArticle", seq);
	}

	@Override
	public void fileUpload(FileSettingBean fs) {
		
		sqlSession.insert(namespace+".fileUpload", fs);
		
	}

	@Override
	public int getSeq() {
		int result = sqlSession.selectOne(namespace+".getSeq");
		return result;
	}

	@Override
	public List<FileSettingBean> getFile() {
		
		return sqlSession.selectList(namespace+".getFile");
	}

	@Override
	public List<BoardBean> getBoardFile(String board_id) {
		
		return sqlSession.selectList(namespace+".getBoardFile",board_id);
	}

	@Override
	public List<BoardBean> getBoardList(PageBean pageBean) {
		List<BoardBean> list = sqlSession.selectList(namespace+".getBoardList", pageBean);
		
//		System.out.println(list);
		
		return list;
	}

	@Override
	public Integer getBoardCount(PageBean pageBean) {
		return sqlSession.selectOne(namespace+".getBoardCount", pageBean);
	}

	@Override
	public void updateReadcount(int seq) {
		sqlSession.update(namespace+".updateReadcount",seq);
		
	}

	@Override
	public List<BoardBean> getSubBoard(PageBean pageBean) {
		
		return sqlSession.selectList(namespace+".getSubBoard", pageBean);
	}

	@Override
	public int getSubBoardCount(String board_id, String etc1) {
		BoardBean bb = new BoardBean();
		bb.setBoard_id(board_id);
		bb.setEtc1(etc1);
		return sqlSession.selectOne(namespace+".getSubBoardCount",bb);
	}

	@Override
	public int getBoardCount(String board_id) {
		return sqlSession.selectOne(namespace+".getBoardCountAll", board_id);
	}

	@Override
	public int delete(int seq) {
		return sqlSession.delete(namespace+".delete", seq);
	}

	@Override
	public String getGalleryRecommendCount(Map<String, Object> map) {
		// 추천수 개수
		map.put("recommend", sqlSession.selectOne(namespace+".recommendCount", map));
		// 추천 눌렀는지 검사
		int isRecommend = sqlSession.selectOne(namespace+".isRecommend", map);
		
		if(isRecommend > 0) {// 추천 누른 상태면  못누름
			// 추천 기록 삭제
			sqlSession.delete(namespace+".recommendDown", map);
			// 추천 -1
			map.put("recommend", (int) map.get("recommend") - 1);
			// 추천개수 update 하기
			sqlSession.update(namespace+".updateRecommend",map);
			map.put("msg", "추천을 누른 상태입니다. 추천을 취소합니다.");
		
		} else { // 추천 안누른 상태
			 // 추천 기록 추가
			sqlSession.insert(namespace+".recommendUp", map);
			// 추천 +1 
			map.put("recommend", (int) map.get("recommend") + 1);
			//추천개수 update 하기
			sqlSession.update(namespace+".updateRecommend",map);
			map.put("msg", "추천하셨습니다.");
		}
		String recommend = map.get("recommend").toString();
		System.out.println("recommend : " + recommend);
		
		return recommend;
	}
	
	@Override
	public String getRecommendCountUp(Map<String, Object> map) {
//		List<Object> list = new ArrayList<Object>();
		
		map.put("recommend", sqlSession.selectOne(namespace+".recommendCount", map));
		int isRecommend = sqlSession.selectOne(namespace+".isRecommend", map);
		
		System.out.println("isRecommend : " + isRecommend);
		
		if(isRecommend == 0) {// 추천 안했다는 뜻
			// recommend table 에 insert
			sqlSession.insert(namespace+".recommendUp", map);
			// 추천수 +1 
			map.put("recommend", (int) map.get("recommend") + 1);

			// 게시글에 추천수 update 하기
			sqlSession.update(namespace+".updateRecommend",map);
		} 
		
		String recommend = map.get("recommend").toString();
		System.out.println("recommend : " + recommend);

		return recommend;
	}

	@Override
	public String getRecommendCountDown(Map<String, Object> map) {
//		List<Object> list = new ArrayList<Object>();
		
		map.put("recommend", sqlSession.selectOne(namespace+".recommendCount", map));
		int isRecommend = sqlSession.selectOne(namespace+".isRecommend", map);
		
		System.out.println("isRecommend : " + isRecommend);
		
		if(isRecommend > 0) {// 이미 추천 했다는 뜻
			// recommend table 에 delete
			sqlSession.delete(namespace+".recommendDown", map);
			// 추천수 -1
			map.put("recommend", (int) map.get("recommend") - 1);
			
			// 게시글에 추천수 update 하기
			sqlSession.update(namespace+".updateRecommend",map);
		}
		
		String recommend = map.get("recommend").toString();
		System.out.println("recommend : " + recommend);

		return recommend;
	}
	
	@Override
	public List<Object> getHotList(Map<String, Object> map) {
		List<Object> list = new ArrayList<Object>();
		list = (List)sqlSession.selectList(namespace+".getHotList",map);
		
		// 날짜 변경 - 진짜 열받는다
		for(Object bb : list) {
	    	int yyyy = ((BoardBean) bb).getRegi_date().getYear() + 1900;
	    	int MM = ((BoardBean) bb).getRegi_date().getMonth() + 1;
	    	int dd = ((BoardBean) bb).getRegi_date().getDate();
	    	
			String date = yyyy + " - " + MM + " - " + dd;
			((BoardBean) bb).setEtc3(date);
	    }
		
//		System.out.println("list : " + list);

		return list;
	}
	@Override
	public List<Object> getNoticeList(Map<String, Object> map) {
		List<Object> list = new ArrayList<Object>();
		list = (List)sqlSession.selectList(namespace+".getNoticeList",map);
		
		// 날짜 변경 - 진짜 열받는다
		for(Object bb : list) {
	    	int yyyy = ((BoardBean) bb).getRegi_date().getYear() + 1900;
	    	int MM = ((BoardBean) bb).getRegi_date().getMonth() + 1;
	    	int dd = ((BoardBean) bb).getRegi_date().getDate();
	    	
			String date = yyyy + " - " + MM + " - " + dd;
			((BoardBean) bb).setEtc3(date);
	    }
		
//		System.out.println("list : " + list);

		return list;
	}

	@Override
	public List<FileSettingBean> getFileById(String regi_id) {
		return sqlSession.selectList(namespace+".getFileById");
	}

	@Override
	public List<BoardBean> getBoardFileById(String board_id, String regi_id) {
		BoardBean bb = new BoardBean();
		bb.setBoard_id(board_id);
		bb.setRegi_id(regi_id);
		return sqlSession.selectList(namespace+".getBoardFileById", bb);
	}

	@Override
	public int filenameCheck(String filename) {
		return sqlSession.selectOne(namespace+".filenameCheck", filename);
	}

	@Override
	public void insertComment(BoardCommentBean commentBean) {
		sqlSession.insert(namespace+".insertComment",commentBean);
		
	}

	@Override
	public List<BoardCommentBean> getCommentList(int seq) {
		return sqlSession.selectList(namespace+".getCommentList", seq);
	}

	@Override
	public List<BoardBean> getBoardListById(PageBean pageBean) {
		return sqlSession.selectList(namespace+".getBoardListById", pageBean);
	}

	@Override
	public String comDelete(Map<String, Object> map) {
		int isDelete = sqlSession.delete(namespace+".comDelete", map);
		
		return isDelete + "";
	}

	@Override
	public String getLike(Map<String, Object> map) {
		// 좋아요 개수
		map.put("like", sqlSession.selectOne(namespace+".likeCount", map));
		// 싫어요 눌렀는지 검사
		int isHate = sqlSession.selectOne(namespace+".isHate", map);
		
		if(isHate > 0) {// 싫어요 누른 상태면 좋아요 못누름
		map.put("msg", "싫어요를 누른 상태입니다.");
		} else { // 싫어요 안누른 상태
			int isLike = sqlSession.selectOne(namespace+".isLike", map);
			
			if(isLike > 0) { // 좋아요 누른 상태면 좋아요 취소
				sqlSession.delete(namespace+".likeDown", map);
				// 좋아요 -1
				map.put("like", (int) map.get("like") - 1);
				// 댓글에 좋아요 update 하기
				sqlSession.update(namespace+".updateLike",map);
				map.put("msg", "좋아요를 취소합니다.");
			} else { // 좋아요 안눌렀으면 좋아요
				sqlSession.insert(namespace+".likeUp", map);
				// 좋아요 +1 
				map.put("like", (int) map.get("like") + 1);
				// 댓글에 좋아요 update 하기
				sqlSession.update(namespace+".updateLike",map);
				map.put("msg", "좋아요를 누르셨습니다.");
			}
			
		}
		String like = map.get("like").toString();
		System.out.println("like : " + like);
		
		return like;
	}

	@Override
	public String getHate(Map<String, Object> map) {
		// 싫어요 개수
		map.put("hate", sqlSession.selectOne(namespace+".hateCount", map));
		// 좋아요 눌렀는지 검사
		int isLike = sqlSession.selectOne(namespace+".isLike", map);
		
		if(isLike > 0) {// 좋아요 누른 상태면 좋아요 못누름
			map.put("msg", "좋아요를 누른 상태입니다.");
		} else { // 좋아요 안누른 상태
			int isHate = sqlSession.selectOne(namespace+".isHate", map);
			
			if(isHate > 0) { // 싫어요 누른 상태면 싫어요 취소
				sqlSession.delete(namespace+".hateDown", map);
				// 싫어요 -1
				map.put("hate", (int) map.get("hate") - 1);
				// 댓글에 싫어요 update 하기
				sqlSession.update(namespace+".updateHate",map);
				map.put("msg", "싫어요를 누르셨습니다.");
			} else { // 싫어요 안눌렀으면 싫어요
				sqlSession.insert(namespace+".hateUp", map);
				// 싫어요 +1 
				map.put("hate", (int) map.get("hate") + 1);
				// 댓글에 싫어요 update 하기
				sqlSession.update(namespace+".updateHate",map);
				map.put("msg", "싫어요를 누르셨습니다.");
			}
			
		}
		String hate = map.get("hate").toString();
		System.out.println("hate : " + hate);
		
		return hate;
		
	}
	@Override
	public int getBoardCountById(PageBean pageBean) {
		return sqlSession.selectOne(namespace+".getBoardCountById", pageBean);
	}

	// 글수정
	@Override
	public int contentUpdate(BoardBean bb) {
		return sqlSession.update(namespace+".contentUpdate", bb);
	}

	@Override
	public List<BoardBean> getMainList(Map<String, Object> map) {
		
		
		List<BoardBean> returnList = sqlSession.selectList(namespace+".getMainList", map);
		
		return returnList;
	}

	@Override
	public List<FileSettingBean> getFileBySeq(int board_seq) {
		return sqlSession.selectList(namespace+".getFileBySeq", board_seq);
	}
	
	@Override
	public int imageDeleteFromFS(int seq) {
		return sqlSession.delete(namespace+".imageDeleteFromFS", seq);
	}
	
	@Override
	public int imageDeleteFromBoard(int seq) {
		return sqlSession.delete(namespace+".imageDeleteFromBoard", seq);
	}

	@Override
	public List<BoardBean> getBoardFileByOrder(String board_id) {
		return sqlSession.selectList(namespace+".getBoardFileByOrder", board_id);
	}
	
	
	
}
