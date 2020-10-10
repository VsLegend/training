package com.training.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @User: wong
 * @Date: 2020/10/10
 * @Description:
 */
public class BeanConvertUtils {

  /**
   * 复制对象相同字段的值
   * @param sourceObj 被复制对象
   * @param targetCls 赋值对象
   */
  public static <T> T copyBean(Object sourceObj, Class<T> targetCls) {
    T targetObj = null;
    if (sourceObj != null) {
      try {
        targetObj = BeanUtils.instantiate(targetCls);
        BeanUtils.copyProperties(sourceObj, targetObj);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return targetObj;
  }


  // 复制list中的每一个对象到另一个对象
  public static <T> List<T> copyList(Collection<?> list, Class<T> targetCls) {
    List<T> result = new ArrayList<T>();
    if (list == null || list.size() == 0) {
      return result;
    }
    for (Object obj : list) {
      result.add(copyBean(obj, targetCls));
    }
    return result;
  }
}
