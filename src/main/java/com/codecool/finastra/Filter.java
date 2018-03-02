package com.codecool.finastra;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.apache.juli.logging.Log;

public class Filter extends HttpHeaderSecurityFilter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.doFilter(arg0, arg1, arg2);
	}

	@Override
	public String getAntiClickJackingOption() {
		// TODO Auto-generated method stub
		return super.getAntiClickJackingOption();
	}

	@Override
	public String getAntiClickJackingUri() {
		// TODO Auto-generated method stub
		return super.getAntiClickJackingUri();
	}

	@Override
	public int getHstsMaxAgeSeconds() {
		// TODO Auto-generated method stub
		return super.getHstsMaxAgeSeconds();
	}

	@Override
	protected Log getLogger() {
		// TODO Auto-generated method stub
		return super.getLogger();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		super.init(filterConfig);
	}

	@Override
	public boolean isAntiClickJackingEnabled() {
		// TODO Auto-generated method stub
		return super.isAntiClickJackingEnabled();
	}

	@Override
	public boolean isBlockContentTypeSniffingEnabled() {
		// TODO Auto-generated method stub
		return super.isBlockContentTypeSniffingEnabled();
	}

	@Override
	protected boolean isConfigProblemFatal() {
		// TODO Auto-generated method stub
		return super.isConfigProblemFatal();
	}

	@Override
	public boolean isHstsEnabled() {
		// TODO Auto-generated method stub
		return super.isHstsEnabled();
	}

	@Override
	public boolean isHstsIncludeSubDomains() {
		// TODO Auto-generated method stub
		return super.isHstsIncludeSubDomains();
	}

	@Override
	public boolean isHstsPreload() {
		// TODO Auto-generated method stub
		return super.isHstsPreload();
	}

	@Override
	public boolean isXssProtectionEnabled() {
		// TODO Auto-generated method stub
		return super.isXssProtectionEnabled();
	}

	@Override
	public void setAntiClickJackingEnabled(boolean antiClickJackingEnabled) {
		// TODO Auto-generated method stub
		super.setAntiClickJackingEnabled(antiClickJackingEnabled);
	}

	@Override
	public void setAntiClickJackingOption(String arg0) {
		// TODO Auto-generated method stub
		super.setAntiClickJackingOption(arg0);
	}

	@Override
	public void setAntiClickJackingUri(String arg0) {
		// TODO Auto-generated method stub
		super.setAntiClickJackingUri(arg0);
	}

	@Override
	public void setBlockContentTypeSniffingEnabled(boolean blockContentTypeSniffingEnabled) {
		// TODO Auto-generated method stub
		super.setBlockContentTypeSniffingEnabled(blockContentTypeSniffingEnabled);
	}

	@Override
	public void setHstsEnabled(boolean hstsEnabled) {
		// TODO Auto-generated method stub
		super.setHstsEnabled(hstsEnabled);
	}

	@Override
	public void setHstsIncludeSubDomains(boolean hstsIncludeSubDomains) {
		// TODO Auto-generated method stub
		super.setHstsIncludeSubDomains(hstsIncludeSubDomains);
	}

	@Override
	public void setHstsMaxAgeSeconds(int hstsMaxAgeSeconds) {
		// TODO Auto-generated method stub
		super.setHstsMaxAgeSeconds(hstsMaxAgeSeconds);
	}

	@Override
	public void setHstsPreload(boolean hstsPreload) {
		// TODO Auto-generated method stub
		super.setHstsPreload(hstsPreload);
	}

	@Override
	public void setXssProtectionEnabled(boolean xssProtectionEnabled) {
		// TODO Auto-generated method stub
		super.setXssProtectionEnabled(xssProtectionEnabled);
	}
	
	

}
