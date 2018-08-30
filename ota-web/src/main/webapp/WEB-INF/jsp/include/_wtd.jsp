<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 委托单 -->
<div class="M_8" >
	<div class="M_8t" id="weituodanDIV">
		<div class="M_8top">
			<img
				src="${mystatic}/${inspection.orgLogoUrl}" />
			<ul style="margin-bottom:0px;">
				<li><p>服务机构：【${inspection.org_name }】</p></li>
				<li><p>报检编号：【${inspection.showId }】</p></li>
				<li><p style="margin-bottom:0px;">报检会员：【${inspection.member_name}】</p></li>
			</ul>
		</div>
		<div class="M_8tmain">

			<ul>
				<h1 style="margin-top:0px;">委托检测测试协议书</h1>
				<li><p
						style="font-family: 微软雅黑;font-size: 16px;color: #e00000;">请把此委托单打印出来，签字盖章，然后和样品一一对应快递至：</p></li>
				<!-- 新版固定 -->
				<c:if test="${inspection.orgExpressName!=null }">
					<li><p>【${inspection.orgExpressName }】-【${inspection.orgExpressAddress }】-
						【${inspection.orgExpressTel }】</p></li>
				</c:if>
				<c:if test="${inspection.orgExpressName==null }">
				<li><p>【${expressArea.provinceName }${expressArea.cityName}${expressArea.districtName}${orgExpress.address }】【${orgExpress.recipient }】
						【${orgExpress.tel }】</p></li>
				</c:if>
			</ul>
			<div class="img">
				<div id="qrcode">
				</div>
			</div>
		</div>
		<div class="M_8tbot">
			<h6>委托方信息</h6>
			<table>
				<tr>
					<td class="M_l">委托方</td>
					<td class="M_2">${inspection.clientName}</td>
					<td class="M_l">联系人</td>
					<td class="M_2">${inspection.linkName}</td>
				</tr>
				<tr>
					<td class="M_l">委托方地址</td>
					<td class="M_2">${inspection.clientAddress}</td>
					<td class="M_l">联系电话</td>
					<td class="M_2">${inspection.linkTel}</td>
				</tr>
				<tr>
					<td class="M_l">店铺名称</td>
					<td class="M_2">${inspection.linkShop}</td>
					<td class="M_l">联系qq</td>
					<td class="M_2">${inspection.linkQq}</td>
				</tr>
				<tr>
					<td class="M_l">生产单位</td>
					<td class="M_2">${inspection.clientManufacturerName}</td>
					<td class="M_l">联系人email</td>
					<td class="M_2">${inspection.linkEmail}</td>
				</tr>
			</table>
			<h6>样品信息</h6>
			<table>
				<tr>
					<td class="M_l">样品名称</td>
					<td class="M_2">${inspection.sampleName }</td>
					<td class="M_l">样品数量</td>
					<td class="M_2">${inspection.miscSampleNum }</td>
				</tr>
				<tr>
					<td class="M_l">样品描述</td>
					<td class="M_2">${inspection.sampleDescription }</td>
					<td class="M_l">商标品牌</td>
					<td class="M_2">${inspection.sampleBrand }</td>
				</tr>
				<tr>
					<td class="M_l">产品链接</td>
					<td class="p1 M_2">${inspection.miscProductLind=='http://www.ajianla.com/jiance.html?id=-1'?'/':inspection.miscProductLind }</td>
					<td class="M_l">规格型号</td>
					<td class="M_2">${inspection.sampleSpecification }</td>
				</tr>
			</table>
			<h6>检测项目</h6>
			<table>
				<tr>
					<td class="M_l">检测项目</td>
					<td colspan="3" style="text-align:left;padding-left:10px;">
						<c:forEach var="orgitem" items="${inspection.orgitemList }">
							${orgitem.item_name_cn },${orgitem.standard_number },${orgitem.tag }<br/>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="M_l">判定依据</td>
					<td colspan="3" style="text-align:left;padding-left:10px;">${inspection.pdyj==null?"需要判定":inspection.pdyj }</td>
				</tr>
			</table>
			<h6>辅助信息</h6>
			<table>
				<tr>
					<td class="M_l">报告版本</td>
					<td class="M_2">${inspection.reportLang}</td>
					<td class="M_l">应用场景</td>
					<td class="M_2">${inspection.sceneAppliedName }</td>
				</tr>
				<tr>
					<td class="M_l">报告介质</td>
					<td class="M_2">${inspection.reportMedium }</td>
					<td class="M_l">检测周期</td>
					<td class="M_2">${inspection.reportPeriod }</td>
				</tr>
				<tr>
					<td class="M_l">检测费/实付金额</td>
					<td class="M_2">${inspection.testFee}/${inspection.realFee}</td>
					<td class="M_l">业务来源</td>
					<td class="M_2">${inspection.usageName }</td>
				</tr>
				<tr>
					<td class="M_l">发票要求</td>
					<td class="M_2">${inspection.invoiceRequire}</td>
					<td class="M_l">余样处理</td>
					<td class="M_2">${inspection.remainderHandleType }</td>
				</tr>
				<tr>
					<td class="M_l">特殊要求备注</td>
					<td class="M_2" colspan="3"></td>
				</tr>

			</table>
			<h6>签字盖章</h6>
			<table style=" margin-bottom:10px;">
				<tr>
					<td class="M_l">委托经办人签字</td>
					<td class="M_2"></td>
					<td class="M_l">签字日期</td>
					<td class="M_2"></td>
				</tr>
				<tr>
					<td class="M_l">检测机构签收人</td>
					<td class="M_2"></td>
					<td class="M_l">签字日期</td>
					<td class="M_2"></td>
				</tr>
				<tr>
					<td class="M_l">实验室签收人</td>
					<td class="M_2"></td>
					<td class="M_l">签字日期</td>
					<td class="M_2"></td>
				</tr>
			</table>
		</div>
		<div class="row">
		  <div class="col-md-12">
		  	<p>
				声明：<br/>
				1.检测机构仅对来样负责，检测结果仅反映对该样品的评价，检测结果的使用、使用所产生的直接或间接损失，检测机构不承担任何责任。如因检测机构过错导致样品的检测结果有误差，检测机构对此检测结果的误差及检测结果的使用承担不超过此样品此检测项目的二倍检测费用金额范围内的赔偿等法律责任。<br/>
				2.委托方声明：对样品及相关信息资料的真实性负责，并同意委托方和检测机构的“约定条款”。
			</p>
		  </div>
		</div>
	</div>
</div>