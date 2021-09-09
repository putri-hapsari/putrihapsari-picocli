/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pico;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import picocli.CommandLine;

import static picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name = "mytools", description = "Test data Telkom Picocli", mixinStandardHelpOptions = true, version = "V.1.0.0")
class Picocli_Java implements Runnable {
    String userName;
    
    @Option(names = "-p", required = true, description = "Source Path.")
    String path;
    
    @Option(names = {"-ext", "-t"}, required = false, description = "extention.", defaultValue = "txt")
    String extension;
    
    @Option(names = {"-tgt", "-o"}, required = false, description = "Target Path.")
    String target;
    
 
    @Override
    public void run() {
        switch(extension){
            case "json" : break;
            default : extension = "txt"; break;
        }
        
        if(target==null){
            target = path.replace(".log", "."+ extension);
        }
        File file = new File(path);
        File targetFile = new File(target);
        
        if(target.endsWith(extension)){
            InputStream targetStream = null;
            try {
                targetStream = new FileInputStream(file);
                java.nio.file.Files.copy( targetStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);targetStream.close();
                System.out.println("Done");
            } catch (FileNotFoundException ex) {
                System.out.println("Error Process");
                Logger.getLogger(Picocli_Java.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.out.println("Error Process");
                Logger.getLogger(Picocli_Java.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            System.out.println("Target Extensing different from extension config");
        }
  
    }
    
    
    public static void main(String... args) {
      CommandLine.run(new Picocli_Java(), System.err, args);
    }
    
}
