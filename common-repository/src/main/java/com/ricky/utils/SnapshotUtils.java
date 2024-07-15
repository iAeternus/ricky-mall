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

    @SuppressWarnings("unchecked")
    public static <T extends Aggregate<ID>, ID extends Identifier> T snapshot(T aggregate) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(aggregate);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
