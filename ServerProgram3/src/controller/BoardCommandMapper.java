package controller;

import command.BoardCommand;
import command.InsertBoardCommand;
import command.InsertBoardPageCommand;
import command.ListBoardCommand;
import command.SelectBoardByNoCommand;

public class BoardCommandMapper {
	private static BoardCommandMapper instance = new BoardCommandMapper();
	private BoardCommandMapper() {}
	public static BoardCommandMapper getInstance() {
		if(instance == null) {
			instance = new BoardCommandMapper();
		}
		return instance;
	}
	
	public BoardCommand getCommand(String cmd) {
		BoardCommand command = null;
		switch(cmd) {
		case "listBoard.do" :
			command = new ListBoardCommand();
			break;
		case "insertBoardPage.do" :
			command = new InsertBoardPageCommand();
			break;
		case "insertBoard.do" :
			command = new InsertBoardCommand();
			break;
		case "selectBoardByNo.do" :
			command = new SelectBoardByNoCommand();
			break;
		}

		return command;
	}

}
