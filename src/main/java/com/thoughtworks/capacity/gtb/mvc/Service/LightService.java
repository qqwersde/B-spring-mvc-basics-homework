package com.thoughtworks.capacity.gtb.mvc.Service;

import com.thoughtworks.capacity.gtb.mvc.Exception.FileException;
import com.thoughtworks.capacity.gtb.mvc.model.LightApp;
import com.thoughtworks.capacity.gtb.mvc.model.LightAppZip;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LightService {
  private final List<LightApp> lightAppList = new ArrayList<>();

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

  public List<LightAppZip> getAllLatestZipVersions() {
    List<LightAppZip> lightAppZips;
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
    return allLightAppZips;
  }

}
