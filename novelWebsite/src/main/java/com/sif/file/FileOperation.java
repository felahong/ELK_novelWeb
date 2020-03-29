package com.sif.file;

import java.io.*;

/**
 * @program: BrotherNovel
 * @description: 存储文章
 * @author: Mr.Wang
 * @create: 2019-01-04 08:51
 **/
public class FileOperation {

  /**
  * @Description:创建文件
  * @Param: [fileName]
  * @return: boolean
  * @Author: xifujiang
  * @Date: 2019/1/4
  */
    public static boolean createFile(File fileName)throws Exception{

        File fileParent = fileName.getParentFile();
        /*创建目录*/
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        /*创建文件*/
        boolean flag=false;
        try{
            if(!fileName.exists()){
                fileName.createNewFile();
                flag=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @Description:读取txt文件
     * @Param: [fileName]
     * @return: boolean
     * @Author: xifujiang
     * @Date: 2019/1/4
     */
    public static String readTxtFile(File fileName)throws Exception{
        String result=null;
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try{
            fileReader=new FileReader(fileName);
            bufferedReader=new BufferedReader(fileReader);
            try{
                String read=null;
                while((read=bufferedReader.readLine())!=null){
                    result=result+read+"\r\n";
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(fileReader!=null){
                fileReader.close();
            }
        }
        System.out.println("读取出来的文件内容是："+"\r\n"+result);
        return result;
    }

    /** 
    * @Description: 写文件 
    * @Param: [content, fileName] 
    * @return: boolean 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */ 
    public static boolean writeTxtFile(String content,File  fileName)throws Exception{
        RandomAccessFile mm=null;
        boolean flag=false;
        FileOutputStream o=null;
        try {
            o = new FileOutputStream(fileName);
            o.write(content.getBytes("UTF-8"));
            o.close();
//   mm=new RandomAccessFile(fileName,"rw");
//   mm.writeBytes(content);
            flag=true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            if(mm!=null){
                mm.close();
            }
        }
        return flag;
    }


    /** 
    * @Description: 更新内容
    * @Param: [filePath, content] 
    * @return: void 
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */ 
    public static void contentToTxt(String filePath, String content) {
        String str = new String(); //原有txt内容
        String s1 = new String();//内容更新
        try {
            File f = new File(filePath);
            if (f.exists()) {
                System.out.print("文件存在");
            } else {
                System.out.print("文件不存在");
                f.createNewFile();// 不存在则创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((str = input.readLine()) != null) {
                s1 += str + "\n";
            }
            System.out.println(s1);
            input.close();
            s1 += content;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /** 
    * @Description: 创建目录
    * @Param:  
    * @return:  
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */
    public static void createMkdirs(File fileName){
        if(!fileName.exists()){//如果文件夹不存在
            fileName.mkdir();//创建文件夹
        }
    }
    
    /** 
    * @Description: 删除目录 及其子目录
    * @Param:  
    * @return:  
    * @Author: xifujiang
    * @Date: 2019/1/4 
    */
    public static void deleteMkdirs(File fileName){
        String path = fileName.getPath();
        if (!fileName.isDirectory()) {
            fileName.delete();
        }else if(fileName.isDirectory()) {
            String[] filelist = fileName.list();
            for (int i = 0; i < filelist.length; i++) {
                File delfile = new File(path + "\\" + filelist[i]);
                if (!delfile.isDirectory()) {
                    delfile.delete();
                    System.out.println(delfile.getAbsolutePath() + "删除文件成功");
                } else if (delfile.isDirectory()) {
                    deleteMkdirs(new File(path + "\\" + filelist[i]));
                }
            }
            System.out.println(fileName.getAbsolutePath() + "删除成功");
            fileName.delete();
        }
    }
}
