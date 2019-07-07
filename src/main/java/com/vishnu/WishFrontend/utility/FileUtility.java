package com.vishnu.WishFrontend.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.vishnu.WishBackend.model.Product;

public class FileUtility {
	
	static String pathFile="E:/Maven Projects/eshop/src/main/webapp/photos/project/";

	public void fileupload(Product product)
	{
		
		MultipartFile file=product.getProductImage();
		BufferedOutputStream bos=null;
		System.out.println("----------image"+file);
		try
		{
			
			byte byteArray[]=file.getBytes();
			System.out.println("----------image-----"+byteArray);
			System.out.println("Image name"+product.getProductName());
			System.out.println("Image Id===="+product.getProductId());
			FileOutputStream fos= new FileOutputStream(pathFile+product.getProductId()+".jpg");
			bos=new BufferedOutputStream(fos);
			bos.write(byteArray);
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				System.out.println(bos);
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void deleteFile(Product product)
	{
		
		File file= new File(pathFile+product.getProductId()+".jpg");
		file.delete();
	}
}
