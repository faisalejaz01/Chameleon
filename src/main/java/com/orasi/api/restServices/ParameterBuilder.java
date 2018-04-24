package com.orasi.api.restServices;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ParameterBuilder {
    @SuppressWarnings("unused")
    private List<NameValuePair> params = new ArrayList<>();

    public ParameterBuilder add(String key, String value) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("ParameterBuilder.Builder.key (" + key + ") is null or empty.");
        }

        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("ParameterBuilder.Builder.value (" + value + ") is null or empty.");
        }

        this.params.add(new BasicNameValuePair(key, value));

        return this;
    }

    public List<NameValuePair> build() {
        return params;
    }
}
