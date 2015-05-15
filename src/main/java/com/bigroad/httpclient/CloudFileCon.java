package com.bigroad.httpclient;

import java.io.File;
import java.io.IOException;



import java.util.ArrayList;

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

import antlr.collections.List;

public class CloudFileCon {

	String uriForward;
	String token;

	public String[] verifyUser() {

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
			String[] inf = {uriForward,token};
			return inf;
		}
		return null;
	}

	

	
	public boolean deleteFile(String filePath){
		
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
	
}
