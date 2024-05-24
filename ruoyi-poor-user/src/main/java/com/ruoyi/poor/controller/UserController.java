package com.ruoyi.poor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.domain.User;
import com.ruoyi.poor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/poor/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;


    @GetMapping("info/{id}")
    public User list(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/list")
    public TableDataInfo list(User dto) {
        startPage();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(dto.getLive())){
            queryWrapper.eq("live",dto.getLive());
        }

        if(StringUtils.isNotEmpty(dto.getDisability())) {
            queryWrapper.eq("disability", dto.getDisability());
        }

        if(StringUtils.isNotEmpty(dto.getCardId())) {
            queryWrapper.like("card_id","%"+dto.getCardId()+"%");
        }

        if(StringUtils.isNotEmpty(dto.getName())) {
            queryWrapper.like("name","%"+dto.getName()+"%");
        }

        List<User> list = userService.list(queryWrapper);

        return getDataTable(list);
    }


    @PostMapping("saveOrUpdate")
    public AjaxResult saveOrUpdate(@RequestBody User domain) {
        userService.saveOrUpdateUser(domain);
        return AjaxResult.success(0);
    }


    @DeleteMapping("del/{id}")
    public AjaxResult del(@PathVariable("id") Long id) {
        userService.removeById(id);
        return AjaxResult.success(0);
    }


    /**
     * 导入用户信息
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("importFile")
    public AjaxResult importFile(MultipartFile file) throws IOException {
        return AjaxResult.success(userService.importData(file));
    }

    /**
     * 查询用户
     * @return
     */
    @GetMapping("/listAll")
    public List<User> list() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","card_id","name");
        return userService.list(queryWrapper);
    }
}
