package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.domain.User;
import com.ruoyi.poor.mapper.UserMapper;
import com.ruoyi.poor.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void saveOrUpdateUser(User entity) {
        if (entity.getId() == null) {
            entity.setId(IdUtil.getSnowflakeNextId());
        }
        this.saveOrUpdate(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer importData(MultipartFile file) throws IOException {
        List<User> dataList = EasyExcel.read(file.getInputStream()).head(User.class).headRowNumber(1).sheet(0).doReadSync();
        AtomicInteger i = new AtomicInteger();
        for (User user : dataList) {
            /**
             * 验证身份证号是否重复
             */
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("card_id", user.getCardId());
            Long count = this.baseMapper.selectCount(queryWrapper);
            if (count > 0) {
                //重复的更新
                this.update(user, new UpdateWrapper<User>().eq("card_id", user.getCardId()));
            } else {
                i.getAndIncrement();
                user.setId(IdUtil.getSnowflakeNextId());
                this.saveOrUpdate(user);
            }
        }

        return i.get();
    }

}
