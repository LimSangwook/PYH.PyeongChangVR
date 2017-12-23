<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

			<!-- ================ 내용시작 =================-->
			<!-- 검색 -->
			<fieldset>
			<legend>게시물 검색</legend>
			<form action="#">
				<div class="search_wrap float_right">
					<select name="search_select" title="구분 선택">
						<option value="0">경기장명</option>
					</select>
					<input type="text" name="search_word" title="검색어 입력" class="srh_word">
					<a href="#" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</a>
				</div><!-- //search_wrap -->
			</form>
			</fieldset>

			<div class="clearfix">
				<div class="guide_total float_left">
					총 <strong>${theForm.total_count}</strong>개의 경기장이 있습니다.
				</div>
			</div>
			<div class="vr_list clearfix">

				<c:forEach var="data" items="${result}" varStatus="i">
					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
								<img src="/assets/admin/vr/vr_site_default.jpg" alt="사진" class="def">
								<div class="dark"></div>
							</div>
							<span class="mark" style="background-image:url(/assets/admin/vr/vr_site_default_Thumb.png);"><span>평창 마운틴 클러스터</span>알펜시아 바이애슬론 센터</span>
							<h4 class="tit_con"><span>평창 마운틴 클러스터</span>${data.title}</h4>
							<div class="btn_wrap">
								<a href="siteForm.do?vr_site_id=<c:out value="${data.vr_site_id}"/>">경기장설정</a>						
								<a href="contentList.do?vr_site_id=<c:out value="${data.vr_site_id}"/>">VR목록보기</a>
							</div>
						</div>
					</div><!-- //item -->
				</c:forEach>
				
			</div><!-- //contents_list -->

			<div class="btn_wrap float_right">
				<a href="siteForm.do" class="btn primary">등록</a>
			</div>


			<!-- ================ 내용종료 =================-->

			<!-- //page_contents -->
