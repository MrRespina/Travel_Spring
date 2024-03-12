package com.travel.main.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	public abstract SiteUser findByUsername(String username);
	public abstract int insertSiteUser(SiteUser siteUser);
	public abstract int deleteByUsername(String username);
	public abstract int updateByUsername(SiteUser siteUser);
}
