package com.fedexu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RemoteSpaceController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getUsersView() {
        return "index";
    }
     
}
