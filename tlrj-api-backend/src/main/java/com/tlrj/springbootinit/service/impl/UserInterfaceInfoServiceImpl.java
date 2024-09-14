package com.tlrj.springbootinit.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tlrj.springbootinit.common.ErrorCode;
import com.tlrj.springbootinit.exception.BusinessException;
import com.tlrj.springbootinit.exception.ThrowUtils;
import com.tlrj.springbootinit.mapper.UserInterfaceInfoMapper;
import com.tlrj.springbootinit.model.entity.InterfaceInfo;
import com.tlrj.springbootinit.model.entity.UserInterfaceInfo;
import com.tlrj.springbootinit.service.UserInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author HX
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
 * @createDate 2024-09-14 14:46:16
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = userInterfaceInfo.getId();
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer leftNum = userInterfaceInfo.getLeftNum();
        Integer status = userInterfaceInfo.getStatus();
        Date createTime = userInterfaceInfo.getCreateTime();
        Date updateTime = userInterfaceInfo.getUpdateTime();
        Integer isDelete = userInterfaceInfo.getIsDelete();
    }

    /**
     * 总调用次数+1，剩余调用次数-1
     * @param userId
     * @param interfaceInfoId
     * @return
     */
    @Override
    public boolean invokeCount(Long userId, Long interfaceInfoId) {
        if (userId == null || interfaceInfoId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId", userId);
        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
        updateWrapper.setSql("leftNum = leftNum - 1,totalNum = totalNum + 1");
        return this.update(updateWrapper);
    }
}




