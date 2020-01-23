package com.ccx.security.handler;

import com.ccx.market.emun.ResultEnum;
import com.ccx.market.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;

import static com.ccx.market.emun.ResultEnum.ParamErrResult;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 用来处理bean validation异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public R resolveConstraintViolationException(ConstraintViolationException ex){

        StringBuilder msgBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
        }
        return R.error(ParamErrResult.getCode(),msgBuilder.toString());
    }


    /**
     * 验证异常
     *
     * @param e
     * @return
     * @throws MethodArgumentNotValidException
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException e) throws MethodArgumentNotValidException {

        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMesssage = new StringBuilder();
        errorMesssage.append("Invalid Request:\n");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage.append(fieldError.getDefaultMessage()).append("\n");
        }
        logger.warn("MethodArgumentNotValidException:{}", errorMesssage);

        return R.error(ParamErrResult.getCode(), errorMesssage.toString());
    }


    /**
     * 无权限
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public R handleAccessDeniedException( AccessDeniedException e) {
        logger.error(e.getMessage(), e);
        return R.error(ResultEnum.AccessDeniedResult.getCode(), ResultEnum.AccessDeniedResult.getMsg());
    }

    /**
     * 全局异常
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R handleException(Exception e) {

        logger.error(e.getMessage(), e);
        return R.error(ResultEnum.FailResult.getCode(), ResultEnum.FailResult.getMsg());
    }

}
