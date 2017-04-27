package com.fedexu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fedexu.modelform.AutenticationForm;
import com.fedexu.modelform.FileForm;
import com.fedexu.modelform.LoginForm;
import com.google.gson.Gson;


@Controller
@Scope("session")
public class RemoteSpaceController {

	@Autowired
	private Session session;
	
	@Autowired
	private Gson gson;
	
	//semplice servizio ritornante l'index
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getUsersView() {
        return "index";
    }
	
	@RequestMapping(value = "/dologin", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String doLogin(@RequestBody String form) {
		LoginForm l = gson.fromJson(form, LoginForm.class);
		//fai la mia logica di login
		AutenticationForm aF = new AutenticationForm();
		session.setUsername(l.getUsername());
		session.setAutenticate(true);
		aF.setAutentication(session.isAutenticate());
		
		//ritorno l'oggetto autentication form
		return gson.toJson(aF);
	}
	
	//chiamata per ritornare un file specifico
	@RequestMapping(value = "/getFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity getFile(@RequestBody String form) throws IOException {
		System.out.println(form);
		FileForm f = gson.fromJson(form, FileForm.class);
		System.out.println(f.getName());
		
		File file = new File("D:\\workspace\\RemoteSpace\\WebContent\\WEB-INF\\webapp\\resources\\" + f.getName());		
		
		ResponseEntity respEntity = null;
		byte[] reportBytes = null;
		InputStream inputStream = new FileInputStream(file);
		byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("content-disposition", "attachment; filename=" + f.getName());
		responseHeaders.add("Content-Type", "application/"+f.getExtension());
		respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK);
		
		FileUtils.writeByteArrayToFile(new File("D:\\workspace\\RemoteSpace\\WebContent\\WEB-INF\\webapp\\resources\\filetest.zip"), out);
		

		return respEntity;
	}
     
}
