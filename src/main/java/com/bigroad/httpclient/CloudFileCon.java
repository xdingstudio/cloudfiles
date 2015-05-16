package com.bigroad.httpclient;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CloudFileCon {

	String uriForward;
	String token;

	boolean verifyUser() {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://172.31.34.114:8080/auth/v1.0");
		httpGet.setHeader("X-Storage-User", "system:root");
		httpGet.setHeader("X-Storage-Pass", "testpass");
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (response.getStatusLine().getStatusCode() == 200) {
			Header urlHeader = response.getFirstHeader("X-Storage-Url");
			Header tokenHeader = response.getFirstHeader("X-Auth-Token");
			uriForward = urlHeader.getValue();
			token = tokenHeader.getValue();
			System.out.println(uriForward+token+"ssssssssssssssssssssssssssss");
			return true;
		}
		return false;
	}

	
	public String uploadFile(String container, String fileName, File file) {
		if (verifyUser()) {

			String uploadUri = uriForward + "/" + container + "/" + fileName;	
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPut httpPut = new HttpPut(uploadUri);
			httpPut.setHeader("X-Auth-Token", token);
			FileBody fileBody = new FileBody(file);
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addPart("file", fileBody).build();
			httpPut.setEntity(reqEntity);
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httpPut);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 201) {
				try {
					response.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				HttpHead httpHead = new HttpHead(uploadUri);
				httpHead.setHeader("X-Auth-Token", token);			
				try {
					response = httpclient.execute(httpHead);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (response.getStatusLine().getStatusCode() == 200) {
			
					Header length = response.getFirstHeader("Content-Length");

					return length.getValue();
				}
				
			} else
				return null;
		}
		return null;
	}
	
	public boolean deleteFile(String filePath){
		if(verifyUser()){
			String uploadUri = uriForward + "/" + filePath;	
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpDelete httpDelete =new HttpDelete(uploadUri);
			httpDelete.addHeader("X-Auth-Token", token);
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httpDelete);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (response.getStatusLine().getStatusCode() == 204) {
		
				return true;
			}
			return false;
			
		}
		return false;
	}
	
}