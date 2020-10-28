package com.thoughtworks.capacity.gtb.mvc.Controller;


import com.thoughtworks.capacity.gtb.mvc.Exception.FileException;
import com.thoughtworks.capacity.gtb.mvc.Exception.NoAuthorizedException;
import com.thoughtworks.capacity.gtb.mvc.Service.LightService;
import com.thoughtworks.capacity.gtb.mvc.model.Account;
import com.thoughtworks.capacity.gtb.mvc.model.LightApp;
import com.thoughtworks.capacity.gtb.mvc.model.LightAppZip;
import io.sigpipe.jbsdiff.InvalidHeaderException;
import org.apache.commons.compress.compressors.CompressorException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class LightController {
  private final LightService lightService;

  public LightController(LightService lightService) {
    this.lightService = lightService;
  }

  @PatchMapping("/download/{lightId}")
  @ResponseStatus(HttpStatus.CREATED)
  public LightAppZip getLatestVersion(
          @PathVariable String lightAId,
          @RequestBody String id) {
    LightAppZip result = lightService.getLatestZipVersion(id);
    return result;
  }

  @GetMapping("/download/all")
  @ResponseStatus(HttpStatus.OK)
  public void download(HttpServletResponse response) throws IOException, CompressorException, InvalidHeaderException {
    lightService.download(response);
  }

  @PostMapping("/download/file")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<InputStreamResource> login() throws IOException {
    return lightService.downloadFile();
  }


}
