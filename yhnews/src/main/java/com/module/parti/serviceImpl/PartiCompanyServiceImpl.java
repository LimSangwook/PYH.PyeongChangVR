package com.module.parti.serviceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.common.util.JProperties;
import com.common.util.StringEncrypter;
import com.module.parti.dto.PartiCompanyDto;
import com.module.parti.service.PartiCompanyService;

@Service("partiCompanyService")
public class PartiCompanyServiceImpl implements PartiCompanyService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void insertCompanyInfo(PartiCompanyDto company) throws Exception {
		setEncrypt(company);
		commonDao.insert("COMPANY.insertCompanyInfo", company);		
	}
	
	@Override
	public void updateCompanyInfo(PartiCompanyDto company) throws Exception {
		setEncrypt(company);
		commonDao.update("COMPANY.updateCompanyInfo", company);		
	}
	
	@Override
	public void deleteCompanyInfo(PartiCompanyDto company) throws Exception {
		commonDao.update("COMPANY.deleteCompanyInfo", company);		
	}
	
	@Override
	public PartiCompanyDto getCompanyInfo(String companyKey) throws Exception {
		PartiCompanyDto result = (PartiCompanyDto)commonDao.queryForObject("COMPANY.getCompanyInfo", companyKey); 
		setDecrypt(result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PartiCompanyDto> getCompanyList(PartiCompanyDto company) throws Exception {
		List<PartiCompanyDto> result = null;
		int totalCount = (Integer)commonDao.queryForObject("COMPANY.getCompanyTotalCount",company);
		if(totalCount > 0){
			company.setTotal_count(totalCount);			
			result = (List<PartiCompanyDto>)commonDao.queryForObjectList("COMPANY.getCompanyList", company);
		}
		return result;
	}
	
	// 정보 암호화
	private void setEncrypt(PartiCompanyDto company) throws Exception {
		StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));
		
		if(StringUtils.isNotBlank(company.getMaster_phone1()) && StringUtils.isNotBlank(company.getMaster_phone2()) && StringUtils.isNotBlank(company.getMaster_phone3())){
			company.setMaster_phone(company.getMaster_phone1()+"-"+company.getMaster_phone2()+"-"+company.getMaster_phone3());
		}
		if(StringUtils.isNotBlank(company.getMaster_email_id()) && StringUtils.isNotBlank(company.getEmail_domain())){
			company.setMaster_email(company.getMaster_email_id()+"@"+company.getEmail_domain());
		}
		if(StringUtils.isNotBlank(company.getFax1()) && StringUtils.isNotBlank(company.getFax2()) && StringUtils.isNotBlank(company.getFax3())){
			company.setFax(company.getFax1()+"-"+company.getFax2()+"-"+company.getFax3());
		}
		
		// 전화번호
		if(StringUtils.isNotBlank(company.getPhone1()) && StringUtils.isNotBlank(company.getPhone2()) && StringUtils.isNotBlank(company.getPhone3())){
			company.setPhone(se.encrypt(company.getPhone1()+"-"+company.getPhone2()+"-"+company.getPhone3()));
		}
		// 이메일주소
		if(StringUtils.isNotBlank(company.getEmail_id()) && StringUtils.isNotBlank(company.getEmail_domain())){
			company.setEmail(se.encrypt(company.getEmail_id()+"@"+company.getEmail_domain()));
		}
	}
	
	// 정보 복호화
	private void setDecrypt(PartiCompanyDto company) throws Exception {
		if(company != null){
			StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));
			// 전화번호
			if(StringUtils.isNotBlank(company.getPhone())){
				company.setPhone(se.decrypt(company.getPhone()));
			}
			// 이메일주소
			if(StringUtils.isNotBlank(company.getEmail())){
				company.setEmail(se.decrypt(company.getEmail()));
			}
		}		
	}
}
