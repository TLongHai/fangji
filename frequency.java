package frequency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csvreader.CsvWriter;

public class frequency {
	
	
public static HashMap<String, Integer> readNumFile(String filePath) {
		
		String key = new String();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
//		HashMap<String, Integer> mapCount = new HashMap<String, Integer>();
		
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			
			if(file.isFile() && file.exists()) { //�ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null) {
					
	
					if(lineTxt.contains("���")) {
						key = lineTxt.replace("��ţ�", "");
//						System.out.println(key);
					}
					
					if(lineTxt.contains("����ɡ�")){
						List<String[]> list = new ArrayList<String[]>();
						lineTxt = lineTxt.replace("����ɡ�", "");
						list.add(lineTxt.split(" "));
						Integer num = 0;
//						System.out.println(list);
						for(String[] item : list) {
							for(String ele : item) {
								num += 1;
							}
//							System.out.println(it);
						}
//						Integer num = list.size();
						map.put(key, num - 2);
					}
					
				}
				read.close();
			} else{
				System.out.println("�Ҳ���ָ�����ļ�");
            }	//else end
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}	//catch end
		return map;
	}	//readDisFile end
	
	
//public static HashMap<String, Integer> readCountFile(String filePath) {
//	
//	Integer one = 1;
////	HashMap<String, Integer> map = new HashMap<String, Integer>();
//	HashMap<String, Integer> mapCount = new HashMap<String, Integer>();
//	
//	try {
//		String encoding = "UTF-8";
//		File file = new File(filePath);
//	
//		if(file.isFile() && file.exists()) { //�ж��ļ��Ƿ����
//			
//			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
//			BufferedReader bufferedReader = new BufferedReader(read);
//			String lineTxt = null;
//			
//			while((lineTxt = bufferedReader.readLine()) != null) {
//			
//				if(lineTxt.contains("����ɡ�")){
//					List<String[]> list = new ArrayList<String[]>();
//					lineTxt = lineTxt.replace("����ɡ�", "");
////					System.out.println(lineTxt);
//					list.add(lineTxt.split(" "));
//					for(String[] item : list) {
//						for(String ele : item) {
//							if(mapCount.containsKey(ele)) {
//								
//								mapCount.put(ele, mapCount.get(ele) + one);
//							}else {
//								mapCount.put(ele, one);
//							}
//						}
//					}		
//				}
//			}
//		
//		read.close();
//		}else{
//			System.out.println("�Ҳ���ָ�����ļ�");
//        }	//else end
//	} catch (Exception e) {
//		System.out.println("��ȡ�ļ����ݳ���");
//		e.printStackTrace();
//	}	//catch end
//	return mapCount;
//}	//readDisFile end
//	
//	
//	
	
	
	
	
	
	public static void main(String[] args) {
		
		String filePath = "C:\\Users\\Sea\\workspace\\frequency\\herb.txt";  
		String tab = ",";

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map = readNumFile(filePath);
		
		String printStr = new String();
//		System.out.println("dsa");

		String csvFilePath = "C:\\Users\\Sea\\workspace\\frequency\\xuHao.csv";  
		
        CsvWriter wr =new CsvWriter(csvFilePath,',',Charset.forName("GBK"));  
  
		for(String it : map.keySet()){
			System.out.println(map.get(it));
			printStr = it + tab + map.get(it) + tab;		

			String[] obj = printStr.split(",");
			try {  			                          
		         wr.writeRecord(obj);  
//		         wr.endRecord();
		         wr.flush();
		     } catch (IOException e) {  
		        e.printStackTrace();  
		     }  
		}	//for end
		wr.close();  
		
		
//		HashMap<String, Integer> mapCount = new HashMap<String, Integer>();
//		mapCount = readCountFile(filePath);
//		String printStrFre = new String();
//		String csvFilePathFre = "C:\\Users\\Sea\\workspace\\frequency\\fre.csv";  
//		CsvWriter wrFre =new CsvWriter(csvFilePathFre,',',Charset.forName("GBK"));  
//		
//		for(String it : mapCount.keySet()){
////			System.out.println(mapCount.get(it));
//			printStrFre = it + tab + mapCount.get(it) + tab;		
//			String[] obj = printStrFre.split(",");
//			try {  			                          
//		         wrFre.writeRecord(obj);  
////		         wr.endRecord();
//		         wrFre.flush();
//		     } catch (IOException e) {  
//		        e.printStackTrace();  
//		     }  
//		}	//for end
//
//		wrFre.close();
	}	//for main
		
		


}
