package com.tit.mapper;

import com.tit.model.NaverVO;

public interface NaverMapper {
	public void naverlogin(NaverVO naverVO);
	public int checkEmail(String email);
	public String checkSns(NaverVO naverVO);
	public void naverlogout(NaverVO naverVO);
}
