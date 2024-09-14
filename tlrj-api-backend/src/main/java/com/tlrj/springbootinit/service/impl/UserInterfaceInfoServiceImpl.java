package com.tlrj.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.UserInterfaceInfo;
import com.tlrj.springbootinit.service.UserInterfaceInfoService;
import com.tlrj.springbootinit.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author HX
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-09-14 14:46:16
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

}




