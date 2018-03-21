package com.easun.wxtool.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileChangeUtil {

	/**
	 * 获得指定文件的byte数组
	 * 
	 * @throws Exception
	 */
	public static byte[] getBytes(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		byte[] b = new byte[1024];
		int n;
		while ((n = fis.read(b)) != -1) {
			bos.write(b, 0, n);
		}
		fis.close();
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 根据byte数组，生成文件
	 */
	public static void getFile(byte[] bfile, String filePath) throws Exception {
		File file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		bos.write(bfile);
		bos.flush();
		bos.close();
		fos.close();
	}
}
