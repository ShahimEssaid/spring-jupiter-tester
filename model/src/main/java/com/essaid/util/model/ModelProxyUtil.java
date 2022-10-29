package com.essaid.util.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelProxyUtil {
  static public Pattern NORMALIZED_PATTERN = Pattern.compile("([A-Z])");
  
  static public String getNormalizedName(String methodName) {
    Matcher matcher = NORMALIZED_PATTERN.matcher(methodName);
    StringBuilder sb = new StringBuilder();
    while (matcher.find()) {
      matcher.appendReplacement(sb, "." + matcher.group(1).toLowerCase());
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

}
