package com.test.jvm;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 *用于编写Btrace 监控的类据的工程需引入btrace 解压缩包中的btrace-agent.jar、btrace-boot.jar、btrace-client.jar
 * */
@BTrace
public class BtraceMonitor {
 
 /**
  * 此次demo仅为验证 WebDemo 的 DaysRestController
  * */
 @OnMethod(clazz="com.sfpay.h5wallet.web.CashBankCard", method="doSetBankList",location=@Location(Kind.RETURN))
    public static void onThreadStart(
     @ProbeClassName String pcn,@ProbeMethodName String probeMethod, @Duration long duration,
     String text,@Return int sleepms
     ) {
	 	print(strcat("类：", pcn));
        print(strcat("方法：", probeMethod));
        print(strcat("入参：", text));
        print("返回结果：" + sleepms);
        println("时长：" + duration/1000000);  //处理duration单位为纳秒不友好
        println("----------------------------------------------------"); //处理日志缓冲丢失一行
           
    }
}



/*
 * 该部分为实际实践代码，因涉及中文btrace命令行编译异常，且package使得编译运行变更环境麻烦些
 * 故省去package及去年所有中文字符 
 * 
 * cd /tomcat/tomcat/h5-?/lib/bin
 * export PATH=/tomcat/tomcat/h5-?/lib/bin:$PATH
 * export JAVA_HOME=/usr/java/jdk1.7.0_79
 * ./btrace 4180 BtraceMonitor.java 
 * 

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;


@BTrace
public class BtraceMonitor {
 

 @OnMethod(clazz="com.sfpay.h5wallet.web.CashBankCard", method="doSetBankList",location=@Location(Kind.RETURN))
    public static void onThreadStart(
     @ProbeClassName String pcn,@ProbeMethodName String probeMethod, @Duration long duration,
     String text,@Return int sleepms
     ) {
	 	print(strcat("class:", pcn));
        print(strcat("Method:", probeMethod));
        print(strcat("InParam:", text));
        print("Return:" + sleepms);
        println("times:" + duration/1000000);  
        println("----------------------------------------------------"); 
           
    }
}*/



