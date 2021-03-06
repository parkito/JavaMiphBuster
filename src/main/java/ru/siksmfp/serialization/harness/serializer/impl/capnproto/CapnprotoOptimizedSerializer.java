package ru.siksmfp.serialization.harness.serializer.impl.capnproto;

import org.capnproto.MessageBuilder;
import org.capnproto.MessageReader;
import ru.siksmfp.serialization.harness.converter.api.Converter;
import ru.siksmfp.serialization.harness.converter.impl.capnpoto.CapnprotoBuilderConverter;
import ru.siksmfp.serialization.harness.converter.impl.capnpoto.CapnprotoReaderConverter;
import ru.siksmfp.serialization.harness.model.standart.User;
import ru.siksmfp.serialization.harness.serializer.api.Serializer;

public class CapnprotoOptimizedSerializer implements Serializer<User> {

    private Converter<MessageBuilder, User> messageConverter = new CapnprotoBuilderConverter();
    private Converter<MessageReader, User> readerConverter = new CapnprotoReaderConverter();

    @Override
    public byte[] serialize(User user) {
        MessageBuilder messageBuilder = messageConverter.toModel(user);
        return CapnprotoSerializationUtils.write(messageBuilder);
    }

    @Override
    public User deSerialize(byte[] bytes) {
        MessageReader read = CapnprotoSerializationUtils.read(bytes);
        return readerConverter.toDto(read);
    }
}
