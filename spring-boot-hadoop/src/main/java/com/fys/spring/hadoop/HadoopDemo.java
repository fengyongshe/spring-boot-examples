package com.fys.spring.hadoop;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HadoopDemo {

  public static void main(String[] args) throws Exception {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hadoop.xml");
    FileSystem fileSystem = (FileSystem) context.getBean("fileSystem");
    fileSystem.mkdirs(new Path("/tmp/springhdfs"));
    fileSystem.close();

  }
}
