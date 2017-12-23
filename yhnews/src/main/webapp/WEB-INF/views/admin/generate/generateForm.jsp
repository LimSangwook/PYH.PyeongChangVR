<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


					<div class="bbs_wrap">
						<fieldset>
						<legend>글쓰기</legend>
						<form name="genForm" action="saveGenerate.do" method="post">

							<table class="table_form">
								<caption>글쓰기</caption>
								<colgroup>
									<col style="width:15%;" />
									<col style="width:35%;" />
									<col style="width:15%;" />
									<col style="width:35%;" />
								</colgroup>
								<tbody>
									<tr>
										<th>1월</th>
										<td>
											<input type="text" name="month1" class="col30" title="입력" value="${generateData.month1}">
											<input type="text" name="month1_cal" class="col30" title="입력" value="${generateData.month1_cal}">
											<input type="text" name="month1_1" class="col30" title="입력" value="${generateData.month1_1}">
										</td>
										<th>7월</th>
										<td>
											<input type="text" name="month7" class="col30" title="입력" value="${generateData.month7}">
											<input type="text" name="month7_cal" class="col30" title="입력" value="${generateData.month7_cal}">
											<input type="text" name="month7_1" class="col30" title="입력" value="${generateData.month7_1}">
										</td>
									</tr>
									<tr>
										<th>2월</th>
										<td>
											<input type="text" name="month2" class="col30" title="입력" value="${generateData.month2}">
											<input type="text" name="month2_cal" class="col30" title="입력" value="${generateData.month2_cal}">
											<input type="text" name="month2_1" class="col30" title="입력" value="${generateData.month2_1}">
										</td>
										<th>8월</th>
										<td>
											<input type="text" name="month8" class="col30" title="입력" value="${generateData.month8}">
											<input type="text" name="month8_cal" class="col30" title="입력" value="${generateData.month8_cal}">
											<input type="text" name="month8_1" class="col30" title="입력" value="${generateData.month8_1}">
										</td>
									</tr>
									<tr>
										<th>3월</th>
										<td>
											<input type="text" name="month3" class="col30" title="입력" value="${generateData.month3}">
											<input type="text" name="month3_cal" class="col30" title="입력" value="${generateData.month3_cal}">
											<input type="text" name="month3_1" class="col30" title="입력" value="${generateData.month3_1}">
										</td>
										<th>9월</th>
										<td>
											<input type="text" name="month9" class="col30" title="입력" value="${generateData.month9}">
											<input type="text" name="month9_cal" class="col30" title="입력" value="${generateData.month9_cal}">
											<input type="text" name="month9_1" class="col30" title="입력" value="${generateData.month9_1}">
										</td>
									</tr>
									<tr>
										<th>4월</th>
										<td>
											<input type="text" name="month4" class="col30" title="입력" value="${generateData.month4}">
											<input type="text" name="month4_cal" class="col30" title="입력" value="${generateData.month4_cal}">
											<input type="text" name="month4_1" class="col30" title="입력" value="${generateData.month4_1}">
										</td>
										<th>10월</th>
										<td>
											<input type="text" name="month10" class="col30" title="입력" value="${generateData.month10}">
											<input type="text" name="month10_cal" class="col30" title="입력" value="${generateData.month10_cal}">
											<input type="text" name="month10_1" class="col30" title="입력" value="${generateData.month10_1}">
										</td>
									</tr>
									<tr>
										<th>5월</th>
										<td>
											<input type="text" name="month5" class="col30" title="입력" value="${generateData.month5}">
											<input type="text" name="month5_cal" class="col30" title="입력" value="${generateData.month5_cal}">
											<input type="text" name="month5_1" class="col30" title="입력" value="${generateData.month5_1}">
										</td>
										<th>11월</th>
										<td>
											<input type="text" name="month11" class="col30" title="입력" value="${generateData.month11}">
											<input type="text" name="month11_cal" class="col30" title="입력" value="${generateData.month11_cal}">
											<input type="text" name="month11_1" class="col30" title="입력" value="${generateData.month11_1}">
										</td>
									</tr>
									<tr>
										<th>6월</th>
										<td>
											<input type="text" name="month6" class="col30" title="입력" value="${generateData.month6}">
											<input type="text" name="month6_cal" class="col30" title="입력" value="${generateData.month6_cal}">
											<input type="text" name="month6_1" class="col30" title="입력" value="${generateData.month6_1}">
										</td>
										<th>12월</th>
										<td>
											<input type="text" name="month12" class="col30" title="입력" value="${generateData.month12}">
											<input type="text" name="month12_cal" class="col30" title="입력" value="${generateData.month12_cal}">
											<input type="text" name="month12_1" class="col30" title="입력" value="${generateData.month12_1}">
										</td>
									</tr>
								</tbody>
							</table>

						</form>
						</fieldset>

						<div class="btn_wrap float_right">
							<a href="#" class="btn primary" onclick="javascript:genSave();">등록</a>
						</div>

					</div><!-- //bbs_wrap -->
					
					<script type="text/javascript">
						function genSave(){
							if(confirm("수정 하시겠습니까?"))
								document.genForm.submit();
						}
					</script>
