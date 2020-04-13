package com.staff.zuul.feign;

import com.staff.common.request.CheckLoginRequest;
import com.staff.common.response.CheckLoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("server")
public interface ServerControllerFeign {


    @RequestMapping(value = "/private/system/checkLogin", consumes = {"application/json"}, produces = {"application/json"},
            method = RequestMethod.POST)
    CheckLoginResponse checkLogin(@RequestBody CheckLoginRequest request);
}
