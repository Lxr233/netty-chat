package org.lxr.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import java.util.HashMap;
import java.util.Map;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.lxr.serialize.Serializer;
import org.lxr.serialize.impl.JSONSerializer;

/**
 * @description: 编解码类
 *
 *  协议格式：
 *  -------------------------------------------------------------------------------------------------
 *  | 魔数（4字节）| 版本号（1字节）| 序列化算法（1字节）| 指令（1字节）| 数据长度（4字节）| 数据（N字节）|
 *  -------------------------------------------------------------------------------------------------
 *
 * @create: 2019-01-22 22:51
 **/
public class PacketCodec
{
    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf  encode(Packet packet){
        // ioBuffer() 方法会返回适配 io 读写相关的内存，它会尽可能创建一个直接内存，直接内存可以理解为不受 jvm 堆管理的内存空间，写到 IO 缓冲区的效果更高。
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

}
