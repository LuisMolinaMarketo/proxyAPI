import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ConfigLoader {
	String ReadPath ="";
	String WritePath ="";
	Properties props = null;
    public ConfigLoader(String configFileName)
{
	File configFile = new File(configFileName);
	//"config.properties"
	
		try {
			FileReader reader = new FileReader(configFile);
			props = new Properties();
			props.load(reader);

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
    
    public String getData(String tag){
		String data = props.getProperty(tag);
		System.out.println(tag + " name is: " + data);
    	return data;
    }

}
