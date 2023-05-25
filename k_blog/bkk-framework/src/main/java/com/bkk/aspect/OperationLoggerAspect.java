package com.bkk.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bkk.annotation.OperationLogger;
import com.bkk.domain.entity.AdminLog;
import com.bkk.domain.entity.ExceptionLog;
import com.bkk.domain.entity.User;
import com.bkk.domain.vo.SystemUserVO;
import com.bkk.exception.SystemException;
import com.bkk.mapper.AdminLogMapper;
import com.bkk.mapper.ExceptionLogMapper;
import com.bkk.mapper.UserMapper;
import com.bkk.utils.AspectUtils;
import com.bkk.utils.BaseContext;
import com.bkk.utils.BeanCopyUtils;
import com.bkk.utils.IpUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

/**
 * 日志切面
 */
@Aspect
@Component
@RequiredArgsConstructor
public class OperationLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(OperationLoggerAspect.class);

    private final AdminLogMapper adminLogMapper;

    private final ExceptionLogMapper exceptionLogMapper;

    private final UserMapper userMapper;

    /**
     * 开始时间
     */
    Date startTime;

    @Pointcut(value = "@annotation(operationLogger)")
    public void pointcut(OperationLogger operationLogger) {

    }

    @Around(value = "pointcut(operationLogger)")
    public Object doAround(ProceedingJoinPoint joinPoint, OperationLogger operationLogger) throws Throwable {
        //因给了演示账号所有权限以供用户观看，所以执行业务前需判断是否是管理员操作
        String name = userMapper.getRoleNameById(BaseContext.getCurrentId());
        if (!name.equals("admin")) {
            throw new SystemException("演示账号不允许操作!");
        }

        startTime = new Date();

        //先执行业务
        Object result = joinPoint.proceed();
        try {
            // 日志收集
            handle(joinPoint, getHttpServletRequest());

        } catch (Exception e) {
            logger.error("日志记录出错!", e);
        }

        return result;
    }


//    @AfterThrowing(value = "pointcut(operationLogger)", throwing = "e")
//    public void doAfterThrowing(JoinPoint joinPoint, OperationLogger operationLogger, Throwable e) throws Exception {
//        HttpServletRequest request = getHttpServletRequest();
//        String ip = IpUtils.getIpAddress(request);
//        String operationName = AspectUtils.INSTANCE.parseParams(joinPoint.getArgs(), operationLogger.value());
//        // 获取参数名称字符串
//        String paramsJson = getParamsJson((ProceedingJoinPoint) joinPoint);
//        User one = userMapper.selectById(BaseContext.getCurrentId());
//        SystemUserVO user = BeanCopyUtils.copyBean(one, SystemUserVO.class);
//
//        ExceptionLog exception = ExceptionLog.builder().ip(ip).ipSource(IpUtils.getIpSource(ip))
//                .params(paramsJson).username(user.getUsername()).method(joinPoint.getSignature().getName())
//                .exceptionJson(JSON.toJSONString(e)).exceptionMessage(e.getMessage()).operation(operationName)
//                .build();
//        exceptionLogMapper.insert(exception);
//    }

    /**
     * 管理员日志收集
     *
     * @param point
     * @throws Exception
     */
    private void handle(ProceedingJoinPoint point, HttpServletRequest request) throws Exception {

        Method currentMethod = AspectUtils.INSTANCE.getMethod(point);

        //获取操作名称
        OperationLogger annotation = currentMethod.getAnnotation(OperationLogger.class);

        boolean save = annotation.save();

        String operationName = AspectUtils.INSTANCE.parseParams(point.getArgs(), annotation.value());
        if (!save) {
            return;
        }
        // 获取参数名称字符串
        String paramsJson = getParamsJson(point);

        // 当前操作用户
        User one = userMapper.selectById(BaseContext.getCurrentId());
        SystemUserVO user = BeanCopyUtils.copyBean(one, SystemUserVO.class);
        String type = request.getMethod();
        String ip = IpUtils.getIpAddress(request);
        String url = request.getRequestURI();

        // 存储日志
        Date endTime = new Date();
        Long spendTime = endTime.getTime() - startTime.getTime();
        AdminLog adminLog = AdminLog.builder().ip(ip)
                .source(StringUtils.isNotBlank(IpUtils.getIpSource(ip)) ? IpUtils.getIpSource(ip) : "未知")
                .type(type).requestUrl(url).username(user.getNickname())
                .paramsJson(paramsJson).classPath(point.getTarget().getClass().getName())
                .methodName(point.getSignature().getName())
                .operationName(operationName).spendTime(spendTime).build();
        adminLogMapper.insert(adminLog);
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

    private HttpServletRequest getHttpServletRequest() {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        assert requestAttributes != null;
        return (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
    }

}
