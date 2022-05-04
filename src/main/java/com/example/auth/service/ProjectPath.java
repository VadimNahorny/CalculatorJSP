package com.example.auth.service;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

public class ProjectPath {

    public static void setPropertyPath(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String prefix = req.getServletContext().getRealPath("");
        prefix = prefix.substring(0, prefix.lastIndexOf('\\'));
        prefix = prefix.substring(0, prefix.lastIndexOf('\\'));
        prefix = prefix.substring(0, prefix.lastIndexOf('\\'));
        prefix = prefix.replace("\\", "/");
        Properties properties = new Properties();
        FileInputStream log4jStream = new FileInputStream(prefix+"/src/main/resources/log4j.properties");
        properties.load(log4jStream);
        properties.replace("log4j.appender.file.File", prefix + "/calcLog.log");
        properties.store(new FileOutputStream(prefix+"/src/main/resources/log4j.properties"), null);
    }
}

