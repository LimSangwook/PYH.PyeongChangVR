function tictoc_start(){
	var Digital=new Date();
	var hours=Digital.getHours();
	var minutes=Digital.getMinutes();
	var seconds=Digital.getSeconds();

	if(hours<10) hours="0"+hours;
	if(minutes<10) minutes="0"+minutes;
	if(seconds<10) seconds="0"+seconds

	//아래에서 원하는 형태대로 시계를 설정 하세요
	myclock=hours+":"+minutes+":"+seconds;

	$("#tictok").text(myclock);
	setTimeout("tictoc_start()",1000);
}