package info.shuige.neonat.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BBS论坛帖子实体类
 * @author shuige
 * 2013年9月11日
 */
public class Topic implements Serializable{

	private int id;
	private String title;
	private String content;
	
	/**
	 * 发帖日期
	 */
	private Date date;
	/**
	 * 回帖内容
	 */
	private List<Topic> replyTopics;
	
	public Topic(int id,String title,String content){
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = new Date();
		this.replyTopics = new ArrayList<Topic>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Topic> getReplyTopics() {
		return replyTopics;
	}
	public void setReplyTopics(List<Topic> replyTopics) {
		this.replyTopics = replyTopics;
	}
	
	
}
