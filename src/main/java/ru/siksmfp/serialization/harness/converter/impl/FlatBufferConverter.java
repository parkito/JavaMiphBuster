package ru.siksmfp.serialization.harness.converter.impl;

import com.google.protobuf.ByteString;
import ru.siksmfp.serialization.harness.converter.api.Converter;
import ru.siksmfp.serialization.harness.dto.proto.UserProto;
import ru.siksmfp.serialization.harness.model.standart.Address;
import ru.siksmfp.serialization.harness.model.standart.User;

import java.util.ArrayList;
import java.util.List;

public class FlatBufferConverter implements Converter<UserProto.User, User> {

    @Override
    public UserProto.User toModel(User dto) {
        return UserProto.User
                .newBuilder()
                .setId(dto.getId())
                .setName(dto.getName())
                .setSignature(ByteString.copyFrom(dto.getSignature()))
                .addAllAddresses(toModelAddress(dto.getAddresses()))
                .build();
    }

    @Override
    public User toDto(UserProto.User model) {
        User dto = new User();

        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSignature(model.getSignature().toByteArray());

        List<UserProto.Address> modelAddresses = model.getAddressesList();
        List<Address> addresses = new ArrayList<>(model.getAddressesCount());

        for (UserProto.Address modelAddress : modelAddresses) {
            Address address = new Address();

            address.setId(modelAddress.getId());
            address.setCity(modelAddress.getCity());
            address.setPopulation(modelAddress.getPopulation());
            addresses.add(address);
        }

        dto.setAddresses(addresses);

        return dto;
    }

    private List<UserProto.Address> toModelAddress(List<Address> addresses) {
        List<UserProto.Address> result = new ArrayList<>(addresses.size());
        for (Address dtoAddress : addresses) {
            result.add(UserProto.Address.newBuilder()
                    .setId(dtoAddress.getId())
                    .setCity(dtoAddress.getCity())
                    .setPopulation(dtoAddress.getPopulation())
                    .build()
            );
        }

        return result;
    }
}
