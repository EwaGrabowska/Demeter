package com.demeter.openiaapi.service;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ClassCodeGenerator {

    public static String generateClassCode(Class<?> clazz) {
        StringBuilder classCode = new StringBuilder();

        // Package
        Package pkg = clazz.getPackage();
        if (pkg != null) {
            classCode.append("package ").append(pkg.getName()).append(";\n\n");
        }

        classCode.append("import com.fasterxml.jackson.annotation.JsonPropertyDescription;\n")
                .append("import lombok.AllArgsConstructor;\n")
                .append("import lombok.Builder;\n")
                .append("import lombok.Data;\n")
                .append("import lombok.NoArgsConstructor;\n")
                .append("import java.util.List;\n\n");

        classCode.append(Modifier.toString(clazz.getModifiers())).append(" class ").append(clazz.getSimpleName()).append(" {\n\n");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            classCode.append(generateFieldCode(field)).append("\n");
        }

        Class<?>[] innerClasses = clazz.getDeclaredClasses();
        for (Class<?> innerClass : innerClasses) {
            classCode.append(generateInnerClassCode(innerClass)).append("\n");
        }

        classCode.append("}\n");

        return classCode.toString();
    }

    private static String generateFieldCode(Field field) {
        StringBuilder fieldCode = new StringBuilder();

        if (field.isAnnotationPresent(JsonPropertyDescription.class)) {
            JsonPropertyDescription annotation = field.getAnnotation(JsonPropertyDescription.class);
            fieldCode.append("    @JsonPropertyDescription(\"").append(annotation.value()).append("\")\n");
        }

        fieldCode.append("    ").append(Modifier.toString(field.getModifiers())).append(" ")
                .append(field.getType().getSimpleName()).append(" ")
                .append(field.getName()).append(";\n");

        return fieldCode.toString();
    }

    private static String generateInnerClassCode(Class<?> innerClass) {
        StringBuilder innerClassCode = new StringBuilder();

        innerClassCode.append("    ").append(Modifier.toString(innerClass.getModifiers())).append(" class ").append(innerClass.getSimpleName()).append(" {\n\n");

        Field[] fields = innerClass.getDeclaredFields();
        for (Field field : fields) {
            innerClassCode.append(generateFieldCode(field)).append("\n");
        }

        innerClassCode.append("    }\n");

        return innerClassCode.toString();
    }
}
