package com.fourboy.bean.res;

import com.fourboy.constant.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class BaseObj<T> {
    /**
     * 0表示成功,其他表示错误
     */
    @ApiModelProperty(value = "返回状态码")
    private String code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据对象")
    private T data;

    public BaseObj() {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMsg();
    }

    public BaseObj(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMsg();
    }
}
