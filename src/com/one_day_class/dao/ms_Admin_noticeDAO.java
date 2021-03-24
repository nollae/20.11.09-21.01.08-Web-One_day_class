package com.one_day_class.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.one_day_class.vo.ms_Admin_noticeVO;


public class ms_Admin_noticeDAO extends DBConn{
	
	
	
	
	
	/**
	 *	delete ���� 
	 */
	public boolean getDelete(String bid) {
		boolean result = false;
		try {
			String sql= "delete from one_board where bid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	/**
	 *  Select : ���������(������������ ������ ����)
	 */
	public ms_Admin_noticeVO getContent(String bid) {
		ms_Admin_noticeVO vo = new ms_Admin_noticeVO();
		try {
			String sql= " SELECT BID,BTITLE,BCONTENT,BCHARGE, TO_CHAR(BDATE,'YYYY.MM.DD'), BHITS, BFILE, BSFILE,BPART FROM ONE_BOARD WHERE BID=?";
			
			getPreparedStatement(sql);
			pstmt.setString(1, bid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setBid(rs.getString(1));
				vo.setBtitle(rs.getString(2));
				vo.setBcontent(rs.getString(3));
				vo.setBcharge(rs.getString(4));
				vo.setBdate(rs.getString(5));
				vo.setBhits(rs.getInt(6));
				vo.setBfile(rs.getString(7));
				vo.setBsfile(rs.getString(8));
				vo.setBpart(rs.getString(9));
				
			System.out.println(vo.getBpart());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	/**
	 *  Update : ���� ������Ʈ - ���ϼ��� X, �������� ����
	 */
	public boolean getUpdateNofile(ms_Admin_noticeVO vo) {
		boolean result= false;
		try {
			String sql ="update one_board set bpart=?, btitle=?, bcontent=? "
					+ "  where bid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getBpart());
			pstmt.setString(2, vo.getBtitle());
			pstmt.setString(3, vo.getBcontent());
			pstmt.setString(4, vo.getBid());
			
			int val = pstmt.executeUpdate();
			if(val != 0) result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	/**
	 *  Update : �������� �������
	 */
	public boolean getUpdate(ms_Admin_noticeVO vo) {
		boolean result= false;
		System.out.println("bpart :");
		System.out.println(vo.getBpart());
		try {
			String sql = "update one_board set bpart=?, btitle=?, bcontent=?, bfile=?, bsfile=? where bid=?";
					
			
			getPreparedStatement(sql);
			
			pstmt.setString(1, vo.getBpart());
			pstmt.setString(2, vo.getBtitle());
			pstmt.setString(3, vo.getBcontent());
			pstmt.setString(4, vo.getBfile());
			pstmt.setString(5, vo.getBsfile());
			pstmt.setString(6, vo.getBid());

			int val = pstmt.executeUpdate();
			if(val !=0) result = true;
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	
	/**
	 * Select : ��ü ����Ʈ ���
	 */
	public ArrayList<ms_Admin_noticeVO> getList(){
		ArrayList<ms_Admin_noticeVO> list = new ArrayList<ms_Admin_noticeVO>();
		try {
			String sql= " SELECT ROWNUM RNO, BID, BTITLE,BCONTENT,BCHARGE,BHITS, TO_CHAR(BDATE,'YYYY.MM.DD')"
					+ " FROM (SELECT * FROM ONE_BOARD ORDER BY BDATE DESC) ";
			
			getPreparedStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ms_Admin_noticeVO vo = new ms_Admin_noticeVO();
				vo.setRno(rs.getInt(1));
				vo.setBid(rs.getString(2));
				vo.setBtitle(rs.getString(3));
				vo.setBcontent(rs.getString(4));
				vo.setBcharge(rs.getString(5));
				vo.setBhits(rs.getInt(6));
				vo.setBdate(rs.getString(7));
				
				list.add(vo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
}
