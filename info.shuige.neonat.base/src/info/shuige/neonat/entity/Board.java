package info.shuige.neonat.entity;

import info.shuige.neonat.base.Activator;
import info.shuige.neonat.persistence.service.PersistenceService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author shuige
 * 2013年9月11日
 */
public class Board implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 论坛版面名称
	 */
	private String name;
	/**
	 * 论坛帖子列表
	 */
	private List<Topic> topics;
	private Map<Integer,Topic> topicMap;
	
	
	public Board(String name){
		this.name = name;
		this.topics = new ArrayList<Topic>();
		this.topicMap = new HashMap<Integer, Topic>();
	}
	/**
	 * 
	 * @param replyTopicID
	 * @param title
	 * @param content
	 */
	public void addTopic(int replyTopicID,String title,String content){
		save();
	}
	/**
	 * 保存版面信息
	 */
	public void save(){
		getPersistenceService().save(this);
	}
	/**
	 * 加载版面信息
	 */
	public void load(){
		getPersistenceService().load(this);
	}
	/**
	 * 获取持久化服务
	 * @return
	 */
	private PersistenceService getPersistenceService(){
		BundleContext context = Activator.getContext();
		ServiceReference<PersistenceService> sr = context.getServiceReference(PersistenceService.class);
		if(null!=sr){
			throw new UnsupportedOperationException("当前osgi容器中没有提供neoat持久话服务，无法存取版面信息");
		}else{
			PersistenceService service = context.getService(sr);
			return service;
		}
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	public Map<Integer, Topic> getTopicMap() {
		return topicMap;
	}
	public void setTopicMap(Map<Integer, Topic> topicMap) {
		this.topicMap = topicMap;
	}
	public Object getTopicCount() {
		
		return this.topics.size();
	}
	
	
}
