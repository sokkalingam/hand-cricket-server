package com.project.handcricket.helper;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class Helper {

  public static String getRandomID(int len) {
    return RandomStringUtils.randomNumeric(len);
  }

  public static void main(String[] args) {
    System.out.println(getRandomID(6));
  }

}
