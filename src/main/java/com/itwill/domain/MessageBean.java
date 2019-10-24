package com.itwill.domain;

import java.sql.Timestamp;

public class MessageBean {
	private int seq;
	private String message_id;
	private String send_id;
	private String receive_id;
	private String subject;
	private String content;
	private String message_type;
	private String read_use;
	private String regi_id;
	private Timestamp regi_date;
	private String regi_ip_addr;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public String getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getRead_use() {
		return read_use;
	}
	public void setRead_use(String read_use) {
		this.read_use = read_use;
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