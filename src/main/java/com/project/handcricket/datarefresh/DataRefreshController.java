package com.project.handcricket.datarefresh;

import com.project.handcricket.mongodb.model.DataRefresh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dataRefresh")
public class DataRefreshController {

  @Autowired
  private DataRefreshService dataRefreshService;

  @RequestMapping("/getAll")
  public List<DataRefresh> getAll() {
    return this.dataRefreshService.getAll();
  }

  @RequestMapping("/getLatest")
  public DataRefresh getLatest() {
    return this.dataRefreshService.getLatest();
  }

  @RequestMapping("/clearAll")
  public String deleteAll() {
    return this.dataRefreshService.deleteAll();
  }

  @RequestMapping("/refresh")
  public boolean dataRefresh() {
    return this.dataRefreshService.refreshData();
  }

}
