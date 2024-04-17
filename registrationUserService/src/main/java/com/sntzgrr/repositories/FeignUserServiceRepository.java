package com.sntzgrr.repositories;

import com.sntzgrr.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "userService")
public interface FeignUserServiceRepository {
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    User createUser(@RequestBody User user);
}
