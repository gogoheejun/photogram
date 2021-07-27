// (1) 회원정보 수정
function update(userId,event) {
	event.preventDefault();//폼태그 액션을 막음
	let data = $("#profileUpdate").serialize();//key=value
	console.log(data);
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,//백틱주의
		data:data,
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		dataType:"json"
	}).done(res=>{//httpstatus 상태코드가 200번대
		console.log("update성공",res);
		location.href=`/user/${userId}`;
	}).fail(error=>{//httpstatus 상태코드가 200번대가 아닐때
		if(error.data==null){
			alert(error.responseJSON.message)
		}else{
			alert(JSON.stringify(error.responseJSON.data))
		}
	});
}