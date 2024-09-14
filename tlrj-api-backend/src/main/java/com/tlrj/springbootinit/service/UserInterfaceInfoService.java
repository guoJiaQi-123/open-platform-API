package com.tlrj.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tlrj.springbootinit.model.entity.UserInterfaceInfo;

/**
* @author HX
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-09-14 14:46:16
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean b);
}
