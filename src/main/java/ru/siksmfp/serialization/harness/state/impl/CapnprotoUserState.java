package ru.siksmfp.serialization.harness.state.impl;

import org.capnproto.ArrayOutputStream;
import org.capnproto.MessageBuilder;
import org.capnproto.Serialize;
import org.capnproto.StructList;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import ru.siksmfp.serialization.harness.model.capnp.UserModel;
import ru.siksmfp.serialization.harness.state.api.BenchmarkState;

import java.io.IOException;
import java.nio.ByteBuffer;

@State(Scope.Benchmark)
public class CapnprotoUserState implements BenchmarkState {
    public MessageBuilder outputMessage;
    public byte[] serializedUser;

    @Setup(Level.Trial)
    public void setUp() {
        outputMessage = new MessageBuilder();

        UserModel.User.Builder user = outputMessage.initRoot(UserModel.User.factory);
        user.setId(1);
        user.setName("name");
        user.setSignature("longUniqGoodUserSignatureForSecurity".getBytes());

        StructList.Builder<UserModel.Address.Builder> addresses = user.initAddresses(5);

        UserModel.Address.Builder address1 = addresses.get(0);
        address1.setId(1);
        address1.setCity("Moscow");
        address1.setPopulation(15_000_000);

        UserModel.Address.Builder address2 = addresses.get(1);
        address2.setId(2);
        address2.setCity("SPB");
        address2.setPopulation(5_000_000);

        UserModel.Address.Builder address3 = addresses.get(2);
        address3.setId(3);
        address3.setCity("Dzerzhinsk");
        address3.setPopulation(250_000);

        UserModel.Address.Builder address4 = addresses.get(3);
        address4.setId(4);
        address4.setCity("Vladivastok");
        address4.setPopulation(1_000_000);

        UserModel.Address.Builder address5 = addresses.get(4);
        address5.setId(5);
        address5.setCity("Tokio");
        address5.setPopulation(30_000_000);

        try (ArrayOutputStream os = new ArrayOutputStream(ByteBuffer.allocate(1024))) {
            Serialize.write(os, outputMessage);
            serializedUser = os.getWriteBuffer().array();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
