package com.hao.genera.exception;

import com.hao.genera.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ClassName: GlobalControllerExceptionHandler
 * @Description: TODO
 * @author: 吴昊
 * @date: 2019/5/7  11:41
 */
@ControllerAdvice()
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    private R<Boolean> runtimeExceptionHandler(Exception e) {
        log.error(e.getMessage());
        return R.data(BaseException.UNKNOWN_ERROR_CODE,false,e.getMessage());
    }


    @ExceptionHandler(ValidateException.class)
    @ResponseBody
    private R<Boolean> ValidateExceptionHandler(ValidateException e) {
        log.error(e.getMessage());
        return R.data(e.getErrorCode(),false,e.getMessage());
    }
}
