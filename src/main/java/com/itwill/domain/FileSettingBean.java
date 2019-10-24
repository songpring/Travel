package com.itwill.domain; 

import java.security.Timestamp;

public class FileSettingBean {
	private int seq;
	private int board_seq;
	private String path;
	private String file_name;
	private String ext;
	private long size;
	private String regi_id;
	private Timestamp regi_date;
	private String regi_ip_addr;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getRegi_id() {
		return regi_id;
	}
	public void setRegi_id(String regi_id) {
		this.regi_id = regi_id;
	}
	public Timestamp getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(Timestamp regi_date) {
		this.regi_date = regi_date;
	}
	public String getRegi_ip_addr() {
		return regi_ip_addr;
	}
	public void setRegi_ip_addr(String regi_ip_addr) {
		this.regi_ip_addr = regi_ip_addr;
	}
	
	
	
}
