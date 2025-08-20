package com.ruoyi.poor.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.convert.VillageConvert;
import com.ruoyi.poor.domain.*;
import com.ruoyi.poor.dto.VillageDto;
import com.ruoyi.poor.mapper.VillageMapper;
import com.ruoyi.poor.service.*;
import com.ruoyi.system.domain.SysPubFile;
import com.ruoyi.system.service.ISysPubFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VillageServiceImpl extends ServiceImpl<VillageMapper, Village> implements IVillageService {

    @Autowired
    private IIndustryService industryService;

    @Autowired
    private ILandService landService;

    @Autowired
    private IBuildService buildService;

    @Autowired
    private UserService userService;

    @Autowired
    private ISysPubFileService  sysPubFileService;


    public  static String  IMG_REF_TYPE="village_image";

    @Override
    public List<VillageDto> selectList(VillageDto dto) {
        QueryWrapper<Village> queryWrapper = new QueryWrapper<Village>();
        List<Village> villages = this.baseMapper.selectList(queryWrapper);

        System.out.println(villages);
        List<VillageDto> villageDtoList = VillageConvert.INSTANCE.convert2ListDto(villages);
        //查询特色企业数量
        List<Map<String, Object>> villageCountList = industryService.groupByVillageId();

        Map<Long,Long> villageCountMap=villageCountList.stream().collect(Collectors.toMap(
                map -> (Long) map.get("villageId"),
                map -> (Long) map.get("count")
        ));

        villageDtoList.forEach(item -> {
            item.setIndustryCount(villageCountMap.get(item.getId()));
        });

        return villageDtoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveOrUpdateVillage(VillageDto domain) {
        long id;
        if(domain.getId()==null){
             id = IdUtil.getSnowflakeNextId();
             domain.setId(id);
        }else{
            id=domain.getId();
        }


        this.saveOrUpdate(VillageConvert.INSTANCE.convert2Entity(domain));

        //图片关系
        if(domain.getImageIds()!=null && domain.getImageIds().size()>0){
            UpdateWrapper<SysPubFile> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("ref_type",IMG_REF_TYPE);
            updateWrapper.eq("ref_id",id);
            updateWrapper.in("id",domain.getImageIds());
            this.sysPubFileService.update(updateWrapper);
        }
        return id;

    }

    @Override
    public void removeVillageById(Long id) {
        this.baseMapper.deleteById(id);
        this.buildService.remove(new QueryWrapper<Build>().eq("village_id",id));
        this.industryService.remove(new QueryWrapper<Industry>().eq("village_id",id));
        this.landService.remove(new QueryWrapper<Land>().eq("village_id",id));
        this.userService.remove(new QueryWrapper<User>().eq("village_id",id));
    }

    @Override
    public VillageDto getVillageById(Long id) {
        Village village = this.baseMapper.selectById(id);
        VillageDto villageDto = VillageConvert.INSTANCE.convert2Dto(village);

        QueryWrapper<SysPubFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ref_id",id);
        queryWrapper.eq("ref_type",IMG_REF_TYPE);

        List<SysPubFile> list = this.sysPubFileService.list(queryWrapper);
        List<Long> imageIds = list.stream().map(SysPubFile::getId).collect(Collectors.toList());
        villageDto.setImageIds(imageIds);
        return villageDto;
    }
}
