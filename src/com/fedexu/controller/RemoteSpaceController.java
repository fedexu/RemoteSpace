package com.fedexu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fedexu.modelform.AutenticationForm;
import com.fedexu.modelform.FileForm;
import com.fedexu.modelform.LoginForm;
import com.fedexu.modelform.Path;
import com.google.gson.Gson;


@Controller
@Scope("session")
public class RemoteSpaceController {
	
	private String basePath = "D:\\workspace\\RemoteSpace\\WebContent\\WEB-INF\\webapp\\resources";
	//private String basePath = "/Users/Federico/Documents/workspace/RemoteSpace/WebContent/WEB-INF/webapp/resources";
	
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
		session.setPath(new ArrayList<Path>());
		session.getPath().add(new Path(session.getUsername()));
		session.setAutenticate(true);
		aF.setAutentication(session.isAutenticate());
		
		//ritorno l'oggetto autentication form
		return gson.toJson(aF);
	}
	
	//chiamata per ritornare un file specifico
	@RequestMapping(value = "/getFile", method = RequestMethod.GET, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getFile(@RequestParam(value = "file") String Jfile, HttpServletResponse response) {
		if(!session.isAutenticate()){
			return;
		}
		FileForm file = gson.fromJson(Jfile, FileForm.class);
		InputStream is;
		try {
			int BYTES_DOWNLOAD = 200;
			is = new FileInputStream(new File(this.convertPath(session.getPath()) + file.getName()));
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

			int read = 0;
			byte[] bytes = new byte[BYTES_DOWNLOAD];
			OutputStream os = response.getOutputStream();

			while ((read = is.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/getFileList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getFilesList(@RequestBody String requestPath){
		if(!session.isAutenticate()){
			return "";
		}
		
		Path temp = gson.fromJson(requestPath, Path.class);
		boolean isTheSame = false;
		if(temp.getDir().equals("return"))
			session.getPath().remove(session.getPath().size()-1);
		else{
			for(Path p : session.getPath())
				if(p.getDir().equals(temp.getDir()))
					isTheSame = true;
			if(!isTheSame)
				session.getPath().add(temp);
		}
			
		File folder = new File(this.convertPath(session.getPath()));
		ArrayList<FileForm> out = new ArrayList<FileForm>();
		if(!(session.getPath().size() == 1)){
			FileForm ret = new FileForm();
			ret.setName("return");
			ret.setExtension("");
			ret.setSize("");
			ret.setType("R");
			out.add(ret);
		}
		
		for (File fileEntry : folder.listFiles()) {
	        FileForm f = new FileForm();
	        f.setName(fileEntry.getName());
	        if(!fileEntry.isDirectory()){
		        f.setExtension(FilenameUtils.getExtension(fileEntry.getName()));
		        f.setSize(fileEntry.length() + " MB");
		        f.setType("F");
	        }
	        else{
	        	f.setExtension("");
		        f.setSize("");
		        f.setType("D");
	        }
	        out.add(f);
	    }
		
		return gson.toJson(out);
	}
	
	@RequestMapping(value = "/getFullPath", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getFullPath(){
		if(!session.isAutenticate()){
			return "";
		}
		return gson.toJson(session.getPath());
	}
	
	@RequestMapping(value = "/getCurrentPath", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getCurrentPath(){
		if(!session.isAutenticate()){
			return "";
		}
		return gson.toJson(session.getPath().get(session.getPath().size()-1));
	}
	
	public String convertPath(ArrayList<Path> path){
		String result = this.basePath + "/";
		for(Path p : path)
			result = result + p.getDir()+ "/";
		return result;
	}
	
}
