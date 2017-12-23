<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

			<div class="std_title"><span>평창 마운틴 클러스터</span><br>${vrSite.title}
				<a href="siteList.do" class="btn btn_small primary">경기장 목록보기</a>
			</div>

			<!-- 검색 -->
			<fieldset>
			<legend>게시물 검색</legend>
			<form action="#">
				<div class="search_wrap">
					<select name="search_select" title="구분 선택">
						<option value="0">제목</option>
					</select>
					<input type="text" name="search_word" title="검색어 입력" class="srh_word">
					<a href="#" class="btn inverse btn_small"><span class="ico ico_search"></span>검색</a>
				</div><!-- //search_wrap -->
			</form>
			</fieldset>

			<div class="clearfix">
				<div class="guide_total float_left">
					총 <strong>${vrSiteContentList.size()}</strong>개의 VR이 있습니다.
				</div>
				<div class="float_right">
					<select>
						<option>8개 보기</option>
						<option>16개 보기</option>
						<option>40개 보기</option>
						<option>80개 보기</option>
					</select>
				</div>
			</div>

			<div class="vr_list list2 clearfix">
				<c:forEach var="data" items="${vrSiteContentList}" varStatus="i">
					<div class="item">
						<div class="item_wrap">
							<div class="thumb">
                                <c:choose>
                                    <c:when test="${data.path_panorama_image_ui eq ''}">
                                        <img style="background-color:white;" src="/assets/admin/img/noPhoto.png" alt="사진" class="def">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${CTX}${data.path_panorama_image_ui}" alt="사진" class="def">
                                    </c:otherwise>
                                </c:choose>
								<div class="dark"></div>
							</div>
							<h4 class="tit_con">${data.name}</h4>
							<div class="btn_wrap">
								<a href="contentForm.do?vr_site_content_id=${data.vr_site_content_id}">VR수정</a>
								<a href="${CTX}/vrContents/adminContents/${data.vr_site_id}/vtour/tour.html?startscene=${i.index}">VR보기</a>
							</div>
						</div>
					</div><!-- //item -->
				</c:forEach>
			</div><!-- //contents_list -->

			<div class="btn_wrap float_right">
				<a href="contentForm.do?vr_site_id=${vrSite.vr_site_id}" class="btn primary">등록</a>
			</div>

