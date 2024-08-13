  --
SELECT marriage,dealCount,concat(round(dealCount / total * 100.00, 2), '%') dealPercent
        FROM(
        select * FROM
        (
        select t.marriage as marriage,count(t.house_code) dealCount from house_data t
        where 1=1
        and t.status = '0'
        and t.contracting_time BETWEEN #{contractingTimeStart} and #{contractingTimeEnd} -- 时间区间
        and t.plan_type = #{planType} -- 用途
        and t.area_county = #{areaCounty} -- 区县
        and t.house_type= #{houseType} -- 户型
        and t.contract_total_price BETWEEN #{contractTotalPriceStart} and #{contractTotalPriceEnd} -- 合同价
        and t.house_total_price BETWEEN #{houseTotalPriceStart} and #{houseTotalPriceEnd} -- 房屋价
        GROUP BY t.marriage
        ) t
        inner join (
        select count(t.house_code) total from house_data t
        where 1=1
        and t.status = '0'
        and t.contracting_time BETWEEN #{contractingTimeStart} and #{contractingTimeEnd} -- 时间区间
        and t.plan_type = #{planType} -- 用途
        and t.area_county = #{areaCounty} -- 区县
        and t.house_type= #{houseType} -- 户型
        and t.contract_total_price BETWEEN #{contractTotalPriceStart} and #{contractTotalPriceEnd} -- 合同价
        and t.house_total_price BETWEEN #{houseTotalPriceStart} and #{houseTotalPriceEnd} -- 房屋价
        )t1 on 1=1
        )t order by dealCount desc
