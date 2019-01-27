package org.lxr.protocal.command;

/**
 * @description: 指令类型的定义
 * @create: 2019-01-22 22:23
 **/
public interface Command
{
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
