package info.shuige.neonat.service.impl;

import info.shuige.neonat.entity.Board;
import info.shuige.neonat.service.NeoatModelService;

/**模型访问接口实现
 * @author shuige
 * 2013年9月12日
 */
public class NeoatModelServiceImpl implements NeoatModelService{

	private volatile static Board board;
	@Override
	public Board getBoard() {
		if(null==board){
			synchronized (Board.class) {
				if(null==board){
					board = new Board("Neonat BBS");
					board.load();
				}
			}
		}
		return board;
	}

	@Override
	public void updateBoard(Board board) {
		if(null == board){
			throw new IllegalArgumentException("传入空的参数board");
		}
		board.save();
		NeoatModelServiceImpl.board = board;
		
	}

}
