package com.one_day_class.dao;

import java.sql.ResultSet;

public class sh_WishListDAO extends DBConn {
	/* mywishlist ������ ���ø���Ʈ ���� */
	public int getMywishListCnt(String cid) {
		int cnt = 0;
		
		try {
			String sql = "select count(*) cnt from one_wishlist where cid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/* ���� �������� ���ø���Ʈ �߰� */
	public int getInsert(String cid, String email) {
		int result = 0;
		
		try {
			String sql = "insert into one_wishlist "
					+ " values('W_'||seq_one_wishlist.nextval, ?,?,sysdate)";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setString(2, email);
			
			int val = pstmt.executeUpdate();
			if(val != 0) {
				result = 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* ���� �������� ���ø���Ʈ ���� */
	public int getDelete(String cid, String email) {
		int result = 0;
		
		try {
			String sql = "delete from one_wishlist where cid=? and email=?";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setString(2, email);
			
			int val = pstmt.executeUpdate();
			if(val != 0) {
				result = 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* ���� �������� ���ø���Ʈ ���� */
	public int getWishCheck(String cid, String email) {
		int cnt = 0;
		
		try {
			String sql = "select count(*) cnt from one_wishlist where cid=? and email=?";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setString(2, email);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
