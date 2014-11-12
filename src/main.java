import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class main {
	static String ReadPath;
	static String WritePath;
	
    public static void main(String[] args) 
    {
    	
    	//testProxy.testProxyOnly();
    	//testProxy.getSOAPEndpointViaProxy();
    	ConfigLoader loader =  new ConfigLoader("soap.properties");
    	String SOAPMessageURL = loader.getData("SOAPMessageURL");
    	String marketoUserId = loader.getData("marketoUserId");
    	String marketoSecretKey = loader.getData("marketoSecretKey");
    	String proxyUri = loader.getData("proxyUri");
    	String proxyPort = loader.getData("proxyPort");
    	String proxyUsername = "";
    	String proxyPassword = "";
    	testGetLead.getLead(SOAPMessageURL,marketoUserId,marketoSecretKey,proxyUri,proxyPort,proxyUsername,proxyPassword, "test@test.com");
    	
    }
    
    // Test reading from a config and copying files 
    protected static void testCopyFile()
    {
    	ReadConfigFile();
    	
		String srcFile = ReadPath;
		String destFile = WritePath;
			
		testFileCopy tfc = new testFileCopy();
		
    	tfc.copyFile(srcFile, destFile);
    }
    protected static void ReadConfigFile()
{
	File configFile = new File("config.properties");
	
	
		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			
			ReadPath = props.getProperty("readpath");
			WritePath = props.getProperty("writepath");

			System.out.println("ReadPath name is: " + ReadPath);
			System.out.println("WritePath name is: " + WritePath);

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

    // Test getting leads through a proxy server
    protected static void testSOAPCallViaProxy()
    {
    	
    	// Call getLeads using proxy settings
    	//URL url = testProxy.getSOAPEndpointViaProxy("112.17.0.211", 80, "");
    	
    	//testGetLead.getLead(url, "test@test.com");
    
    }


}
    