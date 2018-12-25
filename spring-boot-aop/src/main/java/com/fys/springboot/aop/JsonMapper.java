package com.fys.springboot.aop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonMapper {

  private static ObjectMapper objectMapper = new ObjectMapper();

  public static <T> String obj2Str(T src) {
    if (src == null) {
      return null;
    }
    try {
      return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
    } catch (IOException e) {
      log.error("【JSON 转换：对象 --> 字符串】，异常堆栈：{}", e);
      return null;
    }
  }

  public static <T> T str2Obj(String src, TypeReference<T> typeReference) {
    if (src == null || typeReference == null) {
      return null;
    }
    try {
      return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
    } catch (Exception e) {
      log.error("【JSON 转换：字符串 --> 对象】，异常堆栈：{}", e);
      return null;
    }
  }

}
