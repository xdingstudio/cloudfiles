package com.bigroad.httpclient;

import java.io.File;
import java.io.IOException;

import com.bigroad.util.MD5Check;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		MD5Check md5Check=new MD5Check();
		String path = "/home/hadoop/uploaded/RS02e.avi";
		File f=new File(path);
		String mString=md5Check.getFileMD5String(f);
		System.out.println(mString);
	}

}
