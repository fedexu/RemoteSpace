package com.fedexu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

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
	
	//private String basePath = "D:\\workspace\\RemoteSpace\\WebContent\\WEB-INF\\webapp\\resources\\";
	private String basePath = "/Users/Federico/Documents/workspace/RemoteSpace/WebContent/WEB-INF/webapp/resources";
	
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
		session.getPath().add(new Path(""));
		session.setAutenticate(true);
		aF.setAutentication(session.isAutenticate());
		
		//ritorno l'oggetto autentication form
		return gson.toJson(aF);
	}
	
	//chiamata per ritornare un file specifico
	@RequestMapping(value = "/getFile", method = RequestMethod.GET, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getUgeFile(@RequestParam(value = "file") String Jfile, HttpServletResponse response) {
		System.out.println(session.getUsername());
		if(!session.isAutenticate()){
			return;
		}
		FileForm file = gson.fromJson(Jfile, FileForm.class);
		InputStream is;
		try {
			int BYTES_DOWNLOAD = 200;
			is = new FileInputStream(new File(this.basePath + file.getName()));
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
		System.out.println(requestPath);
		
		System.out.println(this.basePath + requestPath);
		File folder = new File(this.basePath + requestPath);
		ArrayList<FileForm> out = new ArrayList<FileForm>();
		for (File fileEntry : folder.listFiles()) {
	        System.out.println(fileEntry.getName());
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
	
	@RequestMapping(value = "/getUserFilePath", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getUserFilePath(){
		System.out.println(gson.toJson(session.getPath()));
		
		return gson.toJson(session.getPath());
	}
	
}
