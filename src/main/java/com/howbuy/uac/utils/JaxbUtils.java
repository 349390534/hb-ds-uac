package com.howbuy.uac.utils;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;


/**
 * Jaxb的工具类以及通过表达式进行验证的方法.
 *
 */
public class JaxbUtils {

    public static String marshall(final Object object) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(object.getClass().getPackage().getName());
        Marshaller marshaller = jc.createMarshaller();
        Writer writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }

    public static void marshall(final Object object, File file) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(object.getClass().getPackage().getName());
        Marshaller marshaller = jc.createMarshaller();
        OutputStream outputStream = new FileOutputStream(file);
        marshaller.marshal(object, outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public static <T extends Object> T unmarshall(final Class<T> clazz, final String xml) throws JAXBException {
        Reader reader = new StringReader(xml);
        JAXBContext jc = JAXBContext.newInstance(clazz.getPackage().getName());
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (T) unmarshaller.unmarshal(reader);
    }

    public static <T extends Object> T unmarshall(final Class<T> clazz, final File file) throws JAXBException, FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        JAXBContext jc = JAXBContext.newInstance(clazz.getPackage().getName());
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (T) unmarshaller.unmarshal(inputStream);
    }

//    public static void main(String[] args) {
//        try {
//            Groups groups = unmarshall(Groups.class, new File("E:\\svn\\merge\\howbuy-uaa\\src\\main\\resources\\mapping.xml"));
//            System.out.println(groups.getGroups().size());
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

}
