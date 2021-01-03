package com.crisper.server.oldImpl.fileManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class IFileContoller {
    ArrayList<File> songsList=new ArrayList<>();
    public String root="D:\\MP3";


    public void scan(){
        File folder = new File(root);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
       // System.out.println("Distance :" + songsList);
    }
    public void searchFileByKey(String key){

    }
    public List<File> searchFilesByDirectory(){
        File folder = new File(root);
        File[] listOfFiles = folder.listFiles();
        ArrayList<File> files=new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                files.add(listOfFiles[i]);
            } else if (listOfFiles[i].isDirectory()) {
            }
        }
        Collections.shuffle(files);
        return files;
    }
    public  ArrayList<File> searchSongsInDirectory(String song){
        songsList.clear();
        File folder = new File(root);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                 find(listOfFiles[i],song);
            } else if (listOfFiles[i].isDirectory()) {

            }
        }
        Collections.shuffle(songsList);
       return songsList;
    }
    public String removeUnwantedWords(String data){
        data=data.toLowerCase();
        List<String> words=new ArrayList<>();
        words.add(".com");
        words.add(".info");
        words.add("djmaza");
        words.add("www.");
        for(String w:words){
            w=w.toLowerCase();
            data=data.replace(w,"");
        }
        return data;
    }
    private ArrayList<File> find(File file,String data2){
        String data=file.getName();

        data=removeUnwantedWords(data);
        String[] da= StringUtils.split(data," -.[]_()");

        data2=data2.toLowerCase();
        String[] da2=StringUtils.split(data2," -.[]_()");

        ArrayList<String> arrayList=new ArrayList<>();


        for(int i=0;i<da2.length;i++){
            for(int j=0;j<da.length;j++){
                double distance2 = StringUtils.getJaroWinklerDistance(da2[i], da[j]);
                if(distance2>0.85){
                    arrayList.add(da[j]);
                }
            }
        }
        if(arrayList.size()>=da2.length){
            songsList.add(file);
        }
        return songsList;
    }
    public void getAnyFileInDirectory(String key){

    }
    public List<File> getListByDirectoryName(String key){
        songsList.clear();
        File folder = new File(root);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                if(isNameMatched(listOfFiles[i].getName(),key)){
                    return getFileList(listOfFiles[i].listFiles());
                }
            }
        }
        Collections.shuffle(songsList);
        return songsList;
    }

    private List<File> getFileList(File[] listFiles) {
        List<File> files=new ArrayList<>();
        for(File f:listFiles){
            if(f.isFile())
                files.add(f);
        }
        Collections.shuffle(files);
        return files;
    }

    private boolean isNameMatched(String directory, String key) {
        String[] songName,directoryName;
        songName=StringUtils.split(key," ");
        directoryName=StringUtils.split(directory," ");
        ArrayList<String> arrayList=new ArrayList<>();


        for(int i=0;i<directoryName.length;i++){
            for(int j=0;j<songName.length;j++){
                double distance2 = StringUtils.getJaroWinklerDistance(directoryName[i], songName[j]);
                if(distance2>0.85){
                    arrayList.add(songName[j]);
                }
            }
        }
        if(arrayList.size()>=songName.length){
            return true;
        }else return false;
    }
}
