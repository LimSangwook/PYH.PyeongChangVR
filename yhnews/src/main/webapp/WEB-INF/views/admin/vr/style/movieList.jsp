<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>
			<!-- 검색 -->
			<fieldset>
			<legend></legend>
			<form action="#">
				<div class="search_wrap com-pb10">
					<select name="search_select" title="구분 선택">
						<option>경기장 선택</option>
						<option value="1">알펜시아 바이애슬론 센터</option>
						<option value="2">알펜시아 크로스컨트리 센터</option>
						<option value="3">알펜시아 스키점프 센터</option>
						<option value="4">올림픽 슬라이딩 센터</option>
						<option value="5">휘닉스 스노 경기장</option>
						<option value="6">정선 알파인 경기장</option>
						<option value="7">용평 알파인 경기장</option>
						<option value="8">관동 하키 센터</option>
						<option value="9">강릉 컬링 센터</option>
						<option value="10">강릉 하키 센터</option>
						<option value="11">강릉 아이스 아레나</option>
						<option value="12">강릉 스피드 스케이팅 경기장</option>
					</select>
					<select name="search_select" title="구분 선택">
						<option value="0">제목</option>
					</select>
					<input type="text" name="search_word" title="검색어 입력" class="col25">
					<a href="#" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</a>
				</div><!-- //search_wrap -->
			</form>
			</fieldset>

			<table class="table_basic">
				<caption>테이블</caption>
				<colgroup>		
					<col style="width:5%;" />
					<col style="width:10%;" />
					<col style="width:20%;" />
					<col style="width:auto;" />
					<col style="width:15%;" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">스타일</th>
						<th scope="col">경기장</th>
						<th scope="col">제목</th>
						<th scope="col">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="data" items="${result}" varStatus="i">
						<tr>
							<td>5</td>
							<td>동영상</td>
							<td>${data.vr_site_title}</td>
							<td class="textAlign_left"><a href="movieForm.do?vr_style_movie_id=${data.vr_style_movie_id}">${data.title}</a></td>
							<td>${data.mod_only_date}</td>
						</tr>
					</c:forEach>
					
			</table><!-- //table_basic -->

			<!-- 페이지 -->
			<div class="pagenate_wrap float_left">
				<ul class="pagenation">
					<a href="#" class="ico ico_arrow_prev">이전</a>
					<a href="#">1</a>
					<span>2</span>
					<a href="#">3</a>
					<a href="#">4</a>
					<a href="#">5</a>
					<a href="#">6</a>
					<a href="#">7</a>
					<a href="#">8</a>
					<a href="#">9</a>
					<a href="#">10</a>
					<a href="#" class="ico ico_arrow_next">다음</a>
				</ul>
			</div><!-- //pagenate_wrap -->

			<div class="btn_wrap float_right">
				<a href="movieForm.do" class="btn primary">등록</a>
			</div>
