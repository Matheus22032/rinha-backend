package com.rinha.rinhabackend.pessoas;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return strings != null ? String.join(SPLIT_CHAR, strings): "";
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return !s.isEmpty() ? Arrays.asList(s.split(SPLIT_CHAR)): null;
    }
}
