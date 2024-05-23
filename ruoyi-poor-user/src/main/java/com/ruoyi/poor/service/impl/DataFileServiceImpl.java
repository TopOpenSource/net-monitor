package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        Long fileId=IdUtil.getSnowflakeNextId();
        dataFile.setId(fileId);
        this.save(dataFile);

        //写入文件数据
        List<Subsidy> dataList = EasyExcel.read("").head(Subsidy.class).sheet(dataFile.getSheetName()).headRowNumber(dataFile.getDataIndex()).doReadSync();
        dataList.forEach(data->{
            data.setId(IdUtil.getSnowflakeNextId());
            data.setSubsidyType(dataFile.getSubsidyType());
            data.setYearMonth(dataFile.getYearMonth());
            data.setDataFileId(fileId);
            subsidyService.save(data);
        });
    }
}
