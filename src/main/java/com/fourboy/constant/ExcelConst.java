package com.fourboy.constant;

/**
 * 导出excel常量
 */
public class ExcelConst {

    /**
     * 数据导出-城区成交套数排行-表头
     */
    public final static String[] AREA_COUNTY_RANK_HEADER = {"排名", "城区", "成交套数", "成交占比"};
    /**
     * 数据导出-城区成交套数排行-对应KEY
     */
    public final static String[] AREA_COUNTY_RANK_KEYS = {"rank", "areaCounty", "dealCount", "dealPercent"};
    /**
     * 数据导出-城区成交套数排行-文件名
     */
    public final static String AREA_COUNTY_RANK_FILE_NAME = "城区成交套数排行.xls";
    /**
     * 数据导出-经纪公司成交占比排行-表头
     */
    public final static String[] CONTRACTING_RANK_HEADER = {"排名", "签约机构", "成交套数", "成交占比"};
    /**
     * 数据导出-经纪公司成交占比排行-对应KEY
     */
    public final static String[] CONTRACTING_RANK_KEYS = {"rank", "contracting", "dealCount", "dealPercent"};
    /**
     * 数据导出-城区成交套数排行-文件名
     */
    public final static String CONTRACTING_RANK_FILE_NAME = "经纪公司成交占比排行.xls";
}
