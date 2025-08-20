package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.convert.BuildConvert;
import com.ruoyi.poor.domain.Build;
import com.ruoyi.poor.domain.Industry;
import com.ruoyi.poor.dto.BuildDto;
import com.ruoyi.poor.mapper.BuildMapper;
import com.ruoyi.poor.service.IBuildService;
import com.ruoyi.system.domain.SysPubFile;
import com.ruoyi.system.service.ISysPubFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildServiceImpl extends ServiceImpl<BuildMapper,Build> implements IBuildService {
    public  static String  IMG_REF_TYPE="build_image";

    @Autowired
    private ISysPubFileService sysPubFileService;

    @Override
    public List<Map<String, Object>> groupByVillageId() {
        QueryWrapper<Build> wrapper = new QueryWrapper<>();
        wrapper.select("village_id as villageId, COUNT(*) as count")
                .groupBy("village_id");
        return this.baseMapper.selectMaps(wrapper);
    }

    @Override
    public List<BuildDto> selectList(BuildDto dto) {
        QueryWrapper<Build> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("village_id",dto.getVillageId());
        List<Build> buildList= this.baseMapper.selectList(queryWrapper);

        List<BuildDto> buildDtos = BuildConvert.INSTANCE.convert2ListDto(buildList);

        //图片
        List<Long> ids = buildDtos.stream().map(buildDto -> {
            return buildDto.getId();
        }).collect(Collectors.toList());

        QueryWrapper<SysPubFile> queryFileWrapper = new QueryWrapper<>();
        queryFileWrapper.in("ref_id",ids);
        queryFileWrapper.eq("ref_type",IMG_REF_TYPE);
        List<SysPubFile> fileList = this.sysPubFileService.list(queryFileWrapper);

        buildDtos.forEach(buildDto -> {
            if(buildDto.getImageIds()==null){
                buildDto.setImageIds(new ArrayList<>());
            }

            fileList.forEach(file -> {
               if (file.getRefId().equals(buildDto.getId())) {
                   buildDto.getImageIds().add(file.getId());
               }
            });
        });

        return buildDtos;
    }

    @Override
    public BuildDto getBuildById(Long id) {
        Build domain = this.baseMapper.selectById(id);
        BuildDto dto = BuildConvert.INSTANCE.convert2Dto(domain);

        QueryWrapper<SysPubFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ref_id",id);
        queryWrapper.eq("ref_type",IMG_REF_TYPE);

        List<SysPubFile> list = this.sysPubFileService.list(queryWrapper);
        List<Long> imageIds = list.stream().map(SysPubFile::getId).collect(Collectors.toList());
        dto.setImageIds(imageIds);
        return dto;
    }

    @Override
    public Long saveOrUpdateBuild(BuildDto dto) {
        long id;
        if(dto.getId()==null){
            id = IdUtil.getSnowflakeNextId();
        }else{
            id=dto.getId();
        }

        dto.setId(id);
        this.saveOrUpdate(BuildConvert.INSTANCE.convert2Entity(dto));

        //图片关系
        if(dto.getImageIds()!=null && dto.getImageIds().size()>0) {
            UpdateWrapper<SysPubFile> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("ref_type", IMG_REF_TYPE);
            updateWrapper.eq("ref_id", id);
            updateWrapper.in("id", dto.getImageIds());
            this.sysPubFileService.update(updateWrapper);
        }
        return id;
    }
}
