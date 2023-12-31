<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <base href="http://localhost:8080/gmxlang/">
    <title>Edit your Post</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <link rel="stylesheet" href="/gmxlang/css/post/changePost.css">
</head>
<body>

    <div class="editForm">
        <form id="changeContent" onsubmit="return changeContent(event)">
            <label for="newContent">Edit your Content</label>
            <sec:csrfInput/>
            <!-- 숨겨서 전송 -->
            <input type="hidden" name="postId" value="${postId}"> 
		    
            <input type="text" id="newContent" name="newContent" required="required">
            
            <div class="buttonGroup">
                <input type="submit" value="Edit" class="postButton" />
                <button class="goBackButton" type="button" onclick="location.href='post/detailPost.do?postId=${postId}'">Go Back</button>
            </div>
        </form>
    </div>

    <script>
	    $(document).ready(function() {
	        let token = localStorage.getItem('jwtToken');
	        let csrfToken = $("input[name='_csrf']").val();
	
	        if (!token) {
	            refreshToken();
	        } else {
	            verifyToken();
	        }
	    });
	
	    function verifyToken(attemptedRefresh = false) {
	        let token = localStorage.getItem('jwtToken');
	        if (token) {
	            $.ajax({
	                url: 'token/verifyToken.do',
	                type: 'GET',
	                beforeSend: function(xhr) {
	                    xhr.setRequestHeader("Authorization", "Bearer " + token);
	                },
	                success: function(response) {
	                    console.log('Token is valid');
	                },
	                error: function(error) {
	                    if (!attemptedRefresh) {
	                    	console.log('from verifyToken to refreshToken');
	                        refreshToken();
	                    } else {
	                    	console.log('from verifyToken to handleTokenError');
	                        handleTokenError();
	                    }
	                }
	            });
	        }
	    }
	
	    function refreshToken() {
	        $.ajax({
	            url: 'token/refreshToken.do',
	            type: 'GET',
	            success: function(response) {
	                console.log('Success in refreshToken. Token is => ', response.token);
	                localStorage.setItem('jwtToken', response.token);
	                console.log('Refreshed token');
	                verifyToken(true);
	            },
	            error: function(xhr) {
	                console.log('Error in refreshToken');
	                handleTokenError(xhr);
	            }
	        });
	    }
	
	
	    function handleTokenError(xhr = null) {
	        let errorMessage = xhr ? (xhr.status + ': ' + xhr.statusText) : 'Unknown error';
	        alert('Authentication failed - ' + errorMessage);
	
	        localStorage.removeItem('jwtToken');
	        window.location.href = 'user/logout.do';
	    }
	
		function checkContent() {
		     var newContent = document.getElementById("newContent").value;
		     if (newContent.trim() === "") {
		         alert("게시글 내용은 null을 허용하지 않습니다.");
		         return false;
		     }            
		     return true;
		 }
		
		 function changeContent(event) {
		     event.preventDefault(); 
		     if (!checkContent()) { 
		         return;
		     }
		     // 폼 데이터 직렬화 key=value로 만들어 ajax로 간편하게 데이터 보냄
		     let formData = $('#changeContent').serialize(); // 반환하는 데이터 타입은 기본적으로 application/x-www-form-urlencoded 
		     let token = localStorage.getItem('jwtToken');
		     
		     $.ajax({
		         url: 'post/changePost.do',
		         type: 'POST',
		         data: formData, // controller의 consume
		         dataType: "json", // controller의 produces
		         success: function(response) {
		             if (response.status === "success") {
		                 window.location.href = 'post/detailPost.do?postId=' + $('[name="postId"]').val(); // GET 방식임
		             } else {
		                 alert("게시물 업데이트 실패");
		             }
		         },
		         error: function(error) {
 	            	alert('ajax error', error);
 	            }
		     });
		}



    </script>

</body>
</html>