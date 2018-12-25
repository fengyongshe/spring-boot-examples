package com.fys.spring.hadoop;

import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HDFSController {

  @Autowired
  FsShell fsShell;

  @RequestMapping("/file")
  public void file() {
    for(FileStatus fileStatus : fsShell.ls("/tmp")) {
      System.out.println(">" + fileStatus.getPath());
    }
  }
}
