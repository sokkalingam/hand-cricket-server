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

  public synchronized List<DataRefresh> getAll() {
    return this.dataRefreshRepo.findAll();
  }

  public synchronized DataRefresh getLatest() {
    return this.dataRefreshRepo.findTopByOrderByPlayerStatsDate();
  }

  public synchronized void deleteAll() {
    this.dataRefreshRepo.deleteAll();
  }

  public synchronized void write(DataRefresh dataRefresh) {
    this.dataRefreshRepo.save(dataRefresh);
  }

  public boolean refreshData() {

    DataRefresh dataRefresh = getLatest();

    if (dataRefresh == null || dataRefresh.getPlayerStatsDate() == null) {
      write(new DataRefresh());
      return false;
    }

    LocalDate today = new LocalDate();
    if (today.compareTo(dataRefresh.getPlayerStatsDate()) > 0) {
      deleteAll();
      write(new DataRefresh());
      return true;
    }

    return false;
  }

}
