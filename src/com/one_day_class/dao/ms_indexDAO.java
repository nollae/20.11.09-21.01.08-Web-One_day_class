package com.one_day_class.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.one_day_class.vo.ms_indexVO;

public class ms_indexDAO extends DBConn {

	
	
	
	
	
	
	
	/**
	 * ���ø���Ʈ���帹�� ����
	 */
	public ArrayList<ms_indexVO> getWishList(){
		ArrayList<ms_indexVO> list1 = new ArrayList<ms_indexVO>();
		try {
			String sql= " SELECT * FROM (SELECT ROWNUM RNO,PICTURE,TITLE,PROFILE_IMG,NAME,EMAIL,REGIONMAIN,SCHEDULE,WISH_CNT,CSTATUS,CDATE FROM(SELECT PICTURE,TITLE,PROFILE_IMG,NAME,OTR.EMAIL,REGIONMAIN,SCHEDULE,WISH_CNT,CSTATUS,CDATE FROM ONE_TUTOR OTR,ONE_CLASS OCL WHERE CSTATUS=1 AND OTR.EMAIL=OCL.EMAIL ORDER BY WISH_CNT DESC))";
			getPreparedStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ms_indexVO vo = new ms_indexVO();
				vo.setRno(rs.getInt(1));
				vo.setPicture(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setProfile_img(rs.getString(4));
				vo.setName(rs.getString(5));
				vo.setEmail(rs.getString(6));
				vo.setRegionmain(rs.getString(7));
				vo.setSchedule(rs.getString(8));
				vo.setWish_cnt(rs.getInt(9));
				vo.setCstatus(rs.getInt(10));
				vo.setCdate(rs.getString(11));
				
				list1.add(vo);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list1;
	}
	
	
	
	/**
	 * ���������������Ʈ
	 */
	public ArrayList<ms_indexVO> getFastList(){
		ArrayList<ms_indexVO> list = new ArrayList<ms_indexVO>();
		try {
			String sql= " SELECT * FROM (SELECT ROWNUM RNO,PICTURE,TITLE,PROFILE_IMG,NAME,EMAIL,REGIONMAIN,SCHEDULE,WISH_CNT,CSTATUS,CDATE FROM(SELECT PICTURE,TITLE,PROFILE_IMG,NAME,OTR.EMAIL,REGIONMAIN,SCHEDULE,WISH_CNT,CSTATUS,CDATE FROM ONE_TUTOR OTR,ONE_CLASS OCL WHERE CSTATUS=1 AND OTR.EMAIL=OCL.EMAIL ORDER BY CDATE DESC))";
			getPreparedStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ms_indexVO vo = new ms_indexVO();
				vo.setRno(rs.getInt(1));
				vo.setPicture(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setProfile_img(rs.getString(4));
				vo.setName(rs.getString(5));
				vo.setEmail(rs.getString(6));
				vo.setRegionmain(rs.getString(7));
				vo.setSchedule(rs.getString(8));
				vo.setWish_cnt(rs.getInt(9));
				vo.setCstatus(rs.getInt(10));
				vo.setCdate(rs.getString(11));
				
				list.add(vo);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
