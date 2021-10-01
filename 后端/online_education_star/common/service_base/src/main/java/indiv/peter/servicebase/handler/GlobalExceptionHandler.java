package indiv.peter.servicebase.handler;

import indiv.peter.commonutils.Result;
import indiv.peter.servicebase.exception.GlobalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author PeterRays
 * ControllerAdvice 使用了AOP的功能
 * @date 2021/9/14
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.error().message("特殊异常处理");
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Result error(GlobalException e){
        e.printStackTrace();
        return Result.error().message(e.getMessage());
    }
}
