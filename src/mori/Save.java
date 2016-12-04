package mori;

import javax.swing.filechooser.FileFilter;
import java.io.*;

public class Save extends FileFilter{
    public boolean accept(File f){
        boolean ans; 
        if(f.getName().toLowerCase().endsWith(".jpeg")
		|| f.getName().toLowerCase().endsWith(".jpg")
		|| f.getName().toLowerCase().endsWith(".bmp")
		){
            ans = true;
        }else if(f.isDirectory()){
            ans = true;
        }else{
            ans = false;
        }
        return ans;
    }

    public String getDescription(){
        return "*";
    }
}


