package util;


import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;


public class DBPropertyUtil {

    public static String getConnString() {
        String connString = null;
        try {
            Properties props = new Properties();
            
            try (InputStream inputStream = DBPropertyUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
                props.load(inputStream);
                connString = props.getProperty("protocol") + "//" + props.getProperty("host") + ":" +
                        props.getProperty("port") + "/" + props.getProperty("database") +
                        "?user=" + props.getProperty("user") + "&password=" + props.getProperty("password") +
                        "&driver=" + props.getProperty("driver");
            }
        } catch (IOException ex) {
            System.out.println("Error occurred while loading the properties file");
            ex.printStackTrace();
        }
        return connString;
    }
}


