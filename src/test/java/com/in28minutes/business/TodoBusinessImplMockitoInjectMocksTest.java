package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.in28minutes.data.api.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {

  // @Rule
  // public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  TodoService todoServiceMock;

  @InjectMocks
  TodoBusinessImpl todoBusinessImpl;

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @Test
  public void testRetrieveTodosRelatedToSpring_usingAMock() {

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance");

    when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

    assertEquals(2, filteredTodos.size());
  }

  @Test
  public void testRetrieveTodosRelatedToSpring_usingBDD() {

    // Given - setUp

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance");

    given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

    // When - Actual call! NOT WHEN(...).then...
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

    // Then
    assertThat(filteredTodos.size(), is(2));
  }

  @Test
  public void testRetrieveTodosRelatedToSpring_emptyList() {

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance");

    when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(new ArrayList());
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

    assertEquals(0, filteredTodos.size());
  }

  @Test
  public void testDeleteTodosNotRelatedToSpring_usingBDD() {

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

    // When - Actual call! NOT WHEN(...).then...
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

    // Then
    verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
    // then(todoServiceMock).should().deleteTodo("Learn to Dance");

    verify(todoServiceMock, never()).deleteTodo("Learn Spring");
    verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
  }

  @Test
  public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
    // declare argument captor

    List<String> todos = Arrays.asList("Learn to Rock", "Learn Spring", "Learn to Dance");

    given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

    // When - Actual call! NOT WHEN(...).then...
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

    // Then
    // Ca[ture captor
    verify(todoServiceMock, atLeastOnce()).deleteTodo(stringArgumentCaptor.capture());

    // assert what he is
    assertThat(stringArgumentCaptor.getAllValues().size(), is(2));

  }
}
