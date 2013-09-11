package info.shuige.neonat.persistence.service.impl;

import info.shuige.neonat.entity.Board;
import info.shuige.neonat.persistence.serialize.Activator;
import info.shuige.neonat.persistence.service.PersistenceService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializePersistenceService implements PersistenceService{

	private File getDataFile(){
		return Activator.getContext().getDataFile("board.bin");
	}
	@Override
	public void load(Board board) {
		ObjectInputStream input;
		File datafile = getDataFile();
		try{
			if(!datafile.exists()){
				datafile.createNewFile();
			}
			input = new ObjectInputStream(new FileInputStream(datafile));
			Board _board = (Board) input.readObject();
			_board.setName(_board.getName());
			_board.setTopicMap(_board.getTopicMap());
			_board.setTopics(_board.getTopics());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void save(Board board) {
		ObjectOutputStream output;
		File datafile = getDataFile();
		try{
			if(!datafile.exists()){
				datafile.createNewFile();
			}
			output = new ObjectOutputStream(new FileOutputStream(datafile));
			output.writeObject(board);
			output.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
