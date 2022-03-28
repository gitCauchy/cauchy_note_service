package com.cauchynote.utils;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author Cauchy
 * @ClassName EmailUtil.java
 * @createTime 2022年03月28日 09:05:00
 */
public class EmailUtil {
    /**
     * @param toAddress 接收地址
     * @param name      发送人
     * @param subject   邮件主题
     * @param content   内容
     * @throws EmailException EmailException
     */
    public static void sendMsg(String toAddress, String name, String subject, String content) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(SystemConstantDefine.SMTPHOST);
        email.setCharset("utf-8");
        email.addTo(toAddress);
        email.setFrom(SystemConstantDefine.FROM, name);
        email.setAuthentication(SystemConstantDefine.FROM, SystemConstantDefine.PASSWORD);
        email.setSubject(subject);
        email.setMsg(content);
        email.send();
    }
}
