package tk.thesuperlab.jea;

import java.io.IOException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Z JEAToken razredom knjižnica dobi dostop <i>(samo za branje)</i> do podatkov do vašega eAsistent profila.
 * @author JurijTSL
 */
public class JEAToken {
	String bearerToken = "";
	String childId = "";	
	

	/**
	 * @param uporabniskoIme - Vaše uporabniško ime za prijavo v eAsistent.
	 * @param geslo - Vaše geslo za prijavo v eAsistent.
	 */
	public JEAToken(String uporabniskoIme, String geslo) {
        OkHttpClient client = new OkHttpClient();
        
        RequestBody body = new FormBody.Builder()
        		.add("uporabnik", uporabniskoIme)
        		.add("geslo", geslo)
                .build();
        
        Request request = new Request.Builder()
                .url("https://www.easistent.com/p/ajax_prijava")
                .post(body)
                .build();
        
        try (Response response = client.newCall(request).execute()) {	
            String easistentResponse = response.body().string();
            JSONObject jsonDocument = new JSONObject(easistentResponse);
            String authStatus = jsonDocument.getString("status");
            
            if(authStatus.equals("ok")) {
                Request getWebsite = new Request.Builder().url("https://www.easistent.com").addHeader("Cookie", response.headers().get("Set-Cookie")).build();

                try (Response easistentWebsite = client.newCall(getWebsite).execute()) {	
                	String eaWebsite = easistentWebsite.body().string();
                    Document website = Jsoup.parse(eaWebsite, "utf-8");
                    
                    Elements metaElements = website.select("meta");
                    for (int i = 0; i < metaElements.size(); i++) {
                    	if(metaElements.get(i).attr("name").equals("access-token")) {
                    		bearerToken = metaElements.get(i).attr("content");
                    	} else if(metaElements.get(i).attr("name").equals("x-child-id")) {
                    		childId = metaElements.get(i).attr("content");
                    	}
					}
                }
            } else {
            	System.err.println("JEA Error: Napačno uporabniško ime ali geslo.");
            }
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
}