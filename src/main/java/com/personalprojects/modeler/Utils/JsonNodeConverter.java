//package com.personalprojects.modeler.Utils;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class JsonNodeConverter implements AttributeConverter<JsonNode,String> {
//
//    public static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public String convertToDatabaseColumn(JsonNode jsonNode) {
//        try{
//            return objectMapper.writeValueAsString(jsonNode);
//        }catch (Exception e) {
//            throw new IllegalArgumentException("Error converting JsonNode to String", e);
//
//        }
//    }
//
//    @Override
//    public JsonNode convertToEntityAttribute(String s) {
//        try{
//            return objectMapper.readTree(s);
//        } catch (JsonProcessingException e) {
//            throw new IllegalArgumentException("Error converting String to JsonNode", e);
//        }
//    }
//}
