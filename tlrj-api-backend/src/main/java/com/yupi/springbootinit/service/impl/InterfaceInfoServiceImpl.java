package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.service.InterfaceInfoService;
import com.yupi.springbootinit.mapper.InterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author HX
* @description 针对表【interface_info(接口信息表-)】的数据库操作Service实现
* @createDate 2024-09-07 21:44:59
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

}




