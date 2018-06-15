package com.m1kes.korath.service;

import com.jcraft.jsch.JSchException;
import com.m1kes.korath.objects.responses.JsonLogResponse;
import com.m1kes.korath.objects.responses.JsonResponse;
import com.m1kes.korath.utils.ssh.SSHConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.ConnectException;

@Controller
public class SSHService {

    @RequestMapping(value = "/ssh",method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView getResponse() {
        ModelAndView map = new ModelAndView("ssh_service");
        SSHConnection conn = new SSHConnection();
        map.addObject("host",conn.getHost());
        map.addObject("username",conn.getUser());
        map.addObject("password",conn.getPassword());
        return map;
    }


    @RequestMapping(value = "/sshCommand",method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView postCommand(@RequestParam("command") String command , @RequestParam("host") String host,@RequestParam("username") String username,@RequestParam("password") String password) throws IOException, JSchException {

        System.out.println("Posting with Values: ");
        System.out.println("Command : " + command);
        System.out.println("Host : " + host);
        System.out.println("username : "+ username);
        System.out.println("Password : " + password);

        ModelAndView map = new ModelAndView("ssh_service");
        map.addObject("host",host);
        map.addObject("username",username);
        map.addObject("password",password);

        try {
            SSHConnection conn = new SSHConnection(host, username, password);

            map.addObject("response", new JsonLogResponse(conn.executeCommand(command)));

        }catch (ConnectException ex){
            ex.printStackTrace();
            map.addObject("response", new JsonLogResponse(new JsonResponse("error","Timeout to connect to host")));
        }catch (JSchException | IOException ex){
            ex.printStackTrace();
            map.addObject("response", new JsonLogResponse(new JsonResponse("error",ex.getMessage())));
        }


        return map;
    }

}
