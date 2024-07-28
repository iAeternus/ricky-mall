package com.ricky.utils;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;

import java.io.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className SnapshotUtils
 * @desc
 */
public class SnapshotUtils {

    // @SuppressWarnings("unchecked")
    // public static <T extends Aggregate<ID>, ID extends Identifier> T snapshot(T aggregate, Class<?> clazz) {
    //     // ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //     // ObjectOutputStream oos;
    //     // try {
    //     //     oos = new ObjectOutputStream(bos);
    //     //     oos.writeObject(aggregate);
    //     //     ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    //     //     ObjectInputStream ois = new ObjectInputStream(bis);
    //     //     return (T) ois.readObject();
    //     // } catch (IOException | ClassNotFoundException e) {
    //     //     throw new RuntimeException(e);
    //     // }
    //     Gson gson = new Gson();
    //     return (T) gson.fromJson(gson.toJson(aggregate), clazz);
    // }

    @SuppressWarnings("unchecked")
    public static <T extends Aggregate<ID>, ID extends Identifier> T snapshot(T aggregate) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(aggregate);

            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }

                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

}
