package com.example.usecase.inventory.dto;

public class TestInventorySearchData implements IInventorySearchData {
  private final int size;
  private final int page;

  public TestInventorySearchData(int size, int page) {
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
