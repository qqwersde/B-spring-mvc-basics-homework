package com.thoughtworks.capacity.gtb.mvc.Controller;


import com.thoughtworks.capacity.gtb.mvc.Exception.FileException;
import com.thoughtworks.capacity.gtb.mvc.Exception.NoAuthorizedException;
import com.thoughtworks.capacity.gtb.mvc.Service.LightService;
import com.thoughtworks.capacity.gtb.mvc.model.Account;
import com.thoughtworks.capacity.gtb.mvc.model.LightAppZip;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LightController {
  private final LightService lightService;

  public LightController(LightService lightService) {
    this.lightService = lightService;
  }

  @GetMapping("/download/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public LightAppZip login(@PathVariable String id) {
    LightAppZip result = lightService.getLatestZipVersion(id);
    return result;
  }

  @GetMapping("/download")
  @ResponseStatus(HttpStatus.CREATED)
  public List<LightAppZip> login() {
    List<LightAppZip> result = lightService.getAllLatestZipVersions();
    return result;
  }


}
