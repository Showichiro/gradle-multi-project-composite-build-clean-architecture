package com.example.usecase.order.dto;

public class TestOrderSearchData implements IOrderSearchData {
  private final int size;
  private final int page;

  public TestOrderSearchData(int size, int page) {
    this.size = size;
    this.page = page;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int page() {
    return page;
  }
}
