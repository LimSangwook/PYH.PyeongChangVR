<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>
	<!-- container -->
	<div id="container" class="container">
		<section class="section-guide01 guide-type01 sub-visual01">
			<div>
				<h1>평창 VR365</h1>
			</div>
		</section>
		<section class="section-vr">
		<!-- ========================== 내용 시작 ========================= -->
			<div class="vr_list vrlist_type01">
                <c:forEach var="data" items="${result}" varStatus="i">
                    <div class="item">
                        <div class="item_wrap">
                            <div class="thumb">
                                <img src="${CTX}/vrContents/adminContents/${data.path_image}" alt="사진" class="def">
                                <div class="dark"></div>
                            </div>
                            <span class="mark" style="background-image:url(${CTX}/vrContents/adminContents/${data.path_icon});"></span>
                            <h4 class="tit_con center">${data.title}</h4>
                            <div class="btn_wrap">
                                <a href="${CTX}/vrContents/adminContents/${data.vr_site_id}/vtour/tour.html"><span class="ico ico_vr"></span><br>VR보기</a>
                            </div>
                        </div>
                    </div><!-- //item -->
                </c:forEach>
 			</div><!-- //vr-list -->

		<!-- ========================== 내용 종료 ========================= -->
		</section><!-- //section-vr -->
	</div>
	<!-- //container -->
