<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>
	<!-- container -->
	<div id="container" class="container">
		<section class="section-guide01 guide-type01 sub-visual02">
			<div>
				<h1>인기 VR 컨텐츠</h1>
			</div>
		</section>
		<section class="section-vr">
		<!-- ========================== 내용 시작 ========================= -->


			<div class="guide_wrap">
				<p>
					<span class="ico ico_guide"></span> 평창 VR360 컨텐츠를 등록 후 관리자의 승인처리 후 게시되오니 참고해주시기 바랍니다.
				</p>
			</div>

			<form action="#">
				<fieldset>
					<legend>VR컨텐츠 등록</legend>
					<div class="vr_write">
						<table summary="VR컨텐츠 등록">
							<caption>VR컨텐츠 등록</caption>
							<colgroup>
								<col style="width:15%;">
								<col style="width:85%;">
							</colgroup>
							<tbody>
								<tr>
									<th>경기장</th>
									<td>
										<select name="select_std" id="select_std" title="경기장선택" class="inp_type01 ">
											<option value="">경기장선택</option>
											<option value="std_1">알펜시아 바이애슬론 센터</option>
											<option value="std_2">알펜시아 크로스컨트리 센터</option>
											<option value="std_3">알펜시아 스키점프 센터</option>
											<option value="std_4">올림픽 슬라이딩 센터</option>
											<option value="std_5">휘닉스 스노 경기장</option>
											<option value="std_6">정선 알파인 경기장</option>
											<option value="std_7">용평 알파인 경기장</option>
											<option value="std_8">관동 하키 센터</option>
											<option value="std_9">강릉 컬링 센터</option>
											<option value="std_10">강릉 하키 센터</option>
											<option value="std_11">강릉 아이스 아레나</option>
											<option value="std_12">강릉 스피드 스케이팅 경기장</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>제목</th>
									<td><input type="text" id="" title="" class="inp_type01 subject"></td>
								</tr>
								<tr>
									<th>이름</th>
									<td><input type="text" id="" title="" class="inp_type01 name"></td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td><input type="text" id="" title="" class="inp_type01 tel1"> - <input type="text" id="" title="" class="inp_type01 tel1"> - <input type="text" id="" title="" class="inp_type01 tel1"></td>
								</tr>
								<tr>
									<th>사진첨부</th>
									<td><input type="file" id="" title=""></td>
								</tr>
								<tr>
									<td colspan="2">
										<!-- 에디터 위치 -->
										<textarea name="" id="" rows="20" class="col100"></textarea>
									</td>
								</tr>
							</tbody>
						</table>

					<div class="agree_wrap">
						<div class="agree_title">개인정보 수집ㆍ이용 동의</div>
						<div class="agree_txt">
							<p><strong>평창 VR360 홈페이지 운영을 위하여 아래와 같이 개인정보를 수집ㆍ이용하고자 합니다. 내용을 자세히 읽으신 후 동의 여부를 결정하여 주십시오.</strong></p>
							<p>□ 개인정보 수집ㆍ이용 내역<br>
							<ul>
								<li>- 필수항목 : 이름, 전화번호</li>
								<li>- 수집목적 : 게시물 승인 시 문자발송을 위한 연락처 수집</li>
								<li>- 보유기간 : 포스트 삭제시까지</li>
							</ul>
							</p>
							<p>※ 위의 개인정보 수집ㆍ이용에 대한 동의를 거부할 권리가 있습니다. 그러나 동의를 거부할 경우 원활한 서비스 제공에 일부 제한을 받을 수 있습니다.<br>
							※ 개인의 초상권에 침해하는 경우에는 관리자가 임의로 삭제할 수 있습니다.</p>
						</div>
						<div class="agree_check"><input type="checkbox" name="wr_agree" id="wr_agree"> <label for="wr_agree">개인정보를 수집ㆍ이용하는데 동의합니다. </label></div>
						<div class="agree_check"><input type="checkbox" name="wr_agree2" id="wr_agree2"> <label for="wr_agree2">본 게시물은 저작권을 침해하거나, 불법적인 내용을 포함하지 않을 것에 동의합니다.</label></div>
					</div>

					</div><!-- //vr_view -->

					<div class="btn_wrap text-r">
						<a href="contents_write.html" class="btn_type02">목록</a>
						<button class="btn_type02 point">등록</button>
						<!-- 수정<button class="btn_type02">수정</button> -->
					</div>

					</fieldset>
				</form>


		<!-- ========================== 내용 종료 ========================= -->
		</section><!-- //section-vr -->
	</div>
	<!-- //container -->
