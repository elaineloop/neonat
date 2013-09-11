package info.shuige.neonat.persistence.service;

import info.shuige.neonat.entity.Board;
/**
 * 对象序列化来实现持久话服务
 * @author shuige
 * 2013年9月11日
 */
public interface PersistenceService {

	public void load(Board board);
	public void save(Board board);
}
