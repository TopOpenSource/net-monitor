package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.domain.User;

public interface UserService extends IService<User> {
    void saveOrUpdateUser(User domain);
}
