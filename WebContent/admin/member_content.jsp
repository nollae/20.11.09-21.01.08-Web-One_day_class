<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member_content</title>
<link rel="stylesheet" href="http://localhost:9000/One_day_class/js_sh/swiper-bundle.min.css">
<style>
	*{
		box-sizing: border-box;
	}
	div, main, section {
		display: block;
	}
	body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, form, fieldset, p, button, pre {
	    margin: 0;
	    padding: 0;
	    font-size: 14px;
	    color: #111;
	    font-family: 'Noto Sans KR';
	    font-weight: 400;
	}
	.admin_content{
		width: 1040px;
    	margin: 0 auto;
    	padding: 50px 0 120px 0;
	}
	/* aside */
   .admin_main {
      float:left;
      width:220px; height:460px;
      background-color:#eee;
      padding:20px 0 0 15px;
   }
   
   .admin_main ul {
      width:195px;
      height:200px;
      margin-top:20px;
   }
   .admin_main ul li {
      list-style-type:none;
      padding: 4px 0;
      margin:25px 0 5px 15px;
   }   
   .admin_main ul li img {
      width:15px;
      height:15px;
   }
   .admin_main ul li a {
      color:black;
      font-size:17px;
      font-weight:bold;
      text-align:center;
      text-decoration:none;
      margin-left:10px;
   }   
   
   .admin_main ul li a:hover {
      text-decoration:underline;
   }
   .admin_main div {
      background-color: #fff;
      padding-top:7px;
      width:195px;
      height:145px;
   }
   .admin_main div img{
      margin:10px 0 0 62px;
      width:80px; 
      height:80px;
   }
   .admin_main .admin_icon2 {
      display:inline-block;
      font-size:20px;
      margin:7px 0 0 27px;
      color: #333;
      text-align:center;
      font-weight:bold;
   }
	.ad_title{
		width: 1040px;
		height: 35px;
		font-size: 16px;
		font-weight: bold; 
		text-align: center;
		color: #333;
		border: 1px solid lightgray;
	}
	.admin_member_content { 
		width: 820px;
		height: 473px;
	}
	.admin_member_content, .admin_member_content th, .admin_member_content td { 
		border: 1px solid #333;	
		border-collapse: collapse;
	}
	table.admin_member_content th, table.admin_member_content td{
		color: #333;
	}
	table.admin_member_content th {
		background-color: #eee;
		font-size: 16px;
		width: 100px;
		height: 115px;
	}
	table.admin_member_content tr:nth-child(1) th,
	table.admin_member_content tr:nth-child(2) th {
		height: 40px;
	}
	table.admin_member_content tr td{
		padding: 0 45px;
	}
	table.admin_member_content tr:last-child td{
		padding: 0;
	}
	table.admin_member_content tr:last-child td a > button{
		font-size: 12px;
		width: 70px;
		padding: 5px 0;
		border-radius: 4px;
		margin-right: 5px;
		cursor: pointer;
		font-weight:bold;
	}
	table.admin_member_content tr:last-child td {
		text-align: right;
	}
	.admin_section1 table.admin_member_content tr:last-child td{
		border: 1px solid white;
		border-top: 1px solid #333;
		padding-top: 15px;
	}
</style>

</head>
<body>
	<!-- header -->
	<jsp:include page="../header.jsp"></jsp:include>

	<!-- content -->
	<div class="admin_content">
		<aside class="admin_main">
		   <nav>
		      <div>
		         <img src="http://localhost:9000/One_day_class/images/admin_profile.png"><br>
		         <span class="admin_icon2">[ 관리자 시스템 ]</span>
		      </div>
		      <ul>
		         <li><img src="http://localhost:9000/One_day_class/images/admin_list.png"><a href="notice_list_admin.jsp">공지사항/이벤트</a></li>
		         <li><img src="http://localhost:9000/One_day_class/images/admin_list.png"><a href="#">수업관리</a></li>
		         <li><img src="http://localhost:9000/One_day_class/images/admin_list.png"><a href="member_list.jsp">회원관리</a></li>
		      </ul>
		   </nav>
		</aside>
		<section class="admin_section1">
			<div class="ad_title">회원상세정보</div>
			<table class="admin_member_content">
				<tr>
					<th>아이디</th>
					<td>qwe123@naver.com</td>
					<th>성명</th>
					<td>홍길동</td>
					<th>가입일</th>
					<td>2020.12.10</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>010-1234-5678</td>
					<th>나이</th>
					<td>20대</td>
					<th>거주지역</th>
					<td>서울</td>
				</tr>
				<tr>
					<th>신청한 수업</th>
					<td colspan="5">[원데이 메이크업]뭘 바르냐가 아니라, '어떻게'바르냐의 차이.</td>
				</tr>
				<tr>
					<th>수강중인 수업</th>
					<td colspan="5">[남양주/구리SNPE] 거북목, 일자허리, 사무직 필수코스! #통증완화 #체형교정 #SNPE #바른자세척추운동<br>
									⭐️ 12월 할인 이벤트! 나만의 매력에 가치를 더 하는 퍼스널컬러 컨설팅⭐️</td>
				</tr>
				<tr>
					<th>개설한 수업</th>
					<td colspan="5">-</td>
				</tr>
				<tr>
					<td colspan="6">
						<a href="member_list.jsp"><button type="button" class="btn_style">목록으로</button></a>
					</td>
				</tr>
			</table>
		</section>
	</div>

	<!-- footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>