package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.controller.LandController;
import com.ruoyi.poor.convert.BuildConvert;
import com.ruoyi.poor.convert.LandConvert;
import com.ruoyi.poor.domain.Land;
import com.ruoyi.poor.dto.LandDto;
import com.ruoyi.poor.mapper.LandMapper;
import com.ruoyi.poor.service.ILandService;
import com.ruoyi.system.domain.SysPubFile;
import com.ruoyi.system.service.ISysPubFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LandServiceImpl extends ServiceImpl<LandMapper, Land> implements ILandService {
    public  static String  IMG_REF_TYPE="land_image";

    @Autowired
    private ISysPubFileService sysPubFileService;
    
    @Override
    public LandDto getLandByVillageId(Long villageId) {
        QueryWrapper<Land> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("village_id",villageId);
        Land land = this.getOne(queryWrapper);

        if(land!=null){
            //图片
            LandDto landDto = LandConvert.INSTANCE.convert2Dto(land);

            QueryWrapper<SysPubFile> queryFileWrapper = new QueryWrapper<>();
            queryFileWrapper.eq("ref_id",landDto.getId());
            queryFileWrapper.eq("ref_type",IMG_REF_TYPE);
            List<SysPubFile> fileList = this.sysPubFileService.list(queryFileWrapper);
            List<Long> fileIds = fileList.stream().map(SysPubFile::getId).collect(Collectors.toList());

            landDto.setImageIds(fileIds);
            return landDto;
        }
        return null;
    }

    @Override
    public Long saveOrUpdateLand(LandDto dto) {

        long id;
        if(dto.getId()==null){
            id = IdUtil.getSnowflakeNextId();
        }else{
            id=dto.getId();
        }

        dto.setId(id);
        this.saveOrUpdate(LandConvert.INSTANCE.convert2Entity(dto));

        //图片关系
        UpdateWrapper<SysPubFile> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("ref_type",null).set("ref_id",null);
        updateWrapper.eq("ref_id",id).eq("ref_type",IMG_REF_TYPE);
        this.sysPubFileService.update(updateWrapper);

        if(dto.getImageIds()!=null && dto.getImageIds().size()>0){
            UpdateWrapper<SysPubFile> updateWrapper2 = new UpdateWrapper<>();
            updateWrapper2.set("ref_type",IMG_REF_TYPE);
            updateWrapper2.set("ref_id",id);
            updateWrapper2.in("id",dto.getImageIds());
            this.sysPubFileService.update(updateWrapper2);
        }
        return id;
    }
}
