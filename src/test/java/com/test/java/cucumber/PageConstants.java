package com.test.java.cucumber;

import java.text.MessageFormat;
import java.util.Properties;

public enum PageConstants {

 
HOMEURL("Home","HomeURL"),;
	
   
 private String pageName;
 private String pageUrlProp;

 private PageConstants(String pageName, String pageUrlProp) {
  this.pageName = pageName;
  this.pageUrlProp = pageUrlProp;
 }
 
 public String getPageName() {
  return pageName;
 }

// public String getPageUrlProp() {
//  return pageUrlProp;
// }
 
//Get Page URL for Current Page
public String getPageUrl(Properties prop) {
return getPageUrl(prop, pageUrlProp);
}
//If the domain name is null, default is tst env 
public static String getPageUrl(Properties prop, String pageUrlProp) {
String websiteEnv = System.getProperty("domain", "tst");
String domain = prop.getProperty(websiteEnv);
String pageUri = prop.getProperty(pageUrlProp);
return MessageFormat.format(pageUri, domain);
}


 public static PageConstants getFromName(String pageName) {
  for (PageConstants pageConstant : PageConstants.values()) {
   if (pageConstant.getPageName().equals(pageName)) {
    return pageConstant;
   }
  }
  return null;
 }
}


