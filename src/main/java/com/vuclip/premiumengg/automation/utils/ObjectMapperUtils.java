package com.vuclip.premiumengg.automation.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.vuclip.premiumengg.automation.common.Log4J;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class ObjectMapperUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param filepath
     * @param returnType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String filepath, Class<T> returnType) {
        Log4J.getLogger().debug("*** filepath: " + filepath);
        try {
            File f = new File(filepath);
            Log4J.getLogger().debug("Is File object is Null : " + (f == null) + "");
            return objectMapper.readValue(f, returnType);
        } catch (UnrecognizedPropertyException e) {
            Log4J.getLogger().debug("UnrecognizedPropertyException occurs : In JSON  ");
            Log4J.getLogger().debug(e.getMessage());
        } catch (JsonParseException e) {
            Log4J.getLogger().debug("JsonParseException occurs ");
            Log4J.getLogger().debug(e.getMessage());
        } catch (JsonMappingException e) {
            Log4J.getLogger().debug("JsonMappingException occurs ");
            Log4J.getLogger().debug(e.getMessage());
        } catch (IOException e) {

            Log4J.getLogger().debug("IOException occurs ");
            Log4J.getLogger().debug(e.getMessage());
        }

        return null;
    }

    /**
     * @param jsonObject
     * @param returnType
     * @param <T>
     * @return
     */
    public static <T> T readValueFromString(String jsonObject, Class<T> returnType) {
        try {
            return objectMapper.readValue(jsonObject, returnType);
        } catch (UnrecognizedPropertyException e) {
            Log4J.getLogger().debug("UnrecognizedPropertyException occurs  in JSON " + jsonObject);
            Log4J.getLogger().debug(e.getMessage());
        } catch (JsonParseException e) {
            Log4J.getLogger().debug("JsonParseException occurs In JSON " + jsonObject);
            Log4J.getLogger().debug(e.getMessage());
        } catch (JsonMappingException e) {
            Log4J.getLogger().debug("JsonMappingException occurs In JSON" + jsonObject);
            Log4J.getLogger().debug(e.getMessage());
        } catch (IOException e) {

            Log4J.getLogger().debug("IOException occurs   " + jsonObject);
            Log4J.getLogger().debug(e.getMessage());
        }

        return null;
    }

    /**
     * Object to JSON as String
     *
     * @param value
     * @return
     */
    public static String writeValueAsString(Object value) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            Log4J.getLogger().debug(e.getMessage());
        }
        return s;
    }

    /**
     * @param value
     * @param filepath
     * @return
     */
    public static boolean writeValueInFile(Object value, String filepath) {

        File f = new File(filepath);

        Log4J.getLogger().debug("Is File object is Null : " + (f == null) + "");
        try {

            objectMapper.writeValue(f, value);
            return true;
        } catch (JsonProcessingException e) {
            Log4J.getLogger().debug(e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * POJO to soap
     *
     * @param obj
     * @return
     */
    public static String marshall(Object obj) {
        String output = null;
        try {
            StringWriter sw = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.marshal(obj, sw);
            output = sw.toString();
        } catch (JAXBException jb) {
            Log4J.getLogger().debug("Error occured while marshalling object to xml {}" + jb.getMessage());
        }

        return output;
    }
}