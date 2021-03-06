package aharisu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;


/**
 * 
 * 同期的に各種データを取得するユーティリティクラス
 * 
 * @author aharisu
 *
 */
public final class DataGetter {
	
	public static String getHTML(Context context, int resRawId) throws IOException{
		InputStream in = context.getResources().openRawResource(resRawId);
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			StringBuilder builder = new StringBuilder();
			String s;
			while((s = reader.readLine()) != null) {
				builder.append(s);
				builder.append("\n");
			}
			
			return builder.toString();
	}
	
	public static JSONObject getJSONObject(String url) {
		byte[] raw = HttpClient.getByteArrayFromURL(url);
		String data = new String(raw);
		raw = null;
		
		JSONObject obj = null;
		try {
			obj = new JSONObject(data);
			if(!obj.getString("status").equals("OK")) {
				Log.i("status failure", obj.toString());
				obj = null;
			}
		} catch(JSONException e) {
			e.printStackTrace();
			obj = null;
		}
		
		return obj;
	}

}
