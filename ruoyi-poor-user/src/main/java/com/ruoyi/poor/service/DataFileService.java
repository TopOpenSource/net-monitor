package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.DataFile;

public interface DataFileService extends IService<DataFile> {
    void saveAndImport(DataFile dataFile);
}
