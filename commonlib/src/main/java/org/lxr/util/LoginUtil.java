package org.lxr.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import org.lxr.attribute.Attributes;

/**
 * @description: 登陆相关操作工具类
 * @create: 2019-01-27 16:09
 **/
public class LoginUtil
{
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get()!=null;
    }
}
