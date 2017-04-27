package com.fedexu.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Session {

	private String username;
	private boolean isAutenticate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAutenticate() {
		return isAutenticate;
	}

	public void setAutenticate(boolean isAutenticate) {
		this.isAutenticate = isAutenticate;
	}

	
}
