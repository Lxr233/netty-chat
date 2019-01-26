package org.lxr.serialize.impl;

import com.alibaba.fastjson.JSON;
import org.lxr.serialize.Serializer;
import org.lxr.serialize.SerializerAlgorithm;

/**
 * @description: json序列化算法的实现类
 * @create: 2019-01-22 22:33
 **/
public class JSONSerializer implements Serializer
{

    @Override
    public byte getSerializerAlgorithm()
    {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object)
    {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes)
    {
        return JSON.parseObject(bytes,clazz);
    }
}
