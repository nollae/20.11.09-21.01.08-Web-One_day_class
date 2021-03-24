<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.one_day_class.vo.*, com.one_day_class.dao.*, java.util.ArrayList"%>
    
    
<%SessionVO svo=(SessionVO)session.getAttribute("svo"); %>

<% if(svo != null){  if(svo.getIdentity().equals("튜터")){
   String email = svo.getEmail();
      
   
      
       ms_TutorclassDAO dao = new ms_TutorclassDAO();
      /* String email ="alstndkrl@naver.com"; */
       /* String cid = dao.getCid1(email); */
       String cid = "";
      if(request.getParameter("cid") != null){
         cid = request.getParameter("cid");
      }else{
          cid = dao.getCidnull(email);
      }
      ms_TutorclassVO vo = dao.getMyclass(cid); //cid값이 변경되면 그 변경된값의 내용을 가져온다
      String[] pic_array = vo.getSpicture().split(",");
      ArrayList<ms_TutorclassVO> list = dao.getTutorList(cid);
        ArrayList<ms_TutorclassVO> list1 = dao.getClassList(cid);
      ArrayList<ms_TutorclassVO> list2= dao.getMyList(email);
      int j=0;
      System.out.println(cid);
      System.out.println("cstats ="+vo.getCstatus());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈멍  :: 내수업</title>
<script src="http://localhost:9000/One_day_class/js_minsu/jquery-3.5.1.min.js"></script>
<script>
   $(document).ready(function(){
       //해당 cid값에 맞는 select 값 세팅
         $("#selectbox").val("<%=cid%>");
      
      
         //전체체크 선택
      /*  $("#all").change(function(){
         if($(this).is(":checked")){
            $("input[name='chk']").prop("checked",true)
         }else{
            $("input[name='chk']").prop("checked",false)
         }
         
      });  */
         
         
         
      $("#true").click(function(){
         if($("[name=chk]:checked").val() != null){
          $("#classbtn").val("true");
          class_check.submit(); 
         }else{
            alert("신청수락할 튜티를 선택해주세요.");
            return false;
         }
       });
       
       $("#fail").click(function(){
          if($("[name=chk]:checked").val() != null){
          $("#classbtn").val("fail");
          class_check.submit(); 
          }else{
             alert("신청거절할 튜티를 선택해주세요.");
             return false;
          }
       });   
         
      
          
   $("#simsa").click(function(){
      var choice = $(this).attr("name");
      if(choice == "simsa"){
         alert("수업을 취소 하시겠습니까?");
         
      }
   });       
   
      
   $("#selectbox").change(function(){
      $(location).attr('href','http://localhost:9000/One_day_class/tutor/new-class.jsp?cid='+$(this).val()); 
      System.out.println(cid);
      /* $(this).attr('text','$("#selectbox").text()+$(this).val())'; */
   });

   
   
 //체크박스        
   function allCheckFunc(obj) {
      $("[name=chk]").prop("checked", $(obj).prop("checked"));
      //alert($("[name=checkAll]:checked").val());
      
   }
   /* 체크박스 체크시 전체선택 체크 여부 */
   function oneCheckFunc(obj) {
      var allObj = $("[name=all]");
      var objName = $(obj).attr("name");

      if($(obj).prop("checked")){
         checkBoxLength = $("[name="+ objName +"]").length;
         checkedLength = $("[name="+ objName +"]:checked").length;                

         if(checkBoxLength == checkedLength){
            allObj.prop("checked", true);    
         }else{
            allObj.prop("checked", false);
         }
      }else{
         allObj.prop("checked", false); 
      }
   }
   
   $(function(){
      $("[name=all]").click(function(){
         allCheckFunc(this);
            });
      });
      $("[name=chk]").each(function(){
         $(this).click(function(){
            oneCheckFunc($(this));
         });                
      });
   
   
   
    
});//ready
   
</script>
<style>

 * {
     font-family: 'Noto Sans KR';
   }
   #selectbox{
         font-weight:bold;
         font-size:16px;
         color:black;
   }
   div.new_class{
      width:1200px;
      margin:auto;
   }
   div.title-box {
      width:1100px;
      text-align:center;
      margin-top:50px;
      margin-left:40px;
      
   }
   div.static-box {
       border-bottom:1px solid lightgray;
      height:450px;
      width:95%;
      margin-left:40px;
   }
   div.static-box>div.box {
      width:500px;
      height:300px;
      vertical-align:top;
      margin-left:30px;
      float:left;
   }
   div.static-box>div.box>div.box1 {
      display:inline-block;
      width:490px;
      height:230px;
      padding-left:40px;
   }
   div.static-box>div.box>div.box1>span.red {
      color:red;
      font-size:13px;
   }
   div.static-box>div.box>div.box1>a>h3 {
      font-weight:bold;
      font-size:30px;
      margin-bottom:35px;
      color:#ff0045;
   }
   div.static-box>div.box>div.box1>a{
   	text-decoration: none;
   }
   .sim {
      color:#ff0045;
   }
   .pink {
      color:#ff0045;
   }
   .mid,.sim,.simsa{
      font-size:18px;
      font-weight:bold;
   }
   
   div.static-box>div.box>div.box2 {
      display:inline-block;
      width:490px; height:70px;
   }
   div.static-box>div.box>div.box2>button.btn1:hover,
   div.static-box>div.box>div.box2>a>button.btn1:hover,
   div.static-box>div.box>div.box2>a>button.btn3:hover,
   .con2_btn>button:hover {
      background-color:white;
      border:1px solid rgb(73,56,245);
      color:rgb(73,56,245);
   }
   div.static-box>div.box>div.box2>button.btn1,
   div.static-box>div.box>div.box2>a>button.btn1 {
      margin:20px 5px 50px 40px;
      width:170px; height:40px;
      background-color:white;
      color:#ff0045;
      border:1px solid #ff0045;
      border-radius:6px;
      
   }
   div.static-box>div.image {
      display:inline-block;
      width:500px;
      margin-left:30px;
      float:left;
   }
   div.static-box>div.image>img {
      width:500px; height:300px;
   }
   div>h1.tt {
      display:inline-block;
      width:150px; height:50px;
      font-size:30px;
      font-weight:bold;
      text-align:left;
      float:left;
      
   }
   div>select.title {
      border:1px solid gray;
      width:700px; height:50px;
      float:right;
      margin-bottom:10px;
      border-radius:5px;
      padding:10px 3px;
   }
   div>select.title {
      color:gray;
   }
   hr.top-hr {
      margin-top:50px;
      opacity:0.5;
      width:100%
   }
   hr.line {
      opacity:0.5;
      margin-bottom:-5px;
      margin-top:20px;
      width:1120px;
   }
   
   div.static-box {
       border-bottom:1px solid lightgray;
      height:450px;
      width:95%;
      margin-left:40px;
   }
   div.container3 {
    width:95%;
    height:400px;
    border-bottom:1px solid lightgray;
      margin-left:40px;
   }
   div.static-box h3.tt1 {
      font-size:28px;
       border-bottom:4px solid #ff0045;
       width:220px;
       padding-left:20px;
       padding-bottom:10px;
      
   }
   div.container3>h3.tt2 {
      font-size:28px;
      border-bottom:4px solid #ff0045;
       width:220px;
       padding-left:20px;
       padding-bottom:10px;
      margin-right:40px;
      display:block;
   }
   div.container2 {
      display:inline-block;
      margin-left:40px;
      
   }
   div.container2>div.con3>h3.tt3 {
       font-size:28px;
       border-bottom:4px solid #ff0045;
       width:220px;
       padding-left:20px;
       padding-bottom:10px;
   }
   div.container2>div.con3>div.con4 {
      font-size:15px;
      opacity:0.8;
   }
   div.image1{
      display:inline-block;
      margin-left:40px;
      margin-top:40px;
      margin-bottom:40px;
   }
   div.image1>img:hover,
   div.image1>a>img:hover{
      border:1px solid black;
   }
   /** apply공간 **/
   
   hr {
      opacity:0.4;
   }
   .con2-1 {
      border:2px solid lightgray;
      border-radius:5px;
      width:1000px; height:210px;
      margin-left:65px;
      margin-bottom:100px;
      padding:15px 0 10px;
      overflow: auto;
   
   }
   
   .con2-1::-webkit-scrollbar{width: 4px;}
   .con2-1::-webkit-scrollbar-thumb {background-color:gray;}
   
   .con2-1>ul {
      margin-bottom:7px;
      margin-left:10px;
   }
   .con2 {
      border:2px solid lightgray;
      border-radius:5px;
      display:inline-block;
      width:1000px;
      height:200px;
      margin-left:25px;
      padding:10px 0 10px 5px;
      text-align:center;
      overflow: auto;
   }
   .con2::-webkit-scrollbar{width: 4px;}
   .con2::-webkit-scrollbar-thumb {background-color:gray;}
   
   .con2-1>.ul_title1 {
      border-bottom:2px solid lightgray;
      padding-bottom:5px;
   }
   .con2>ul>li,
   .con2>form>ul>li,
   .con2-1>ul>li {
      display:inline-block;
      text-align:center;
      font-size:15px;
      color:rgb(85,85,85);
      vertical-align:middle;
   }
   .con2>ul.ul_title>li,
   .con2-1>.ul_title1>li {
      font-size:19px;
      font-weight:bold;
      color:black;
   }
   .con2_btn>button {
      margin-top:20px;
      float:right;
      margin-right:10px;
      width:170px; height:40px;
      display:inline-block;
      background-color:white;
      color:#ff0045;
      border-radius:6px;
      border:1px solid #ff0045;
   }
   .container3 {
      width:840px;
      height:350px;
   }
   .inp_chkbox{
      zoom:1.5;
   
   }
   
   .sinbar{
      height:3px;
      background-color:lightgray;
      border-radius:9px;
   }
   .bar{
      width:950px;
   }
   .ut1,
   .ut2,
   .ut4{
      width:130px;
   }
   .ut5 {
      width:150px;
   }
   .ut3 {
      width:350px;
   }
   .ut3-1{
      width:170px;
   }
   .ut4-1 {
      width:300px;
   }
   .ut6,
   .ut7{
        width:100px;
   }
   .ut8{
        width:220px;
   }
   .ut9{
        width:400px;
   }
   .ut10{
        width:100px;
   }
   .bar1{
        width:950px;
   }
   /** 체크분홍 **/
   
   .inp_label:checked + label::before {
       background-image: url(http://localhost:9000/One_day_class/images/mb_check_on.png);
       background-size: 13px;
   }
   .inp_chkbox::before {
       content: '';
       display: inline-block;
       width: 18px;
       height: 18px;
       vertical-align: middle;
       background-image:url(http://localhost:9000/One_day_class/images/mb_check_off.png);
       background-repeat: no-repeat;
       background-size: 13px;
   }
   .blind {
      position: absolute !important;
       clip: rect(0,0,0,0);
       clip-path: polygon(0 0,0 0,0 0);
       width: 1px;
       height: 1px;
       margin: -1px;
       overflow: hidden;
       white-space: nowrap;
   } 
   
</style>
</head>
<body>
   <!--  session  -->
         <% if(svo != null) { %>
      <% if(svo.getIdentity().equals("튜터")) { %>
         <jsp:include page="../header_tutor.jsp" />
      <% } else if(svo.getIdentity().equals("튜티")) {%>
         <jsp:include page="../header_login.jsp" />
   <% } %>
   <% } else {%>
      <jsp:include page="../header.jsp" />
   <% } %>
   
   
   
   <div class="new_class">
   
      <hr class="top-hr">
      <div class="title-box">
      <h1 class="tt">내 수업</h1>
      <select name="title" class="title" id="selectbox">
      <% for(ms_TutorclassVO vo3 : list2){ %>
         <option id="selectbox1" value="<%=vo3.getCid()%>"><%=vo3.getTitle()%></option>
      <%} %>
      </select>
      </div>
      <hr class="line">
      <div class="static-box">
      <%if(vo.getCstatus() != 0){ %>
         <h3 class="tt1">수업 심사완료</h3>
         <%}else{ %>
         <h3 class="tt1">수업 심사중</h3>
         <%} %>
         <div class="image">
            <img src="http://localhost:9000/One_day_class/upload/<%=pic_array[0]%>"> <!-- <--%=pic_array[0]%>  -->
         </div>
         <div class="box">
            <div class="box1">
              <a href="http://localhost:9000/One_day_class/class/class.jsp?cid=<%=vo.getCid()%>"><h3>[<%=vo.getCatemain()%>] <%=vo.getCatesub() %></h3></a> 
               <span class="mid">[ <%=vo.getName() %> ] 튜터 님!<br><br></span>
            <%if(vo.getCstatus() != 0){ %>
               <span class="mid">수업 심사가 드디어 완료됐습니다!<br></span>
               <span class="simsa">튜터님의 재능으로 <span class="pink">오늘도 즐거운'탈멍' 하세요:)</span></span></span>
            <%}else{ %>
               <span class="mid">즐거운 '탈멍'을 위해서는 수업 심사가 필요합니다.<br></span>
               <span class="sim">심사가 진행 중이니 조금만 기다려주세요:)</span>
            <%} %>
            </div>
            <div class="box2">
               <a href="http://localhost:9000/One_day_class/tutor/new_classDeleteProc.jsp?cid=<%=vo.getCid()%>"><button class="btn1" id="simsa" name="simsa"><span>신청 취소하기</span></button></a>
               <a href="http://localhost:9000/One_day_class/tutor/class_update.jsp?cid=<%=vo.getCid()%>"><button class="btn1"><span>신청 수정하기</span></button></a>
            </div>
         </div>
      </div>
      
      <div class="container3">
         <h3 class="tt2">신청 현황</h3>
         <div class="con2">
            <ul class="ul_title">
               <input  type="checkbox"  id="all" class="blind inp_label" name="all">
               <label for="all" class="inp_chkbox"></label>
               <li class="ut1">번호</li>
               <li class="ut2">이름</li>
               <li class="ut3">일정</li>
               <li class="ut4">인원수</li>
               <li class="ut5">상태</li>
            </ul>
            <hr class="sinbar">
         <form name="class_check" action="new_classProc.jsp?cid=<%=vo.getCid()%>" method="get" class="class_check">
         <input type="hidden" name="cid1" value="<%=cid%>">
            <input type="hidden" name="classbtn" value="" id="classbtn" class="blind inp_label">
            <% for(ms_TutorclassVO vo2 : list1) { j++;  String  i ="" ;
              if(vo2.getAstatus()!=0) {
                  i="신청수락";
               }else{
                  i="대기중";
               } 
            %>
            
            <ul class="sin">
               <input type="checkbox" name="chk" class="blind inp_label" id="chk<%=vo2.getRno() %>" value="<%=vo2.getEmail()%>"> <!-- 원래 cid값을 받아옴-->
               <label for="chk<%=vo2.getRno() %>" class="inp_chkbox"></label>
               <li class="ut1"><%=vo2.getRno() %></li>
               <li class="ut2"><%=vo2.getName() %></li>
               <li class="ut3"><%=vo2.getAschedule() %></li>
               <li class="ut4"><%=vo2.getAperson() %></li>
               <li class="ut5"><%=i%></li>
            </ul>
            <hr class="bar">
            <% } %>
            <input type="hidden" name="idx" value="<%=j %>">
            </form>
         </div>
         <div class="con2_btn">
            <button type="button" class="fail" id="fail">신청 거절하기</button>
            <button type="button" class="true" id="true">신청 수락하기</button>
         </div>
      </div>
      <div class="container2">
         <div class="con3">
         <h3 class="tt3">리뷰 현황</h3>
         </div>
      </div>
      <div class="con2-1">
            <ul class="ul_title1">
               <li class="ut6">번호</li>
               <li class="ut7">이름</li>
               <li class="ut8">일정</li>
               <li class="ut9">리뷰내용</li>
               <li class="ut10">작성날짜</li>
            </ul>
            <%
               for(ms_TutorclassVO vo1 : list) {
            %>
          <ul>
               <li class="ut6"><%=vo1.getRno() %></li>
               <li class="ut7"><%=vo1.getName() %></li>
               <li class="ut8"><%=vo1.getAschedule() %></li>
               <li class="ut9"><%=vo1.getRcontent() %></li>
               <li class="ut10"><%=vo1.getRdate() %></li>
            </ul>
            <hr class="bar1">
            <% } %>
             
            
            
      </div>
   
   <!--header -->
   <jsp:include page="../footer.jsp" />
   </div>
   
</body>
</html>
< <%}}else{%>
<script>
   alert(" 튜터로 로그인을 진행하셔야 접근이 가능합니다.");
   location.href="../index.jsp";
</script>
<%}%>