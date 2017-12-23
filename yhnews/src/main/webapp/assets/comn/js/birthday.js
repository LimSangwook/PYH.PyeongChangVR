// 셀렉트 박스 생년월일
// 임시 스크립트 (수정 필요)
var birthday = function(){
	function appendYear(){
		var date = new Date();
		var year = date.getFullYear();
		var selectValue = document.getElementById("year");
		var optionIndex = 0;
		for(var i=year-100;i<=year;i++){
				selectValue.add(new Option(i,i),optionIndex++);
		}
	}
	appendYear()
	function appendMonth(){
		var selectValue = document.getElementById("month");
		var optionIndex = 0;
		for(var i=1;i<=12;i++){
				selectValue.add(new Option(i,i),optionIndex++);
		}
	}
	appendMonth()
	function appendDay(){
		var selectValue = document.getElementById("day");
		var optionIndex = 0;
		for(var i=1;i<=31;i++){
				selectValue.add(new Option(i,i),optionIndex++);
		}
	}
	// appendDay()
}
birthday()
var birthday2 = function(){
	function appendYear(){
		var date = new Date();
		var year = date.getFullYear();
		var selectValue = document.getElementById("year2");
		var optionIndex = 0;
		for(var i=year-100;i<=year;i++){
				selectValue.add(new Option(i,i),optionIndex++);
		}
	}
	appendYear()
	function appendMonth(){
		var selectValue = document.getElementById("month2");
		var optionIndex = 0;
		for(var i=1;i<=12;i++){
				selectValue.add(new Option(i,i),optionIndex++);
		}
	}
	appendMonth()
	function appendDay(){
		var selectValue = document.getElementById("day2");
		var optionIndex = 0;
		for(var i=1;i<=31;i++){
				selectValue.add(new Option(i,i),optionIndex++);
		}
	}
	// appendDay()
}
birthday2()
