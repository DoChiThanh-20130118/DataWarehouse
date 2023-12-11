package DB;


import java.io.InputStream;
import java.util.Properties;

public class StagingConnect {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = StagingConnect.class.getResourceAsStream("/staging.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getJdbcUrl() {
    	String s=(new StringBuilder()).append(properties.getProperty("db.jdbcUrl")).append("://").append(getHost()).append(":").append(getPort()).append("/").append(getName()).toString();
//        return properties.getProperty("db.jdbcUrl")+"://"+ getHost()+ ":"+getPort()+"/"+getName();
        return s;
    }
    public static String getHost() {
		return properties.getProperty("db.host");
	}
    public static String getPort() {
		return properties.getProperty("db.port");
	}

    public static String getUsername() {
        return properties.getProperty("db.username");
    }

    public static String getPassword() {
        return properties.getProperty("db.password");
    }
    public static String getName() {
		return properties.getProperty("db.name");
	}
    
    public static String getCsvFilePath() {
    	String s = (new StringBuilder()).append(properties.getProperty("csv.filePath")).append("\\").append(getCsvNameFile()).toString();
//        return properties.getProperty("csv.filePath")+"\\"+getCsvNameFile();
        return s;
    }
    public static String getCsvNameFile() {
        return properties.getProperty("csv.nameFile");
    }
    public static void main(String[] args) {
		System.out.println(getJdbcUrl());
		System.out.println(getCsvFilePath());
	}
}

