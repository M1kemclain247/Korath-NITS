package com.m1kes.korath.service;

import com.m1kes.korath.NITS_Core;
import com.m1kes.korath.objects.responses.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReloadService {

    @RequestMapping(method = RequestMethod.GET,value = "/reload")
    public @ResponseBody
    JsonResponse reload(){
        NITS_Core.getMain().getDaoManager().onReload();
        return new JsonResponse("success","reload successful");
    }

}
