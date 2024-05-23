package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService extends IService<User> {
    void saveOrUpdateUser(User domain);

    Integer importData(MultipartFile file) throws IOException;
}
