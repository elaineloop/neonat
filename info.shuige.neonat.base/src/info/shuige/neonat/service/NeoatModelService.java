package info.shuige.neonat.service;

import info.shuige.neonat.entity.Board;
/**
 * 模型访问接口
 * @author shuige
 * 2013年9月11日
 */
public interface NeoatModelService {
	public Board getBoard();
	public void updateBoard(Board board);
}
