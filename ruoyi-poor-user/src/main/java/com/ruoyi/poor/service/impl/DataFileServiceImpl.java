package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.domain.Subsidy;
import com.ruoyi.poor.mapper.DataFileMapper;
import com.ruoyi.poor.service.DataFileService;
import com.ruoyi.poor.service.SubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataFileServiceImpl extends ServiceImpl<DataFileMapper, DataFile> implements DataFileService {
    @Autowired
    private SubsidyService subsidyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndImport(DataFile dataFile) {
        //保存基本信息
        Long fileId = IdUtil.getSnowflakeNextId();

        String path = dataFile.getFilePath().replaceFirst("/profile/upload", "");
        //写入文件数据
        List<Subsidy> dataList = EasyExcel.read(RuoYiConfig.getUploadPath() + path).head(Subsidy.class).headRowNumber(1).sheet(dataFile.getSheetName()).doReadSync();
        dataList.forEach(data -> {
            data.setId(IdUtil.getSnowflakeNextId());
            data.setSubsidyType(dataFile.getSubsidyType());
            data.setSubsidyDate(dataFile.getSubsidyDate());
            data.setDataFileId(fileId);
            subsidyService.save(data);
        });

        //保存基本信息
        dataFile.setId(fileId);
        dataFile.setDataCount(dataList.size());
        this.save(dataFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delFileAndRecord(Long id) {
        //删除补贴数据
        QueryWrapper<Subsidy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("data_file_id", id);
        subsidyService.remove(queryWrapper);

        this.removeById(id);
    }
}
