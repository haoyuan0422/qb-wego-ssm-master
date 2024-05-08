/*
 * Copyright 2023 杭州云亮科技有限公司
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wego.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 *
 * @author hc
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    /**
     * 切入点：设置方法会在com.wego.controller包及其子包下的、任意访问控制修饰符修饰的、返回值为任意类型的、包含任何多个参数的、任意方法、执行之前执行
     */
    @Pointcut("execution(* com.wego.controller..*.*(..))")
    public void fun() {
    }

    ///**
    // * 前置通知
    // * @param joinPoint
    // */
    //@Before("fun()")
    //public void doBefore(JoinPoint joinPoint) {
    //    final String fullMethodName = getFullMethodName(joinPoint);
    //    log.info(fullMethodName+"方法开始执行");
    //}
    //
    ///**
    // * 最终通知
    // * @param joinPoint
    // */
    //@After("fun()")
    //public void doAfter(JoinPoint joinPoint) {
    //    final String fullMethodName = getFullMethodName(joinPoint);
    //    log.info(fullMethodName+"方法执行结束");
    //}
    //
    ///**
    // * 后置通知
    // * @param joinPoint
    // * @param res
    // */
    //@AfterReturning(returning = "res", pointcut = "fun()")
    //public void doAfterReturning(JoinPoint joinPoint, Object res) {
    //    final String fullMethodName = getFullMethodName(joinPoint);
    //    final Object[] args = joinPoint.getArgs();
    //    log.info("访问的方法为：" + fullMethodName + " 参数为：" + Arrays.toString(args) + " 结果为：" + res);
    //}
    //
    ///**
    // * 异常抛出后通知
    // * @param joinPoint
    // * @param ex
    // */
    //@AfterThrowing(throwing = "ex", pointcut = "fun()")
    //public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
    //    final String fullMethodName = getFullMethodName(joinPoint);
    //    final Object[] args = joinPoint.getArgs();
    //    log.error("访问 " + fullMethodName + "参数为：" + Arrays.toString(args) + " 出现异常：" + ex);
    //}

    /**
     * 环绕通知
     * 第一个参数可以定义为org.aspectj.lang.ProceedingJoinPoint类型
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("fun()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;

        //取得类名和方法名
        final String fullMethodName = getFullMethodName(joinPoint);

        //相当于前置通知
        log.info(fullMethodName + "方法开始执行");

        Object[] args = joinPoint.getArgs();
        log.info("参数信息为：{}  ", args);

        try {
            result = joinPoint.proceed();
            log.info("结果为：{}" + result);
        } catch (Throwable ex) {
            //相当于异常抛出后通知
            StackTraceElement stackTraceElement = ex.getStackTrace()[ex.getStackTrace().length - 1];

            log.error("{}方法的{}行", fullMethodName, stackTraceElement.getLineNumber() + "抛出异常");
            log.error("异常信息为：{}  ", ex.fillInStackTrace().toString());
            throw ex;
        } finally {
            //相当于最终通知
            log.info(fullMethodName + "方法执行结束");
        }

        return result;
    }

    private String getFullMethodName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName()
                + "."
                + joinPoint.getSignature().getName()
                + "()";
    }
}
