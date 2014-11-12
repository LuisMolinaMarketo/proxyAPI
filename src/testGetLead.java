import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.commons.codec.binary.Hex;

import com.marketo.mktows.AuthenticationHeader;
import com.marketo.mktows.LeadKey;
import com.marketo.mktows.LeadKeyRef;
import com.marketo.mktows.MktMktowsApiService;
import com.marketo.mktows.MktowsPort;
import com.marketo.mktows.ParamsGetLead;
import com.marketo.mktows.SuccessGetLead;
import com.sun.xml.internal.fastinfoset.sax.Properties;

public class testGetLead{

	public static void getLead(
			String SOAPMessageURL,
			String marketoUserId,
			String marketoSecretKey,
			String proxyUri,
			String proxyPort,
			String proxyUsername,
			String proxyPassword,
			String email
	){
		
	System.out.println("Executing Get Lead");
		
	        try {
	            URL url = new URL(SOAPMessageURL);

	            URL marketoSoapEndPoint = url;
	            java.util.Properties systemProperties = System.getProperties();
	            systemProperties.setProperty("http.proxyHost",proxyUri);
	            systemProperties.setProperty("http.proxyPort",proxyPort);

	            
	            QName serviceName = new QName("http://www.marketo.com/mktows/", "MktMktowsApiService");
	            MktMktowsApiService service = new MktMktowsApiService(marketoSoapEndPoint, serviceName);
	            MktowsPort port = service.getMktowsApiSoapPort();
	 
	            // Create Signature
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	            String text = df.format(new Date());
	            String requestTimestamp = text.substring(0, 22) + ":" + text.substring(22);           
	            String encryptString = requestTimestamp + marketoUserId ;
	 
	            SecretKeySpec secretKey = new SecretKeySpec(marketoSecretKey.getBytes(), "HmacSHA1");
	            Mac mac = Mac.getInstance("HmacSHA1");
	            mac.init(secretKey);
	            byte[] rawHmac = mac.doFinal(encryptString.getBytes());
	            char[] hexChars = Hex.encodeHex(rawHmac);
	            String signature = new String(hexChars); 
	             
	            // Set Authentication Header
	            AuthenticationHeader header = new AuthenticationHeader();
	 
	            header.setMktowsUserId(marketoUserId);
	            header.setRequestTimestamp(requestTimestamp);
	            header.setRequestSignature(signature);
	             
	            // Create Request
	            ParamsGetLead request = new ParamsGetLead();
	 
	            LeadKey key = new LeadKey();
	            key.setKeyType(LeadKeyRef.EMAIL);
	            key.setKeyValue(email);
	            request.setLeadKey(key);
	 
	            SuccessGetLead result = port.getLead(request, header);
	 
	            JAXBContext context = JAXBContext.newInstance(SuccessGetLead.class);
	            Marshaller m = context.createMarshaller();
	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	            m.marshal(result, System.out);
	             
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
}

