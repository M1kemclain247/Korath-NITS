package com.m1kes.korath.service;

import com.m1kes.korath.NITS_Core;
import com.m1kes.korath.objects.Host;
import com.m1kes.korath.objects.responses.JsonResponse;
import com.m1kes.korath.storage.mysql.core.NITS_AsyncQuery;
import com.m1kes.korath.storage.mysql.core.NITS_QueryCallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
public class AddHostService {

    @RequestMapping(method = RequestMethod.POST, value = "/AddHost")
    public @ResponseBody JsonResponse addHost(@RequestBody Host host){

        NITS_AsyncQuery query = null;
        try {
            query = NITS_Core.getMain().getMysqlManager().getAsyncQuery("nits_config","INSERT INTO hosts (name,ip,category_id,img_path) VALUES (?,?,?,?)");
            query.setValue(1,host.getName());
            query.setValue(2,host.getIp());
            query.setValue(3,host.getCategory_id());
            query.setValue(4,host.getImg_path());

            query.update(new NITS_QueryCallback<Integer>() {
                @Override
                public void onSuccess(Integer response) throws SQLException {
                    System.out.println("Successfully added a new Host!");
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new JsonResponse("Success","Successfully added a host");
    }


    @RequestMapping(method = RequestMethod.POST, value = "/AddHosts")
    public @ResponseBody JsonResponse addHosts(@RequestBody Host[] hosts){

        NITS_AsyncQuery query = null;
        try {

            for(Host host : hosts){

                query = NITS_Core.getMain().getMysqlManager().getAsyncQuery("nits_config","INSERT INTO hosts (name,ip,category_id,img_path) VALUES (?,?,?,?)");
                query.setValue(1,host.getName());
                query.setValue(2,host.getIp());
                query.setValue(3,host.getCategory_id());
                query.setValue(4,host.getImg_path());

                query.update(new NITS_QueryCallback<Integer>() {
                    @Override
                    public void onSuccess(Integer response) throws SQLException {
                        System.out.println("Successfully added a new Host!");
                    }
                });

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new JsonResponse("success","all hosts are now being added!");
    }

}
