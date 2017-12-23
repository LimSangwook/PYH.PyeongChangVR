package com.module.parti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonWebUtils;
import com.common.util.PagingUtil;
import com.module.main.service.MainService;
import com.module.parti.dto.PartiCompanyDto;
import com.module.parti.service.PartiCompanyService;

@Controller
@RequestMapping({"/siteManage/**/company*","/forum/**/company*"})
public class PartiCompanyController extends CommonWebUtils{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private PartiCompanyService partiCompanyService;
	
	@Autowired
	private MainService mainService;
	
	/**
	 * 참여업체 등록/수정폼
	 * @param request
	 * @param PartiCompanyDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping("/siteManage/**/companyForm")
	public ModelAndView companyForm(HttpServletRequest request, PartiCompanyDto company) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".companyForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/company/companyForm");
		
		try {
			if(StringUtils.isNotBlank(company.getCompany_key())){
				PartiCompanyDto result = (PartiCompanyDto)partiCompanyService.getCompanyInfo(company.getCompany_key());
				if(result != null){
					result.setAct("update");
					company = (PartiCompanyDto)result.clone();
				} else {
					throw new Exception("ERROR.NO.DATA");
				}
			}
			
			// 이메일주소 도메인 목록
			mav.addObject("emailDomainList", mainService.getCommonCodeList("EMAIL_DOMAIN_CODE"));
			mav.addObject("theForm", company);			
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".companyForm()");
		return mav;
	}
	
	/**
	 * 참여업체 등록/수정/삭제
	 * @param request
	 * @param PartiCompanyDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value="/siteManage/**/companySave", method=RequestMethod.POST)
	public ModelAndView companySave(HttpServletRequest request, PartiCompanyDto company) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".companySave()");
		
		ModelAndView mav = new ModelAndView();
		String returnUrl = "";
		try {
			if(StringUtils.equals("insert", company.getAct())){
				returnUrl = "companyList.do";
				partiCompanyService.insertCompanyInfo(company);
			} else if(StringUtils.equals("update", company.getAct())){
				returnUrl = "companyForm.do?company_key="+company.getCompany_key();
				partiCompanyService.updateCompanyInfo(company);
			} else if(StringUtils.equals("delete", company.getAct())){
				returnUrl = "companyList.do";
				partiCompanyService.deleteCompanyInfo(company);				
			}
			
			redirectView(mav, "", returnUrl);

		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".companySave()");
		return mav;
	}
	
	/**
	 * 참여업체 목록
	 * @param request
	 * @param PartiCompanyDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView companyList(HttpServletRequest request, PartiCompanyDto company) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".companyList()");
		
		ModelAndView mav = new ModelAndView();
		
		try {			
			
			String siteGubun = (String)request.getAttribute("siteGubun");
			if(StringUtils.equals("siteManage", siteGubun)){
				// 전체 업체조회
				company.setSearch_type("1");
			} else {
				// 사용 업체 조회
				company.setSearch_type("2");
			}			
			mav.addObject("result", partiCompanyService.getCompanyList(company));
			mav.addObject("pageNavigation", PagingUtil.printPageNavi(company, getParameter(request,"companyList.do?","company_key|page")));
			mav.addObject("theForm", company);			
			mav.setViewName(siteGubun+"/company/companyList");
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".companyList()");
		return mav;
	}
	
	/**
	 * 참여업체 상세보기
	 * @param request
	 * @param PartiCompanyDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView companyDetail(HttpServletRequest request, PartiCompanyDto company) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".companyDetail()");
		
		ModelAndView mav = new ModelAndView("forum/company/companyDetail");
		
		try {
			
			PartiCompanyDto result = (PartiCompanyDto)partiCompanyService.getCompanyInfo(company.getCompany_key());			
			if(result == null )throw new Exception("ERROR.NO.DATA");				
			mav.addObject("result", result);
			
		} catch (Exception e) {
			if(log.isDebugEnabled())log.debug(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".companyDetail()");
		return mav;
	}
}
