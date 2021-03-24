package com.one_day_class.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.one_day_class.vo.sh_ApplyClassVO;

public class sh_ApplyClassDAO extends DBConn {
	
	/* delete : ��û�� ��� */
	public boolean cacelClass(String email, String cid) {
		boolean result=false;
		try {
			String sql="delete from one_apply_class where astatus=0 and email=? and cid=?  ";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, cid);
			
			int val=pstmt.executeUpdate();
			if(val!=0) result=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	/* insert : ȸ������ */
	public boolean getInsert(sh_ApplyClassVO vo) {
		boolean result = false;
		
		try {
			String sql = "insert into one_apply_class values('A_'|| seq_one_apply_class.nextval,?,?,?,?,0, sysdate)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getCid());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getAschedule());
			pstmt.setInt(4, vo.getAperson());
			
			int val = pstmt.executeUpdate();
			if(val != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* myclassform ��û ���� ��� */
	public ArrayList<sh_ApplyClassVO> getMyclassForm(String email) {
		ArrayList<sh_ApplyClassVO> list = new ArrayList<sh_ApplyClassVO>();
		
		try {
			String sql = "select r.name, sprofile_img, adate, aschedule, title,c.cid"
						  + " from one_class c, one_tutor r, one_apply_class a "
						  + " where c.cid=a.cid and r.email=c.email and a.astatus=0 and a.email=?"
						  + " order by adate desc";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_ApplyClassVO vo = new sh_ApplyClassVO();
				vo.setAdate(rs.getString(3));
				vo.setAschedule(rs.getString(4));
				vo.setCid(rs.getString(6));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* myclasslist ��û ���� ��� */
	public ArrayList<sh_ApplyClassVO> getMyclassList(String email) {
		ArrayList<sh_ApplyClassVO> list = new ArrayList<sh_ApplyClassVO>();
		
		try {
			String sql = "select c.cid, spicture, title, adate, aschedule"
						  + " from one_class c, one_tutor r, one_apply_class a "
						  + " where c.cid=a.cid and r.email=c.email and a.astatus=1 and a.email=?"
						  + " order by adate desc";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_ApplyClassVO vo = new sh_ApplyClassVO();
				vo.setAdate(rs.getString(4));
				vo.setAschedule(rs.getString(5));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* ����apply������ ��� �ִ��� Ȯ�� */
	public int getApplyCheck(String cid, String email) {
		int cnt = 0;
		
		try {
			String sql = "select count(*) cnt from one_apply_class where cid=? and email=? and astatus=0";
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
