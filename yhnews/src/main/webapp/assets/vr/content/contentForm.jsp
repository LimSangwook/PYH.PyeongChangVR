<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/comm/taglib.jsp"%>

			<h4 class="tit-h4">기본정보</h4>
			<table class="table_form">
				<caption>테이블</caption>
				<colgroup>
					<col style="width:15%">
					<col style="width:35%">
					<col style="width:15%">
					<col style="width:35%">
				</colgroup>
				<tbody>
					<tr>
						<th>경기장</th>
						<td colspan="3">
							<select>
							<c:forEach var="data" items="${siteList}" varStatus="i">
								<option value='${data.vr_site_id}' <c:if test="${data.vr_site_id eq theForm.vr_site_id}"><c:out value="selected"/></c:if>>${data.title}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>VR ID</th>
						<td colspan="3">
							<input type="text" name="std_name" class="wid100" title="제목 입력" value="${theForm.vr_site_content_id}">
							<a href="#" class="btn btn_small" >중복검사</a>
						</td>
					</tr>
					<tr>
						<th>VR 제목</th>
						<td><input type="text" name="std_name" class="col100" title="제목 입력" value="${theForm.name}"></td>
						<th>노출 순서</th>
						<td>
							<select>
								<option>1</option>
								<option>2</option>
								<option>3</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>그룹 ID</th>
						<td>
							<select>
								<c:forEach var="data" items="${groupList}" varStatus="i">
									<option value="${data.vr_site_group_id}" <c:if test="${data.vr_site_group_id eq theForm.vr_site_group_id}"><c:out value="selected"/></c:if>> ${data.name}</option>
								</c:forEach>
							</select>
						</td>
						<th>배경음악</th>
						<td>
							<select>
							<c:forEach var="data" items="${bgMusicList}" varStatus="i">
									<option value="${data.vr_style_music_id}" <c:if test="${data.vr_style_music_id eq theForm.vr_style_music_id}"><c:out value="selected"/></c:if>> ${data.title}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>파노라마 이미지</th>
						<td colspan="3"><input type="file" name="std_name" class="" title="제목 입력"></td>
					</tr>
				</tbody>
			</table>
			
			<!-- 스팟정보는 최초등록시 보이지 않고 수정모드일때 보여줌. -->
			<div class="section">
				<div class="clearfix com-pt40">
					<h4 class="tit-h4 float_left">스팟정보</h4>
					<div class="float_right plusbtn_wrap">
						<a href="javascript:appendRow('spot');" class="ico ico_plus">추가</a>
						<a href="javascript:deleteRow('spot');" class="ico ico_minus">삭제</a>
					</div>
				</div>
				<table class="table_basic " id="spot">
					<caption>테이블</caption>
					<colgroup>
						<col style="width:8%;" />
						<col style="width:auto;" />
						<col style="width:30%;" />
						<col style="width:20%;" />
						<col style="width:20%;" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">명칭</th>
							<th scope="col">아이콘스타일</th>
							<th scope="col">위치</th>
							<th scope="col">링크</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><input type="text" name="spot_1_1" class="col100"></td>
							<td>
								<select name="spot_1_2"><option>링크</option><option>사진</option><option>동영상</option></select>
								<select name="spot_1_3"><option>link1</option></select>
							</td>
							<td><input type="text" name="spot_1_4" placeholder="x 좌표" class="wid50">&nbsp;&nbsp;<input type="text" name="" placeholder="y 좌표" class="wid50"></td>
							<td>
								<select name="spot_1_5"><option>경기장정문</option></select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="btn_wrap textAlign_right">
				<a href="club.html" class="btn ">목록</a>
				<button type="submit" class="btn point">저장</button>
			</div>

			<script>
				function appendRow(val) {
					var table_id = $("#"+val);
					var count = table_id.find('tbody tr').length +1;

					if (val == "spot"){
						var html = "<tr>";
							  html += "<td>"+count+"</td>";
							  html += "<td><input type='text' name='"+val+"_"+count+"_1' class='col100'></td>";
							  html += "<td>";
							  html += "<select name='"+val+"_"+count+"_2'><option>링크</option><option>사진</option><option>동영상</option></select>";
							  html += "<select name='"+val+"_"+count+"_3'><option>link1</option></select>";
							  html += "</td>";
							  html += "<td><input type='text' name='"+val+"_"+count+"_4' placeholder='x 좌표' class='wid50'>&nbsp;&nbsp;<input type='text' name='' placeholder='y 좌표' class='wid50'></td>";
							  html += "<td><select name='"+val+"_"+count+"_5'><option>경기장정문</option></select></td>";
							  html += "</tr>";

					};

					table_id.append(html);
					table_id.find('tr').removeClass('active');
				}
				function deleteRow(val) {
					var table_id = $("#"+val + " tr:last");
					table_id.remove();
				}
			</script>

