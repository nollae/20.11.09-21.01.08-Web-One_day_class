package com.one_day_class.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.one_day_class.vo.sh_TutorVO;

public class sh_TutorDAO extends DBConn {
	
	/* ��ü �� ���� */
	public int getListCount() {
		int result = 0;
		try {
			//4. SQL ���� - ResultSet ��ü ����
			String sql = "select count(*) from one_tutor";
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
	public ArrayList<sh_TutorVO> getTutorList() {
		ArrayList<sh_TutorVO> list = new ArrayList<sh_TutorVO>();
		
		try {
			String sql = " select rownum rno, name, gender, email, phone, age, area, hope_class, to_char(rdate, 'yyyy.mm.dd') rdate "
					+ " from (select * from one_tutor order by rdate) order by rno desc";
			getPreparedStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_TutorVO vo = new sh_TutorVO();
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
	
	/* select : ���� ����Ʈ  */
	public ArrayList<sh_TutorVO> getTutorList(int start, int end) {
		ArrayList<sh_TutorVO> list = new ArrayList<sh_TutorVO>();
		
		try {
			String sql = " select * from (select rownum rno, name, gender, email, phone, age, area, hope_class, to_char(rdate, 'yyyy.mm.dd') rdate "
					+ " from (select * from one_tutor order by rdate) order by rno)"
					+ " where rno between ? and ?";
			getPreparedStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_TutorVO vo = new sh_TutorVO();
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
	
	/* content : ȸ�� �� ���� */
	public sh_TutorVO getTutorContent(String email) {
		sh_TutorVO vo = new sh_TutorVO();
		
		try {
			String sql = "select email, name, to_char(rdate, 'yyyy.mm.dd') rdate, phone, age, area, profile_img, sprofile_img, pr  "
					+ "	from one_tutor where email=?";
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
	
	/* content : ���� ������������ ȸ�� �̸�, �����ʻ��� ��� */
	public sh_TutorVO getTutorInfo(String cid) {
		sh_TutorVO vo = new sh_TutorVO();
		
		try {
			String sql = "select name, sprofile_img from one_tutor "
					+ " where email=(select email from one_class where cid=?)";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setName(rs.getString(1));
				vo.setSprofile_img(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	/* mypage Ʃ�� ���� ���� */
	public boolean getUpdate(sh_TutorVO vo) {
		boolean result = false;
		
		try {
			String sql = "update one_tutor "
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
	
	/* mypage Ʃ�� ���� ���� (���� ������)*/
	public boolean getUpdateNofile(sh_TutorVO vo) {
		boolean result = false;
		
		try {
			String sql = "update one_tutor "
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
	
	/* mypassedit Ʃ�� ��й�ȣ ����*/
	public boolean getPassUpdate(sh_TutorVO vo) {
		boolean result = false;
		
		try {
			String sql = "update one_tutor set password=? where email=?";
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
	
	/* myleave Ʃ�� ȸ�� Ż��*/
	public boolean getDelete(sh_TutorVO vo) {
		boolean result = false;
		
		try {
			String sql = "delete from one_tutor where email=?";
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
	
	/* myclassform Ʃ�� ���� ��� */
	public ArrayList<sh_TutorVO> getMyclassForm(String email) {
		ArrayList<sh_TutorVO> list = new ArrayList<sh_TutorVO>();
		
		try {
			String sql = "select r.name, sprofile_img, adate, title, aschedule "
						  + " from one_class c, one_tutor r, one_apply_class a "
						  + " where c.cid=a.cid and r.email=c.email and a.astatus=0 and a.email=?"
						  + " order by adate desc";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sh_TutorVO vo = new sh_TutorVO();
				vo.setName(rs.getString(1));
				vo.setSprofile_img(rs.getString(2));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
