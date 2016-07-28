package fangji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.csvreader.CsvWriter;


public class fangji {
	
	static String tab = ",";

	public static List<String[]> readHerbFile(String filePath) {
		List<String[]> list = new ArrayList<String[]>();	
		/**
		 *载入文件 
		 */
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			
			if(file.isFile() && file.exists()) { //判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null) {		 
					list.add(lineTxt.split("/s"));
				}
				read.close();
			} else{
				System.out.println("找不到指定的文件1");
            }	//else end
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}	//catch end
		return list;
	}	//readHerbFile end
	
	
	public static String returnDis(HashMap<String, List<String[]>> map, String conString) {
		StringBuffer buffer = new StringBuffer();
		for(String it : map.keySet()){
			for( String[] item : map.get(it)) {
				for(String ele : item){		
					if(conString.contains(ele) && !buffer.toString().contains(it)) {
						buffer.append(it);
						buffer.append(tab);
					}
				}
			}
		}
		if(buffer.toString() != "") {
			return buffer.toString();
		}else {
		return "Not Found";
		}
	}

	public static String returnLau(HashMap<String, List<String[]>> map, String conString) {
		StringBuffer buffer = new StringBuffer();
//		if(buffer.toString().equals("")) {
//			System.out.println("fdsa");
//		}
//		buffer.append("");
		for(String it : map.keySet()){
			for( String[] item : map.get(it)) {
				for(String ele : item){		
//					System.out.println(ele);

					if(conString.contains(ele) && !buffer.toString().contains(it)) {

						buffer.append(it);
						buffer.append(tab);
					}
				}
			}
		}
		if(!buffer.toString().equals("")) {
//			System.out.println("fd");
			return buffer.toString();
			
		}else {
//			System.out.println("fdsafdsa");
		return "Not Found" + tab;
		}
	}

	public static HashMap<String, List<String[]>> readDisFile(String filePath) {
		
		String key = new String();
		HashMap<String, List<String[]>> map = new HashMap<String, List<String[]>>();
		
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
		
			if(file.isFile() && file.exists()) { //判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null) {
				
					if(lineTxt.contains("StartSignal:")) {
						key = lineTxt.replace("StartSignal:", "");
					}else {
						List<String[]> list = new ArrayList<String[]>();
						list.add(lineTxt.split("、"));
						map.put(key, list);
					}
				}
				read.close();
			} else{
				System.out.println("找不到指定的文件2");
            }	//else end
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}	//catch end
		return map;
	}	//readDisFile end
	
	
	public static HashMap<String, List<String[]>> readLauFile(String filePath) {
		
		String key = new String();
		HashMap<String, List<String[]>> map = new HashMap<String, List<String[]>>();
		
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
		
			if(file.isFile() && file.exists()) { //判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null) {
				
					if(lineTxt.contains("StartSignal:")) {
						key = lineTxt.replace("StartSignal:", "");
					}else {
						List<String[]> list = new ArrayList<String[]>();
						list.add(lineTxt.split("、"));
						map.put(key, list);
					}
				}
				read.close();
			} else{
				System.out.println("找不到指定的文件3");
            }	//else end
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}	//catch end
		return map;
	}	//readDisFile end

	public static void outCvsFile(String filePathData, String filePathDis, String filePathLua, String herbNameOne, String herbNameTwo) {

		List<String[]> list = new ArrayList<String[]>();
		HashMap<String, List<String[]>> mapOne = new HashMap<String, List<String[]>>();
		HashMap<String, List<String[]>> mapLau = new HashMap<String, List<String[]>>();
		mapOne = readDisFile(filePathDis);
		mapLau = readLauFile(filePathLua);

//		int count = 0;
		
		/**
		 *载入文件 
		 */
		try {
			String encoding = "UTF-8";
			File file = new File(filePathData);
			
			if(file.isFile() && file.exists()) { //判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null) {		 
					list.add(lineTxt.split("\n"));
				}
				read.close();
			} else{
				System.out.println("找不到指定的文件4");
            }	//else end
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}	//catch end
		

		String seqNum = new String();
		String  presName = new String();
		String printStr = new String();
		boolean sign = false;
		
		
		String csvFilePath = "C:\\Users\\Sea\\workspace\\fangji\\" + herbNameTwo +".csv";  
        CsvWriter wr =new CsvWriter(csvFilePath,',',Charset.forName("GBK"));  
		
        String titleCsv = "序号" + tab + "方剂名称" + tab + "草药1" + tab + "草药1用量" + tab + "草药2" + tab + "草药2用量" + tab + 
        		"比例" + tab + "是否炙(药1)" + tab + "是否炙(药2)" + tab + "科属" + tab + " " + tab + " " + tab + " " + tab + 
        		" " + tab + " " + tab + " " + tab + "疾病";
		String[] titleObj = titleCsv.split(",");
		try {  			                          
	         wr.writeRecord(titleObj);  
//	         wr.endRecord();
	         wr.flush();
	     } catch (IOException e) {  
	        e.printStackTrace();  
	     }  
        
        
        
        
        
        
		for(String[] temp : list) {
			
			if(temp[0].contains("序号")) {
				seqNum = temp[0].replace("序号： ", "");
			}
			
			if(temp[0].contains("名称")) {
				presName = temp[0].replace("【名称】 ", "");
			}

			
			if(temp[0].contains("【组成】") && temp[0].contains(herbNameTwo)) {		
				sign = true;
				String string = temp[0] + " ";
				String herbOneBroil = new String();
				String herbTwoBroil = new String();
				
				Pattern patternHerbOne = Pattern.compile("\\s(\\S*" + herbNameOne + "\\S*)\\s");
				Pattern patternHerbTwo = Pattern.compile("\\s(\\S*" + herbNameTwo + "\\S*)\\s");
				Matcher matcherHerbOne = patternHerbOne.matcher(string);
				Matcher matcherHerbTwo = patternHerbTwo.matcher(string);
				boolean judgeHerbOneBroil = matcherHerbOne.find();
				boolean judgeHerbTwoBroil = matcherHerbTwo.find();

				if(judgeHerbOneBroil && (matcherHerbOne.group(1).contains("炙") | matcherHerbOne.group(1).contains("炒") 
						| matcherHerbOne.group(1).contains("制"))) {
					herbOneBroil = "炙";
				}else {
					herbOneBroil = "不炙";
				}
				
				if(judgeHerbTwoBroil && (matcherHerbTwo.group(1).contains("炙") | matcherHerbOne.group(1).contains("炒") 
						| matcherHerbOne.group(1).contains("制"))) {
					herbTwoBroil = "炙";
				}else {
					herbTwoBroil = "不炙";
				}
				
				StringBuffer buffer = new StringBuffer();
				
				Pattern patternHerbOneDose = Pattern.compile("(" + herbNameOne + ")[^\\d* & ^等分]*(\\d+\\.?\\d*)g?", Pattern.COMMENTS);
				Pattern patternHerbTwoDose = Pattern.compile("(" + herbNameTwo + ")[^\\d* & ^等分]*(\\d+\\.?\\d*)g?", Pattern.COMMENTS);
				Pattern patternDivOne = Pattern.compile("(" + herbNameTwo + ")[^\\d*]*(" + herbNameOne + ")[^\\d*]*(等分)", Pattern.COMMENTS);
				Pattern patternDivTwo = Pattern.compile("(" + herbNameOne + ")[^\\d*]*(" + herbNameTwo + ")[^\\d*]*(等分)", Pattern.COMMENTS);
				Matcher matcherHerbOneDose = patternHerbOneDose.matcher(string);
				Matcher matcherHerbTwoDose = patternHerbTwoDose.matcher(string);
				Matcher matcherDivOne = patternDivOne.matcher(string);
				Matcher matcherDivTwo = patternDivTwo.matcher(string);
				
				boolean judgeHerbOneDose= matcherHerbOneDose.find();
				boolean judgeHerbTwoDose= matcherHerbTwoDose.find();				
				boolean judgeDivOne= matcherDivOne.find();
				boolean judgeDivTwo= matcherDivTwo.find();
				
				if(judgeHerbOneDose && judgeHerbTwoDose && !judgeDivOne && !judgeDivTwo) {
					
					double per = Double.parseDouble(matcherHerbOneDose.group(2)) / Double.parseDouble(matcherHerbTwoDose.group(2));
					buffer.append(matcherHerbOneDose.group(1)); 
					buffer.append(tab); 
				    buffer.append(matcherHerbOneDose.group(2)); 
				    buffer.append("g" + tab); 
					buffer.append(matcherHerbTwoDose.group(1)); 
					buffer.append(tab); 
				    buffer.append(matcherHerbTwoDose.group(2)); 
				    buffer.append("g" + tab); 
				    buffer.append(String.valueOf(per));
				}else if(!judgeHerbOneDose && !judgeHerbTwoDose && judgeDivOne && !judgeDivTwo) {					
					buffer.append(matcherDivOne.group(1)); 
					buffer.append(tab); 
					buffer.append(" ");
					buffer.append(tab); 
				    buffer.append(matcherDivOne.group(2)); 
				    buffer.append(tab); 
				    buffer.append(" ");
				    buffer.append(tab); 
				    buffer.append("1");
				}else if(!judgeHerbOneDose && !judgeHerbTwoDose && !judgeDivOne && judgeDivTwo) {
					buffer.append(matcherDivTwo.group(1)); 
					buffer.append(tab); 
					buffer.append(" ");
					buffer.append(tab); 
				    buffer.append(matcherDivTwo.group(2)); 
				    buffer.append(tab); 
				    buffer.append(" ");			
				    buffer.append(tab); 
				    buffer.append("1");
				}else {
					sign = false;
					continue;
				}	//else end
				
				printStr = seqNum + tab + presName + tab + buffer.toString() + tab +
						herbOneBroil + tab + herbTwoBroil + tab;
			}	//if end
//			Integer max = maxLauNum( filePathData, filePathLua, herbNameOne, herbNameTwo);
//			System.out.println(max);
			if(temp[0].contains("主治") && sign) {
				String dis = returnDis(mapOne, temp[0]);
				String lau = returnLau(mapLau, temp[0]);
//				System.out.println(lau);
				String[] tempLen = lau.split(tab);
				Integer len = tempLen.length;
				for(int k = len; k <= 5; k++) {
					lau = lau + tab + " ";
				}
//				String printCsv = printStr + temp[0].replace("【主治】 ", "");
				String printCsv = printStr + lau + tab + dis;
				String[] obj = printCsv.split(",");
				try {  			                          
			         wr.writeRecord(obj);  
//			         wr.endRecord();
			         wr.flush();
			     } catch (IOException e) {  
			        e.printStackTrace();  
			     }  
				
//				System.out.println(printStr);
				sign = false;
//				count++;
			}	//if end
		}	//for end
		wr.close();  
	}	//readTxtFile end

	

	
	public static void main(String[] args) {
		
		String disFilePath = "C:\\Users\\Sea\\workspace\\fangji\\dis.txt";  
		String luaFilePath = "C:\\Users\\Sea\\workspace\\fangji\\lau.txt";  
		String dataFilePath = "C:\\Users\\Sea\\workspace\\fangji\\data.txt";
		String herbFilePath = "C:\\Users\\Sea\\workspace\\fangji\\herbName.txt";
		
		List<String[]> herbNameList = new ArrayList<String[]>();
		
		String herbNameOne = new String();
		String herbNameTwo = new String();
		herbNameOne = "甘草";
		herbNameList = readHerbFile(herbFilePath);
		
		for(String[] element : herbNameList) {
			herbNameTwo = element[0];
			outCvsFile(dataFilePath, disFilePath, luaFilePath, herbNameOne, herbNameTwo);
		}	//for end		
	}	//main end

}	//fangji end


