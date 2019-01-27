package org.lxr.attribute;

import io.netty.util.AttributeKey;

/**
 * @description: channel相关的属性
 * @author: l00427576
 * @create: 2019-01-27 16:12
 **/
public interface Attributes
{
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
