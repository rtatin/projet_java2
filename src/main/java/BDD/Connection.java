package BDD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Connection {
	
    private static MysqlDataSource dataSource;
    
    public static DataSource getDataSource() {
        Properties props =new Properties();
        try (FileInputStream fils=new FileInputStream("conf.properties")){
            props.load(fils);
            if (dataSource == null) {
                dataSource = new MysqlDataSource();
                dataSource.setUrl(props.getProperty( "jdbc.url" ));
                dataSource.setUser(props.getProperty( "jdbc.login" ));
                dataSource.setPassword(props.getProperty( "jdbc.password" ));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataSource;
    }
}
