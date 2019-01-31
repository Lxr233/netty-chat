package org.lxr.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import java.util.HashMap;
import java.util.Map;
import org.lxr.protocal.command.Command;
import org.lxr.protocal.packet.Packet;
import org.lxr.protocal.packet.request.CreateGroupRequestPacket;
import org.lxr.protocal.packet.request.GroupMessageRequestPacket;
import org.lxr.protocal.packet.request.JoinGroupRequestPacket;
import org.lxr.protocal.packet.request.ListGroupMembersRequestPacket;
import org.lxr.protocal.packet.request.LoginRequestPacket;
import org.lxr.protocal.packet.request.LogoutRequestPacket;
import org.lxr.protocal.packet.request.MessageRequestPacket;
import org.lxr.protocal.packet.request.QuitGroupRequestPacket;
import org.lxr.protocal.packet.response.CreateGroupResponsePacket;
import org.lxr.protocal.packet.response.GroupMessageResponsePacket;
import org.lxr.protocal.packet.response.JoinGroupResponsePacket;
import org.lxr.protocal.packet.response.ListGroupMembersResponsePacket;
import org.lxr.protocal.packet.response.LoginResponsePacket;
import org.lxr.protocal.packet.response.LogoutResponsePacket;
import org.lxr.protocal.packet.response.MessageResponsePacket;
import org.lxr.protocal.packet.response.QuitGroupResponsePacket;
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
    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodec INSTANCE = new PacketCodec();
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        packetTypeMap.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBuf byteBuf , Packet packet){
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
