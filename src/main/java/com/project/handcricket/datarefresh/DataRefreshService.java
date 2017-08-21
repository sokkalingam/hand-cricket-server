package com.project.handcricket.datarefresh;

import com.project.handcricket.mongodb.model.DataRefresh;
import com.project.handcricket.mongodb.repo.DataRefreshRepo;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataRefreshService {

  @Autowired
  private DataRefreshRepo dataRefreshRepo;

  public List<DataRefresh> getAll() {
    return this.dataRefreshRepo.findAll();
  }

  public DataRefresh getLatest() {
    return this.dataRefreshRepo.findTopByOrderByPlayerStatsDate();
  }

  public String deleteAll() {
    this.dataRefreshRepo.deleteAll();
    return "Deleted";
  }

  public boolean refreshData() {
    DataRefresh dataRefresh = this.dataRefreshRepo.findTopByOrderByPlayerStatsDate();

    if (dataRefresh == null || dataRefresh.getPlayerStatsDate() == null) {
      this.dataRefreshRepo.save(new DataRefresh());
      return false;
    }

    LocalDate today = new LocalDate();
    if (today.compareTo(dataRefresh.getPlayerStatsDate()) > 0) {
      this.dataRefreshRepo.deleteAll();
      this.dataRefreshRepo.save(new DataRefresh());
      return true;
    }

    return false;
  }
}
