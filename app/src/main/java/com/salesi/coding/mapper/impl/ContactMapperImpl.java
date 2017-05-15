package com.salesi.coding.mapper.impl;

import android.support.annotation.Nullable;

import com.salesi.coding.entity.ContactEntity;
import com.salesi.coding.mapper.IContactMapper;
import com.salesi.coding.mapper.adapter.ContactsAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Mapper layer implementations
 *
 * Copyright © 2017 sales­i
 */
public class ContactMapperImpl implements IContactMapper {
    private final ContactsAPI mContactsAPI;

    private List<ContactEntity> contacts = null;

    public ContactMapperImpl(ContactsAPI contactsAPI) {
        mContactsAPI = contactsAPI;
        contacts = new ArrayList<>();
    }

    @Override
    public List<ContactEntity> fetchContacts() {
        Call<List<ContactEntity>> call = mContactsAPI.fetchContacts();
        try {
            Response<List<ContactEntity>> response = call.execute();
            contacts = response.body();
            return contacts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    @Nullable
    @Override
    public ContactEntity fetchContact(int position) {
        fetchContactsIfNecessary();
        ContactEntity contactFound = null;
        if (null != contacts && position < contacts.size()) {
            contactFound = contacts.get(position);
        }
        return contactFound;
    }

    private void fetchContactsIfNecessary() {
        if (contacts.size() == 0) {
            fetchContacts();
        }
    }
}
