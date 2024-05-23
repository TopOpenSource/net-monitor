package com.ruoyi.poor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.poor.domain.DataFile;
import com.ruoyi.poor.domain.Family;
import com.ruoyi.poor.mapper.DataFileMapper;
import com.ruoyi.poor.mapper.FamilyMapper;
import com.ruoyi.poor.service.DataFileService;
import com.ruoyi.poor.service.FamilyService;
import org.springframework.stereotype.Service;

@Service
public class FamilyServiceImpl extends ServiceImpl<FamilyMapper, Family> implements FamilyService {
}
