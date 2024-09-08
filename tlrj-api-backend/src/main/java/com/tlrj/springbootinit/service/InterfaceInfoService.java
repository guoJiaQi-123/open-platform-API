package com.tlrj.springbootinit.service;

import com.tlrj.springbootinit.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author HX
* @description 针对表【interface_info(接口信息表-)】的数据库操作Service
* @createDate 2024-09-07 21:44:59
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
