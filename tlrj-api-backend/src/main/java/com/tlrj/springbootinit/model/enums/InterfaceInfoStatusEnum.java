package com.tlrj.springbootinit.model.enums;

import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.common.collect.HppcMaps;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 接口类型枚举
 * @author <a href="https://blog.csdn.net/guojiaqi_">oldGj</a>
 * @from <a href="https://github.com/guoJiaQi-123/TYUT-SmartViewBI-backend">GitHub地址</a>
 */
public enum InterfaceInfoStatusEnum {

    ONLINE("上线", 1),
    OFFLINE("下线", 0);

    private final String text;

    private final Integer value;

    InterfaceInfoStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static InterfaceInfoStatusEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (InterfaceInfoStatusEnum anEnum : InterfaceInfoStatusEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
