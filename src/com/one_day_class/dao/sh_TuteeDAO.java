package com.one_day_class.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.one_day_class.vo.sh_TuteeVO;

public class sh_TuteeDAO extends DBConn {
	
	/* ��ü �� ���� */
	public int getListCount() {
		int result = 0;
		try {
			//4. SQL ���� - ResultSet ��ü ����
			String sql = "select count(*) from one_tutee";
			getPreparedStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			//5. ResultSet ��ü �����Ǵ� ��� - ������ ��������
			while(rs.next()){
				result = rs.getInt(1);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* select : ���� ����Ʈ  */
	public ArrayList<sh_TuteeVO> getTuteeList() {
		ArrayList<sh_TuteeVO> list_tutee = new ArrayList<sh_TuteeVO>();
		
		try {
			String sql = " select rownum rno, name, gender, email, phone, age, area, hope_class, to_char(rdate, 'yyyy.mm.dd') rdate "
					+ " from (select * from one_tutee order by rdate) order by rno desc";
			getPreparedStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_TuteeVO vo = new sh_TuteeVO();
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setGender(rs.getString(3));
				vo.setEmail(rs.getString(4));
				vo.setPhone(rs.getString(5));
				vo.setAge(rs.getString(6));
				vo.setArea(rs.getString(7));
				vo.setHope_class(rs.getString(8));
				vo.setRdate(rs.getString(9));
				
				list_tutee.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list_tutee;
	}
	
	/* select : ���� ����Ʈ  */
	public ArrayList<sh_TuteeVO> getTuteeList(int start, int end) {
		ArrayList<sh_TuteeVO> list = new ArrayList<sh_TuteeVO>();
		
		try {
			String sql = " select * from (select rownum rno, name, gender, email, phone, age, area, hope_class, to_char(rdate, 'yyyy.mm.dd') rdate "
					+ " from (select * from one_tutee order by rdate) order by rno)"
					+ " where rno between ? and ?";
			getPreparedStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_TuteeVO vo = new sh_TuteeVO();
				vo.setRno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setGender(rs.getString(3));
				vo.setEmail(rs.getString(4));
				vo.setPhone(rs.getString(5));
				vo.setAge(rs.getString(6));
				vo.setArea(rs.getString(7));
				vo.setHope_class(rs.getString(8));
				vo.setRdate(rs.getString(9));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* content : ȸ�� ���� ���� */
	public sh_TuteeVO getTuteeContent(String email) {
		sh_TuteeVO vo = new sh_TuteeVO();
		
		try {
			String sql = "select email, name, to_char(rdate, 'yyyy.mm.dd') rdate, phone, age, area, profile_img, sprofile_img, pr "
					+ " from one_tutee where email=?";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setEmail(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setRdate(rs.getString(3));
				vo.setPhone(rs.getString(4));
				vo.setAge(rs.getString(5));
				vo.setArea(rs.getString(6));
				vo.setProfile_img(rs.getString(7));
				vo.setSprofile_img(rs.getString(8));
				vo.setPr(rs.getString(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/* ���� �������� ���� ����(�̸�, �����ʻ���)*/
	public ArrayList<sh_TuteeVO> getReviewContent(String cid) {
		ArrayList<sh_TuteeVO> list = new ArrayList<sh_TuteeVO>();
		
		try {
			String sql = "select name, sprofile_img, rdate, rcontent, cid "
					+ " from (select e.name, e.sprofile_img, r.rdate, r.rcontent,r.cid from one_tutee e, one_review r where e.email = r.email order by rdate desc) "
					+ " where cid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_TuteeVO vo = new sh_TuteeVO();
				vo.setName(rs.getString(1));
				vo.setSprofile_img(rs.getString(2));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* ���� �������� ���� ����(�̸�, �����ʻ���)*/
	public ArrayList<sh_TuteeVO> getReviewContent(String cid, int start, int end) {
		ArrayList<sh_TuteeVO> list = new ArrayList<sh_TuteeVO>();
		
		try {
			String sql = "select * from (select rownum rno, name, sprofile_img, rdate, rcontent,cid "
					+ " from (select e.name, e.sprofile_img, r.rdate, r.rcontent, r.cid from one_tutee e, one_review r where e.email = r.email order by rdate desc) "
					+ " where cid=?) where rno between ? and ?";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_TuteeVO vo = new sh_TuteeVO();
				vo.setName(rs.getString(2));
				vo.setSprofile_img(rs.getString(3));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/* mypage ƩƼ ���� ���� */
	public boolean getUpdate(sh_TuteeVO vo) {
		boolean result = false;
		
		try {
			String sql = "update one_tutee "
					+ " set phone=?, name=?, age=?, area=?, pr=?, profile_img=?, sprofile_img=? "
					+ " where email=?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getPhone());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getAge());
			pstmt.setString(4, vo.getArea());
			pstmt.setString(5, vo.getPr());
			pstmt.setString(6, vo.getProfile_img());
			pstmt.setString(7, vo.getSprofile_img());
			pstmt.setString(8, vo.getEmail());
			
			int val = pstmt.executeUpdate();
			if(val != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* mypage ƩƼ ���� ���� (���� ������)*/
	public boolean getUpdateNofile(sh_TuteeVO vo) {
		boolean result = false;
		
		try {
			String sql = "update one_tutee "
					+ " set phone=?, name=?, age=?, area=?, pr=? "
					+ " where email=?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getPhone());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getAge());
			pstmt.setString(4, vo.getArea());
			pstmt.setString(5, vo.getPr());
			pstmt.setString(6, vo.getEmail());
			
			int val = pstmt.executeUpdate();
			if(val != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* mypassedit ƩƼ ��й�ȣ ����*/
	public boolean getPassUpdate(sh_TuteeVO vo) {
		boolean result = false;
		
		try {
			String sql = "update one_tutee set password=? where email=?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getEmail());
			
			int val = pstmt.executeUpdate();
			if(val != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* myleave ƩƼ ȸ�� Ż��*/
	public boolean getDelete(sh_TuteeVO vo) {
		boolean result = false;
		
		try {
			String sql = "delete from one_tutee where email=?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getEmail());
			
			int val = pstmt.executeUpdate();
			if(val != 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* myclasslist ���� �� ������ ƩƼ���� */
	public sh_TuteeVO getMyclassList(String email) {
		sh_TuteeVO vo = new sh_TuteeVO();
		
		try {
			String sql = "select r.rid, r.cid, r.rservice, r.rcontent, to_char(r.rdate, 'yyyy.mm.dd') rdate, e.sprofile_img, e.name "
					+ " from one_review r, one_tutee e where r.email=e.email and r.email=?";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setSprofile_img(rs.getString(6));
				vo.setName(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
}
