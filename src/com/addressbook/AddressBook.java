package com.addressbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class AddressBook {
    private Map<String, Long> phoneIndex;
    private Map<String, Long> nameIndex;
    private RandomAccessFile storage;
    private long nextOffset;

    public AddressBook(String filename) throws IOException {
        this.phoneIndex = new HashMap<>();
        this.nameIndex = new HashMap<>();
        this.storage = new RandomAccessFile(filename, "rw");
        this.nextOffset = storage.length();
    }

    public void addContact(Contact contact) throws IOException {
        long offset = writeContact(contact);
        phoneIndex.put(contact.phoneNumber, offset);
        nameIndex.put(contact.firstName.toLowerCase() + " " + contact.lastName.toLowerCase(), offset);
    }

    private long writeContact(Contact contact) throws IOException {
        long offset = nextOffset;
        storage.seek(offset);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(contact);
        objectOutputStream.flush();

        byte[] data = byteArrayOutputStream.toByteArray();
        storage.writeInt(data.length);
        storage.write(data);

        nextOffset = storage.getFilePointer();

        return offset;
    }

    public Contact searchByPhoneNumber(String phoneNumber) throws IOException, ClassNotFoundException {
        if (phoneIndex.containsKey(phoneNumber)) {
            long offset = phoneIndex.get(phoneNumber);
            return readContact(offset);
        }
        return null;
    }

    public Contact searchByName(String name) throws IOException, ClassNotFoundException {
        if (nameIndex.containsKey(name.toLowerCase())) {
            long offset = nameIndex.get(name.toLowerCase());
            return readContact(offset);
        }
        return null;
    }

    private Contact readContact(long offset) throws IOException, ClassNotFoundException {
        storage.seek(offset);
        int length = storage.readInt();
        byte[] data = new byte[length];
        storage.readFully(data);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (Contact) objectInputStream.readObject();
    }

    public void close() throws IOException {
        storage.close();
    }
}
