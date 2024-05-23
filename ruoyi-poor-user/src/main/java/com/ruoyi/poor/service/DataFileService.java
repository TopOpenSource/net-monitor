package com.ruoyi.poor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.poor.domain.DataFile;

public interface DataFileService extends IService<DataFile> {
    void saveAndImport(DataFile dataFile);

    /**
     * 删除文件及数据
     * @param id
     */
    void delFileAndRecord(Long id);
}
