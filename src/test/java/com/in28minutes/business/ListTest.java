package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

  @Test
  public void letsMockListSizeMethod() {
    List listMock = mock(List.class);
    when(listMock.size()).thenReturn(2);

    assertEquals(2, listMock.size());
    assertEquals(2, listMock.size());
    assertEquals(2, listMock.size());
  }

  @Test
  public void letsMockListSizeMethod_ReturnMultipleValues() {
    List listMock = mock(List.class);
    when(listMock.size()).thenReturn(2).thenReturn(3);

    assertEquals(2, listMock.size());
    assertEquals(3, listMock.size());

  }

  @Test
  public void letsMockListSizeGet() {
    List listMock = mock(List.class);
    when(listMock.get(anyInt())).thenReturn("in28Minutes");

    assertEquals("in28Minutes", listMock.get(0));
    assertEquals("in28Minutes", listMock.get(1));

  }

  @Test(expected = RuntimeException.class)
  public void letsMockListSizeGet_throwAnException1() {
    List listMock = mock(List.class);

    when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException("Something went wrong"));

    listMock.get(0);

  }

  @Test
  public void letsMockListSizeGet_usingBDD() {
    // Given
    List<String> listMock = mock(List.class);
    given(listMock.get(anyInt())).willReturn("in28Minutes");

    // When
    String firstElement = listMock.get(0);

    // Then
    assertThat(firstElement, is("in28Minutes"));
  }

  @Test(expected = RuntimeException.class)
  public void letsMockListSizeGet_throwAnException() {
    List listMock = mock(List.class);

    when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException("Something went wrong"));

    listMock.get(0);

  }

}
