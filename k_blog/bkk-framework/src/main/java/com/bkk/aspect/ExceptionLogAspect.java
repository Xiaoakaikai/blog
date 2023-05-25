package com.bkk.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bkk.annotation.BusinessLogger;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.entity.ExceptionLog;
import com.bkk.domain.entity.User;
import com.bkk.domain.vo.SystemUserVO;
import com.bkk.mapper.ExceptionLogMapper;
import com.bkk.mapper.UserMapper;
import com.bkk.utils.AspectUtils;
import com.bkk.utils.BaseContext;
import com.bkk.utils.BeanCopyUtils;
import com.bkk.utils.IpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * AOP记录异常日志
 *
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ExceptionLogAspect {

    private final ExceptionLogMapper exceptionLogMapper;

    private final UserMapper userMapper;

    /**
     * 设置操作异常日志切入点，扫描所有controller包下的操作
     */
//    @Pointcut("execution(* *..*Controller.*(..))")
    @Pointcut("@annotation(operationLogger)")
    public void exceptionLogPointCut(OperationLogger operationLogger) {
    }

    /**
     * 异常通知，只有连接点异常后才会执行
     *
     * @param joinPoint 切面方法的信息
     * @param e         异常
     */
    @AfterThrowing(pointcut = "exceptionLogPointCut(operationLogger)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, OperationLogger operationLogger, Throwable e) throws Exception {
        HttpServletRequest request = getHttpServletRequest();
        String ip = IpUtils.getIpAddress(request);

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作
//        OperationLogger operationLogger = method.getAnnotation(OperationLogger.class);

        String operationName = AspectUtils.INSTANCE.parseParams(joinPoint.getArgs(), operationLogger.value());
        // 获取参数名称字符串
        String paramsJson = getParamsJson((ProceedingJoinPoint) joinPoint);
        User one = userMapper.selectById(BaseContext.getCurrentId());
        SystemUserVO user = BeanCopyUtils.copyBean(one, SystemUserVO.class);

        ExceptionLog exception = ExceptionLog.builder().ip(ip).ipSource(IpUtils.getIpSource(ip))
                .params(paramsJson).username(user.getUsername()).method(joinPoint.getSignature().getName())
                .exceptionJson(JSON.toJSONString(e)).exceptionMessage(e.getMessage()).operation(operationName)
                .build();
        exceptionLogMapper.insert(exception);
    }

    private HttpServletRequest getHttpServletRequest() {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        assert requestAttributes != null;
        return (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
    }

    private String getParamsJson(ProceedingJoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException {
        // 参数值
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();

        // 通过map封装参数和参数值
        HashMap<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }

        boolean isContains = paramMap.containsKey("request");
        if (isContains) paramMap.remove("request");
        return JSONObject.toJSONString(paramMap);
    }

}