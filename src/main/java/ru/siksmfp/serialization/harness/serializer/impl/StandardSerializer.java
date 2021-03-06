package ru.siksmfp.serialization.harness.serializer.impl;

import ru.siksmfp.serialization.harness.model.standart.User;
import ru.siksmfp.serialization.harness.serializer.api.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StandardSerializer implements Serializer<User> {

    @Override
    public byte[] serialize(User user) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(user);
            out.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Incorrect standard serialization process");
        }
    }

    @Override
    public User deSerialize(byte[] bytes) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInput in = new ObjectInputStream(bis)) {
            return (User) in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Incorrect standard de-serialization process");
        }
    }
}
