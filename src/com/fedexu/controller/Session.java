package com.fedexu.controller;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.fedexu.modelform.Path;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class Session {

	private String username;
	private boolean isAutenticate;
	private ArrayList<Path> path;

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

	public ArrayList<Path> getPath() {
		return path;
	}

	public void setPath(ArrayList<Path> path) {
		this.path = path;
	}

	

	
	
}
