package com.Codelabs.crimezone.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ApiConnection {

	public static String excuteGet(String url) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);

		InputStreamReader insReader = new InputStreamReader(response
				.getEntity().getContent());
		BufferedReader buffReader = new BufferedReader(insReader);
		StringBuilder total = new StringBuilder();
		String line;
		while ((line = buffReader.readLine()) != null) {
			total.append(line);
		}
		buffReader.close();

		return total.toString();

	}

}