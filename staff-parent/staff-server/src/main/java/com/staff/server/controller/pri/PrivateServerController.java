package com.staff.server.controller.pri;

import com.staff.common.request.CheckLoginRequest;
import com.staff.common.response.CheckLoginResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/private/system")
public class PrivateServerController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/hello", consumes = {"application/json"}, produces = {"application/json"},
            method = RequestMethod.POST)
    @ApiOperation(value = "hello", notes = "hello", response = String.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "成功", response = String.class)})
    public String hello() {
       return "private hello";
    }

    @RequestMapping(value = "/checkLogin", consumes = {"application/json"}, produces = {"application/json"},
                    method = RequestMethod.POST)
    @ApiOperation(value = "检查是否登录", notes = "检查是否登录", response = CheckLoginResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "成功", response = CheckLoginResponse.class)})
    public CheckLoginResponse checkLogin(@RequestBody CheckLoginRequest request) {
        CheckLoginResponse response = new CheckLoginResponse();
        String sessionId = redisTemplate.opsForValue().get(request.getCount());
        if (request.getSessionId().equals(sessionId)) {
            response.setIsLogin("1");
            // 刷新用户缓存
            redisTemplate.opsForValue().set(request.getCount(), sessionId, 30, TimeUnit.MINUTES);
        } else {
            response.setIsLogin("0");
        }
        return response;
    }
}
