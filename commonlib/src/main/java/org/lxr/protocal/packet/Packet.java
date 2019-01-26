package org.lxr.protocal.packet;

import lombok.Data;

/**
 * @description: 通信过程中 Java 对象的抽象类
 *
 * 协议格式：
 * -------------------------------------------------------------------------------------------------
 * | 魔数（4字节）| 版本号（1字节）| 序列化算法（1字节）| 指令（1字节）| 数据长度（4字节）| 数据（N字节）|
 * -------------------------------------------------------------------------------------------------
 * @create: 2019-01-22 21:53
 **/
@Data
public abstract class Packet
{

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();
}
