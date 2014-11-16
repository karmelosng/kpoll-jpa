package com.karmelos.kpoll.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServiceUtil {
	private static final String GOOGLE_SERVER_KEY = "AIzaSyClIxHxErCXCIwzvISTUIQRokhxsBA_ZQI";
	private static final String API_KEY = "AIzaSyClIxHxErCXCIwzvISTUIQRokhxsBA_ZQI";

	public static String post(PollContent content) {
		String serverResponse = null;
		try {

			// 1. URL
			URL url = new URL("https://android.googleapis.com/gcm/send");

			// 2. Open connection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 3. Specify POST method
			conn.setRequestMethod("POST");

			// 4. Set the headers
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=" + GOOGLE_SERVER_KEY);

			conn.setDoOutput(true);

			// 5. Add JSON data into POST request body
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String jsonCon = gson.toJson(content);
			// System.out.println(gson.toJson(content));

			// 5.2 Get connection output stream
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

			wr.write(jsonCon.getBytes());

			// 5.4 Send the request
			wr.flush();

			// 5.5 close
			wr.close();

			// 6. Get the response
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Response Code : " + responseCode);

				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// 7. Print result
				System.out.println(response.toString());
				serverResponse = response.toString();
			} else {
				serverResponse = "error";
			}

		} catch (MalformedURLException e) {
			serverResponse = "error";
		} catch (IOException e) {
			serverResponse = "error";
		}

		return serverResponse;
	}

	public static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[30];
		random.nextBytes(bytes);
		return bytes;
	}

	public static String bytetoString(byte[] input) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(input);
	}

	public static String md5(String input) {

		String md5 = null;

		if (null == input)
			return null;

		try {

			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}

	
}
