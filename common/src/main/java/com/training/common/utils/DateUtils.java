package com.training.common.utils;

import java.time.LocalDate;
import java.time.Month;

/**
 * @User: wong
 * @Date: 2020/10/10
 * @Description: 日期相关工具
 */
public class DateUtils {


  /**
   * 获取某个时间所处季度的第一天或最后一天
   *
   * @param localDate 时间
   * @param isFirst true 获取季度的第一天 false 获取季度的最后一天
   */
  public static LocalDate setSearchTime(LocalDate localDate, boolean isFirst) {
    int year = localDate.getYear();
    int m = localDate.getMonthValue();
    LocalDate resDate = LocalDate.of(year, m, 1);
    Month month = resDate.getMonth();
    Month firstMonthOfQuarter = month.firstMonthOfQuarter();
    Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
    if (isFirst)
      return LocalDate.of(resDate.getYear(), firstMonthOfQuarter, 1);
    return LocalDate.of(resDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(resDate.isLeapYear()));
  }

}
