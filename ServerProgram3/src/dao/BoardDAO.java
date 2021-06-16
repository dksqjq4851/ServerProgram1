package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import db.util.DBConnector;
import dto.Board;

public class BoardDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private static DataSource dataSource;
	static {
		try{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO(){}
	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con!= null) {con.close();
			if(ps!= null) {ps.close();}
			if(rs!= null) {rs.close();}
			}
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	
	public List<Board> boardList(){
		List<Board> list = new ArrayList<Board>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, AUTHOR, TITLE, HIT, POSTDATE FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setNo(rs.getLong(1));
				board.setAuthor(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setHit(rs.getLong(4));
				board.setPostdate(rs.getDate(5));
				list.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	public int getTotal() {
		int count = 0;
		try {
			con = dataSource.getConnection();
			sql = "SELECT COUNT(*) FROM BOARD";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			close(con, ps, rs);
		}
		return count;
	}
	public int insertBoard(Board board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getAuthor());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContent());
			ps.setString(4, board.getIp());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			close(con, ps, rs);
		}
		return result;
	}
	public Board selectBoard(long no) {
		Board board = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, AUTHOR, POSTDATE, IP, HIT, TITLE, CONTENT"+
				  "  FROM BOARD" +
				  " WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new Board();
				board.setNo(rs.getLong(1));
				board.setAuthor(rs.getString(2));
				board.setPostdate(rs.getDate(3));
				board.setIp(rs.getString(4));
				board.setHit(rs.getLong(5));
				board.setTitle(rs.getString(6));
				board.setContent(rs.getString(7));
			}
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			close(con, ps, rs);
		}
		return board;
	}
	public void updateHit(long no) {
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			close(con, ps, null);
		}
	}
	
	

}
