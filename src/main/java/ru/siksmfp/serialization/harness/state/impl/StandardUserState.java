package ru.siksmfp.serialization.harness.state.impl;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import ru.siksmfp.serialization.harness.model.standart.User;
import ru.siksmfp.serialization.harness.serializer.api.Serializer;
import ru.siksmfp.serialization.harness.serializer.impl.StandardSerializer;
import ru.siksmfp.serialization.harness.state.api.InputState;
import ru.siksmfp.serialization.harness.state.api.OutputState;

@State(Scope.Benchmark)
public class StandardUserState implements OutputState<User>, InputState<User> {

    private Serializer<User> serializer;
    private byte[] serializedUser;
    private User user;

    @Override
    public User getInputObject() {
        return user;
    }

    @Override
    public Serializer<User> getSerializer() {
        return serializer;
    }

    @Override
    public byte[] getOutputObject() {
        return serializedUser;
    }

    @Override
    @Setup(Level.Trial)
    public void setUp() {
        InputUserState userState = new InputUserState();
        userState.setUp();

        user = userState.getInputObject();
        serializer = new StandardSerializer();
        serializedUser = serializer.serialize(userState.getInputObject());
    }
}