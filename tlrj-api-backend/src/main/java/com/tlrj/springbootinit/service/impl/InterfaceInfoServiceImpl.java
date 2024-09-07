package com.tlrj.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tlrj.springbootinit.common.ErrorCode;
import com.tlrj.springbootinit.exception.BusinessException;
import com.tlrj.springbootinit.exception.ThrowUtils;
import com.tlrj.springbootinit.mapper.InterfaceInfoMapper;
import com.tlrj.springbootinit.model.entity.InterfaceInfo;
import com.tlrj.springbootinit.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author HX
 * @description 针对表【interface_info(接口信息表-)】的数据库操作Service实现
 * @createDate 2024-09-07 21:44:59
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
        implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String method = interfaceInfo.getMethod();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(name, url, method), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(name) && name.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称过长");
        }
        if (StringUtils.isNotBlank(description) && description.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述过长");
        }
        if (StringUtils.isNotBlank(url) && url.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "URL过长");
        }
    }

}




