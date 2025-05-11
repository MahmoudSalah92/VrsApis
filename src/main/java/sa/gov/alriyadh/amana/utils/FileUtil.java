package sa.gov.alriyadh.amana.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.DatatypeConverter;

public class FileUtil {

	public static String getSystemSeparator() {
		return File.separator;
	}

	public static boolean createFile(String filePath, String base64) {
		boolean created = false;
		OutputStream outputStream = null;
		try {
			File targetFile = new File(filePath);
			if (!targetFile.getParentFile().exists()) {
				created = targetFile.getParentFile().mkdirs();
			}
			if (!targetFile.exists()) {
				System.out.println("file not exsists");
				targetFile.createNewFile();
				byte[] data = DatatypeConverter.parseBase64Binary(base64);
				outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
				outputStream.write(data);
				outputStream.close();
				created = true;
			} else {
				System.err.println("file exsists");
				byte[] data = DatatypeConverter.parseBase64Binary(base64);
				outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
				outputStream.write(data);
				created = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return created;
	}

	public static String encodeFileToBase64Binary(File file) {
		String encodedfile = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = Base64.getEncoder().encodeToString(bytes).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStreamReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return encodedfile;
	}

	public static String convertPdfUrlToBase64(String pdfUrl) {
		String base64EncodedPdf = "";
		try {
			URL url = new URL(pdfUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			try (InputStream inputStream = connection.getInputStream();
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
				// Read the input stream into the byte array output stream
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, bytesRead);
				}
				// Convert the ByteArrayOutputStream to byte array
				byte[] pdfBytes = byteArrayOutputStream.toByteArray();
				// Encode the byte array to Base64
				base64EncodedPdf = Base64.getEncoder().encodeToString(pdfBytes);
				System.out.println(base64EncodedPdf);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64EncodedPdf;
	}

}
