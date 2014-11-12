import java.io.File;

import org.apache.commons.io.FileUtils;


public class testFileCopy 
{
	public void copyFile(String source, String dest){
		
		File srcFile = new File(source);
		File destFile = new File(dest);
		
		
		try {
						
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
