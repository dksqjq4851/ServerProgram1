package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import db.util.DBConnector;
import dto.MemberDTO;

public class MemberDAO {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql = null;
	
	private static DataSource dataSource;
	
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {
		con = DBConnector.getInstance().getConnection();
	}
	public static MemberDAO getInstance() {
		if(dao == null) {
			dao = new MemberDAO();
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
	
	
	
	public MemberDTO login(MemberDTO dto) {
		MemberDTO loginDTO = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, ID, NAME, GRADE, POINT FROM MEMBER_TABLE WHERE ID = ? AND NAME = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getName());
			rs = ps.executeQuery();
			if(rs.next()) {
				loginDTO = new MemberDTO();
				loginDTO.setNo(rs.getString(1));
				loginDTO.setId(rs.getString(2));
				loginDTO.setName(rs.getString(3));
				loginDTO.setGrade(rs.getString(4));
				loginDTO.setPoint(rs.getString(5));
			}
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}finally {
			close(con,ps,rs);
		}
		return loginDTO;
	}

}
