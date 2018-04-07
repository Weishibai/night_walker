package com.sunshine.publicserver.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class SerializeUtil {

    public static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);
    private static final byte[] EMPTY_BYTES = new byte[0];
    /**
     * 序列化成字节数组
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj) {
        if(obj == null) {
            return EMPTY_BYTES;
        }
        try {
            byte[] bytes = null;
            Schema schema = (Schema)RuntimeSchema.getSchema(obj.getClass());
            LinkedBuffer buffer = LinkedBuffer.allocate(4096);
            try {
                bytes = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
            } finally {
                buffer.clear();
            }
            return bytes;
        } catch (Exception e) {
            logger.error("protostuff serialize error:",e);
        }
        return EMPTY_BYTES;
    }

    /**
     * 反序列化成对象
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] bytes,Class<T> type) {
        if(bytes == null || bytes.length <= 0) {
            return null;
        }
        try {
            Schema<T> schema = RuntimeSchema.getSchema(type);
            T obj = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
            return obj;
        } catch (Exception e) {
            logger.error("protostuff deserialize error:",e);
        }
        return null;
    }

    /**
     * 反序列化成对象
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] bytes, Class<T> type, int offset, int lenght) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        try {
            Schema<T> schema = RuntimeSchema.getSchema(type);
            T obj = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, offset, lenght, obj, schema);
            return obj;
        } catch (Exception e) {
            logger.error("protostuff deserialize error:", e);
        }
        return null;
    }

    public static  String serialize2String(Object obj) throws Exception{
        byte[] bytes = serialize(obj);
        if(bytes != null){
            return new String(bytes, Charsets.UTF_8.name());
        }
        return null;
    }

    public static <T> T deserializeFromString(String str,Class<T> type) throws Exception{
        if(str == null) {
            return null;
        }
        return deserialize(str.getBytes(Charsets.UTF_8.name()),type);
    }

    /**
     * 序列化List对象
     * @param list 对象
     * @param type 类型
     * @return 字节数组
     */
    public static <T> byte[] serializeList(List<T> list, Class<T> type) {
        if(list == null) {
            return EMPTY_BYTES;
        }
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Schema<T> schema = (Schema)RuntimeSchema.getSchema(type);
            ProtostuffIOUtil.writeListTo(out,list, schema, buffer);
            return out.toByteArray();
        } catch (Exception e) {
            logger.error("protostuff serializeList error:",e);
        } finally {
            buffer.clear();
        }
        return EMPTY_BYTES;
    }

    /**
     * 反序列化成List对象
     * @param bytes 字节数组
     * @param type 类型
     * @return list 对象
     */
    public static <T> List<T> deserializeFromList(byte[] bytes,Class<T> type) {
        if(bytes == null || bytes.length <= 0) {
            return null;
        }
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            Schema<T> schema = RuntimeSchema.getSchema(type);
            List<T> list = ProtostuffIOUtil.parseListFrom(in,schema);
            return list;
        } catch (Exception e) {
            logger.error("protostuff deserializeList error:",e);
        }
        return null;
    }
}
