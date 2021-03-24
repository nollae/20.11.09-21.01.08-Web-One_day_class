package com.one_day_class.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.one_day_class.vo.ClassVO;
import com.one_day_class.vo.ms_TutorclassVO;


public class ClassDAO extends DBConn{
	/**
	 *  select : email�� �ް� �������� ����� Ȯ��
	 */
	/**
	 * ����������Ʈ  ���� ���ϱ�
	 */
	public  int getMyList(String email){
		int count=0;
		try {
			String sql="SELECT COUNT(*)  FROM (SELECT CID, EMAIL, TITLE,PICTURE,SPICTURE FROM (SELECT CID, OTR.EMAIL, TITLE,PICTURE,SPICTURE FROM ONE_CLASS ONEC, ONE_TUTOR OTR WHERE ONEC.EMAIL=OTR.EMAIL ORDER BY CID ASC)  WHERE EMAIL=?) ";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	/**
	 * index : �˻���
	 */
	public ArrayList<ClassVO> SearchList(String inp_sch ,int start, int end) {
		ArrayList<ClassVO> search_list = new ArrayList<ClassVO>();
		
		try {
			getStatement();
			String sql = "select * from"
					+ " (select cid, email, regionmain, catemain, catesub, price, picture, schedule, title , rownum rno,spicture, cstatus "
					+ " from(select cid, email, regionmain, catemain, catesub, price, picture, "
					+ " schedule, title , rownum rno,spicture, cstatus from one_class where   title "
					+ " like '%" +inp_sch+ "%' or catemain like '%" + inp_sch +"%' or catesub like '%" + inp_sch + "%') where cstatus=1) where rno between "+start+" and "+end+"  "; 
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ClassVO vo = new ClassVO();
				vo.setCid(rs.getString(1));
				vo.setEmail(rs.getString(2));
				vo.setRegionmain(rs.getString(3));
				vo.setCatemain(rs.getString(4));
				vo.setCatesub(rs.getString(5));
				vo.setPrice(rs.getInt(6));
				vo.setPicture(rs.getString(7));
				vo.setSchedule(rs.getString(8));
				vo.setTitle(rs.getString(9));
				vo.setSpicture(rs.getString(11));
				
				
				search_list.add(vo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return search_list;
	}
	
	/**
	 * index : ���帹�� ���� ��
	 */
	public ArrayList<ClassVO> indexWishlist (String email) {
		ArrayList<ClassVO> list5 = new ArrayList<ClassVO>();
		
		try {
			String sql = "select cno, a.cid, email, picture, spicture, title, schedule, regionmain, wish_chk, cnt from " + 
					"(select rownum cno, cid, email, picture, spicture, title, schedule, regionmain, cnt " + 
					"from(select  a.cid, email, picture, spicture, title, schedule, regionmain, cnt " + 
					"from one_class a, (select count(*) cnt, cid from one_wishlist group by cid) b " + 
					"where a.cid = b.cid " + 
					"order by cnt desc) where rownum between 1 and 6) a, (select distinct cid, cid wish_chk from one_wishlist where email=?) b " + 
					"where a.cid = b.cid(+) order by cno asc";
			getPreparedStatement(sql); 
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setPicture(rs.getString(4));
				vo.setSpicture(rs.getString(5));
				vo.setTitle(rs.getString(6));
				vo.setSchedule(rs.getString(7));
				vo.setRegionmain(rs.getString(8));
				vo.setWish_chk(rs.getString(9));
				vo.setCnt(rs.getInt(10));
				
				list5.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list5;
	}
	
	/**
	 * index : �ֱ� ��� �� // Ȯ�οϷ�
	 */
	public ArrayList<ClassVO> indexRecent(String email) {
		ArrayList<ClassVO> list4 = new ArrayList<ClassVO>();
		
		try {
			String sql = "select cno, a.cid, email, picture, spicture, title, schedule, regionmain, wish_chk, cstatus from " + 
					"(select rownum cno, cid, email, picture, spicture, title, schedule, regionmain, cstatus " + 
					"from(select cid, email, picture, spicture, title, schedule, regionmain, cstatus " + 
					"from one_class where cstatus = 1 " + 
					"order by cdate desc) " + 
					"where rownum between 1 and 6) a, (select cid, cid wish_chk from one_wishlist where email=?) b " + 
					"where a.cid = b.cid(+)  "
					+ "order by cid desc";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setPicture(rs.getString(4));
				vo.setSpicture(rs.getString(5));
				vo.setTitle(rs.getString(6));
				vo.setSchedule(rs.getString(7));
				vo.setRegionmain(rs.getString(8));
				vo.setWish_chk(rs.getString(9));
				vo.setCstatus(rs.getInt(10));
				
				list4.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list4;
	}
	/**
	 * index : MD ��õ Ŭ����
	 */
	public ArrayList<ClassVO> indexRecommend(String email) {
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		
		try {
			String sql = "select cno, a.cid, email, spicture, title, schedule, regionmain, wish_chk from " + 
					"(select rownum cno, cid, email, picture, spicture, title, schedule, regionmain, cdate, cstatus " + 
					"from(select cid, email, picture, spicture, title, schedule, regionmain, cdate, cstatus from one_class " + 
					"where videos IS NOT NULL order by cdate desc) where rownum between 1 and 6) a, "
					+ "(select cid, cid wish_chk from one_wishlist where email=?) b " + 
					"where a.cid = b.cid(+) and cstatus=1" + 
					"order by cdate desc";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setSpicture(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setSchedule(rs.getString(6));
				vo.setRegionmain(rs.getString(7));
				vo.setWish_chk(rs.getString(8));
				
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * Update : ���� �� Ŭ���� cstatus 1�κ���
	 */
	public void updateStatus2(String cid) {
		try {
			String sql="update one_class set cstatus=0 where cid=? ";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			int val=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update : ���� �� Ŭ���� cstatus 1�κ���
	 */
	public void updateStatus1(String cid) {
		try {
			String sql="update one_class set cstatus=1 where cid=? ";
			getPreparedStatement(sql);
			pstmt.setString(1, cid);
			int val=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *  ��ü ����Ʈ ī��Ʈ2
	 */
	public int getListCount2(String inp_sch) {
		int result =0;
		try {
			getStatement();
			String sql = "select count(*) from(select cid, email, regionmain, catemain, catesub, price, picture, "
					+ " schedule, title , rownum  from one_class where title "
					+ " like '%" +inp_sch+ "%' or catemain like '%" + inp_sch +"%' or catesub like '%" + inp_sch + "%')"; 
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()) result= rs.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 *  ��ü ����Ʈ ī��Ʈ
	 */
	public int getListCount() {
		int result =0;
		try {
			String sql = "select count(*) from one_class";
			getPreparedStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) result= rs.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Select : spicture�� �̿��Ͽ� Cid��������
	 */
	public String getCid1(String spicture) {
		String cid="";
		try {
			String sql="select cid from one_class where spicture=?";
			getPreparedStatement(sql);
			pstmt.setString(1, spicture);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				cid=rs.getString(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cid;
	}
	
	/**
	 *  Update : ������� 3������ - ����
	 */
	public boolean classUpdate3(ClassVO vo) {
		boolean result=false;
		try {
			String sql="update one_class set tutorinfo=?,introduction=?,target=?,curriculum=?,tutornotice=? where cid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getTutorinfo());
			pstmt.setString(2, vo.getIntroduction());
			pstmt.setString(3, vo.getTarget());
			pstmt.setString(4, vo.getCurriculum());
			pstmt.setString(5,vo.getTutornotice());
			pstmt.setString(6,vo.getCid());
			
			int val=pstmt.executeUpdate();
			if(val!=0) result=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 *  Update : ������� 2������ - ����
	 */
	public boolean classUpdate2(ClassVO vo) {
		boolean result=false;
		try {
			String sql="update one_class set price=?,time=?,schedule=? where cid=?";
			getPreparedStatement(sql);
			pstmt.setInt(1, vo.getPrice());
			pstmt.setInt(2, vo.getTime());
			pstmt.setString(3, vo.getSchedule());
			pstmt.setString(4,vo.getCid());
			int val=pstmt.executeUpdate();
			if(val!=0) result=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 *  Insert : ������� 1������ - ����
	 */
	public boolean classInsert1(ClassVO vo) {
		boolean result=false;
		try {
			String sql="insert into one_class values('C_'||seq_one_class.nextval,?, " + 
					"  ?,?,?,?,'������',?,?,?,?,?,0,0,'������','Ʃ�ͼҰ�','��������','�����Ұ�' " + 
					"  ,'�������','Ŀ��ŧ��',0,0,sysdate)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getRegionmain());
			pstmt.setString(3, vo.getRegionsub());
			pstmt.setString(4, vo.getCatemain());
			pstmt.setString(5, vo.getCatesub());
			pstmt.setInt(6, vo.getPerson());
			pstmt.setString(7, vo.getTitle());
			pstmt.setString(8, vo.getPicture());
			pstmt.setString(9, vo.getSpicture());
			pstmt.setString(10, vo.getVideos());
			int val = pstmt.executeUpdate();
			if(val!=0) {result=true;}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 *  Update :��ȸ�� ������Ʈ-����
	 */
//	public void getUpdateHits(String bid) {
//		try {
//			String sql="update classtable set bhits=bhits+1 where bid = ?";
//			getPreparedStatement(sql);
//			pstmt.setString(1, bid);
//			pstmt.executeUpdate();
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	/**
	 *  ��������Ʈ ��������- ����
	 */
	public ArrayList<ClassVO> getCList4(int start, int end,String cateMain){
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		try {
			String sql = "select * from (select rownum cno,cid,title,picture,price,regionmain,catemain,spicture from (select * from one_class where cstatus=1 and catemain=? order by cdate desc)) where cno between ? and ? ";
			getPreparedStatement(sql);
			pstmt.setString(1, cateMain);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setPicture(rs.getString(4));
				vo.setPrice(rs.getInt(5));
				vo.setRegionmain(rs.getString(6));
				vo.setCatemain(rs.getString(7));
				vo.setSpicture(rs.getString(8));
				
				list.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 *  ��������Ʈ ��������- ����
	 */
	public ArrayList<ClassVO> getCList3(int start, int end,String cateSub){
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		try {
			String sql = "select * from (select rownum cno,cid,title,picture,price,regionmain,catemain,spicture from (select * from one_class where cstatus=1 and catesub=? order by cdate desc)) where cno between ? and ? ";
			getPreparedStatement(sql);
			pstmt.setString(1,cateSub);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setPicture(rs.getString(4));
				vo.setPrice(rs.getInt(5));
				vo.setRegionmain(rs.getString(6));
				vo.setCatemain(rs.getString(7));
				vo.setSpicture(rs.getString(8));
				
				list.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 *  ��������Ʈ ��������- ����
	 */
	public ArrayList<ClassVO> getCList2(int start, int end){
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		try {
			String sql = "select * from (select rownum cno,cid,title,picture,price,regionmain,catemain,spicture from (select * from one_class where cstatus=1 order by cdate desc)) where cno between ? and ? ";
			getPreparedStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setPicture(rs.getString(4));
				vo.setPrice(rs.getInt(5));
				vo.setRegionmain(rs.getString(6));
				vo.setCatemain(rs.getString(7));
				vo.setSpicture(rs.getString(8));
				
				list.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 *  ��������Ʈ ��������- ����
	 */
	public ArrayList<ClassVO> getCList(int start, int end){
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		try {
			String sql = "select * from (select rownum cno,cid,title,to_char(cdate,'yyyy.mm.dd') cdate,cstatus from (select * from one_class order by cdate desc)) where cno between ? and ?";
			getPreparedStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setCdate(rs.getString(4));
				vo.setCstatus(rs.getInt(5));
				
				list.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/*�ε��� �������� ���� ���� ������ ���� 8�� ��������*/
	public ArrayList<ClassVO> getIndexList4(){
		ArrayList<ClassVO> list3 = new ArrayList<ClassVO>();
		try {
			String sql = " select cid ,catemain from one_class where cid in (select cid from(select rownum ,cid ,starpoint from(select cid ,avg(rservice)starpoint from one_review  group by cid)where rownum between 1 and 8 order by starpoint desc))";
			getPreparedStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ClassVO vo=new ClassVO();
				vo.setCid(rs.getString(1));
				vo.setCatemain(rs.getString(2));
				
				list3.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list3;
	}
	
	/**
	 * ƩƼ���� ���� ã�� ���� ����Ʈ- ����
	 */
	public ArrayList<ClassVO> getIndexList3(String email){
		ArrayList<ClassVO> list2 = new ArrayList<ClassVO>();
		try {
			String sql = "select cno, a.cid, email, spicture, title, schedule, regionmain, wish_chk from " + 
					" (select rownum cno, cid,email,title,regionmain,schedule,spicture from one_class where cid in (select cid from(select rownum cno, cid, count1 " + 
					" from(select cid ,count(*) count1 from one_apply_class group by cid " + 
					" order by count1 desc,sysdate desc)where rownum between 1 and 6))) a, (select cid, cid wish_chk from one_wishlist where email=?) b " + 
					" where a.cid = b.cid(+) order by cno asc";
			getPreparedStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setSpicture(rs.getString(4));
				vo.setTitle(rs.getString(5));
				vo.setSchedule(rs.getString(6));
				vo.setRegionmain(rs.getString(7));
				vo.setWish_chk(rs.getString(8));
				
				list2.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list2;
	}
	
	/**
	 *  ��������Ʈ ��������- ����
	 */
	public ArrayList<ClassVO> getCList(){
		ArrayList<ClassVO> list = new ArrayList<ClassVO>();
		try {
			String sql = " select rownum cno,cid,title,to_char(cdate,'yyyy.mm.dd') cdate from (select * from one_class order by cdate desc)";
			getPreparedStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ClassVO vo=new ClassVO();
				vo.setCno(rs.getInt(1));
				vo.setCid(rs.getString(2));                     
				vo.setTitle(rs.getString(3));
				vo.setCdate(rs.getString(4));
				
				list.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
