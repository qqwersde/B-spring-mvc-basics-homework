package com.thoughtworks.capacity.gtb.mvc.Service;

import com.thoughtworks.capacity.gtb.mvc.Exception.FileException;
import com.thoughtworks.capacity.gtb.mvc.Exception.UserExistingException;
import com.thoughtworks.capacity.gtb.mvc.model.LightApp;
import com.thoughtworks.capacity.gtb.mvc.model.LightAppZip;
import io.sigpipe.jbsdiff.Diff;
import io.sigpipe.jbsdiff.InvalidHeaderException;
import io.sigpipe.jbsdiff.ui.FileUI;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LightService {
  private final List<LightApp> lightAppList = new ArrayList<>();

  @Value("${max-size}")
  private int size;

  public LightService() {
    LightAppZip lightAppZipOneVersionOne = new LightAppZip("AppOneZipOne", "AppOne", 100, 1);
    LightAppZip lightAppZipOneVersionTwo = new LightAppZip("AppOneZipTwo", "AppOne", 200, 2);
    final LightApp appOne = new LightApp("AppOne", Arrays.asList(lightAppZipOneVersionOne, lightAppZipOneVersionTwo));

    LightAppZip lightAppZipTwoVersionOne = new LightAppZip("AppTwoZipOne", "AppTwo", 100, 1);
    LightAppZip lightAppZipTwoVersionTwo = new LightAppZip("AppTwoZipTwo", "AppTwo", 300, 2);
    final LightApp appTwo = new LightApp("AppTwo", Arrays.asList(lightAppZipTwoVersionOne, lightAppZipTwoVersionTwo));

    lightAppList.add(appOne);
    lightAppList.add(appTwo);
  }

  public LightAppZip getLatestZipVersion(String id) {
    List<LightAppZip> lightAppZips = null;
    int max = 0;
    LightAppZip lightAppZip = null;
    for (LightApp l :
            lightAppList) {

      if (l.getId().equals(id)) {
        lightAppZips = l.getLightAppZips();
        max = lightAppZips.stream().mapToInt(LightAppZip::getVersion).max().orElse(-1);
      }
    }

    if ((lightAppZips == null)|| (max == -1)){
      throw FileException.notFound();
    }
    for (LightAppZip light:
         lightAppZips) {
      if (light.getVersion() == max){
        lightAppZip = light;
      }
    }
    return lightAppZip;
  }

  public void download(HttpServletResponse response) throws IOException, CompressorException, InvalidHeaderException {
    /*List<LightAppZip> lightAppZips;
    List<LightAppZip> allLightAppZips = null;
    int max;
    LightAppZip lightAppZip;

    for (LightApp l :
            lightAppList) {

        lightAppZips = l.getLightAppZips();
        max = lightAppZips.stream().mapToInt(LightAppZip::getVersion).max().orElse(-1);
        if ((lightAppZips == null)|| (max == -1)){
          throw FileException.notFound();
        }
      for (LightAppZip light:
              lightAppZips) {
        if (light.getVersion() == max){
          lightAppZip = light;
          allLightAppZips.add(lightAppZip);
        }
      }
    }
    return allLightAppZips;*/
    /*if (size>50){
      throw new UserExistingException("超过大小");
    }*/
    File file1 = new File("/Users/yinzhonghao/GTB/SpringMvc/B-spring-mvc-basics-homework/src/main/java/com/thoughtworks/capacity/gtb/mvc/Service/buddy lunch(1).pdf");
    File file2 = new File("/Users/yinzhonghao/GTB/SpringMvc/B-spring-mvc-basics-homework/src/main/java/com/thoughtworks/capacity/gtb/mvc/Service/buddy lunch(2).pdf");
    InputStream inputStream = new FileInputStream(file1);
    InputStream inputStream1 = new FileInputStream(file2);
    final byte[] bytes = IOUtils.toByteArray(inputStream);
    final byte[] bytes1 = IOUtils.toByteArray(inputStream1);

    response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
    response.setCharacterEncoding("UTF-8");
    OutputStream outputStream = response.getOutputStream();
    response.setHeader("Content-Disposition", "attachment;filename=" + file1.getName());
    /*int a = 0;
    while((a = inputStream.read()) != -1){
      outputStream.write(a);
      outputStream.flush();
    }
    outputStream.close();*/
    Diff.diff(bytes,bytes1,outputStream);


  }


  public ResponseEntity<InputStreamResource> downloadFile()
          throws IOException {
    FileSystemResource file = new FileSystemResource("/Users/yinzhonghao/GTB/SpringMvc/B-spring-mvc-basics-homework/src/main/java/com/thoughtworks/capacity/gtb/mvc/Service/1603423835376000.pdf");
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    final InputStreamResource aNull = new InputStreamResource(file.getInputStream(), "test");
    System.out.println(aNull.getDescription());
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentLength(file.contentLength())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(aNull);
  }

}
