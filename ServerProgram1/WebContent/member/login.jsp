<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>

<style>

		.login_form{
				width : 300px;
				margin : 50px auto;
			}
			
		#f input{
			width : 100%;
			height : 50px;
			padding: 5px;
		}
</style>
<script>
	$(document).ready(function(){
		const f = $('#f');
		const id = $('#id');
		const name = $('#name');
		f.submit(function(event){
			if(id.val() == '' || name.val() == ''){
				alert('아이디와 이름은 필수입니다!');
				id.focus();
				event.preventDefault();
				return false;
			}
		})		
	})

</script>

<div class = "login_form">
	<h1>회원 관리 프로그램</h1>
	<form action = "/10_MODEL2/login.do" id = "f" method = "post">
		<input type = "text" name = "id" id = "id" placeholder = "ID"><br>
		<input type = "text" name = "name" id ="name" placeholder = "이름"><br>
		<button>로그인</button>
		<a href = "/ServerProgram1/join.do">회원가입</a>
	</form>
</div>
