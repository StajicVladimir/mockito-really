package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.in28minutes.data.api.TodoService;

public class TodoBusinessImplMockTest {

  @Test
  public void testRetrieveTodosRelatedToSpring_usingAMock() {

    TodoService todoServiceMock = mock(TodoService.class);

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance");

    when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

    assertEquals(2, filteredTodos.size());
  }

  @Test
  public void testRetrieveTodosRelatedToSpring_usingBDD() {

    // Given - setUp
    TodoService todoServiceMock = mock(TodoService.class);

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance");

    given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    // When - Actual call! NOT WHEN(...).then...
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

    // Then
    assertThat(filteredTodos.size(), is(2));
  }

  @Test
  public void testRetrieveTodosRelatedToSpring_emptyList() {

    TodoService todoServiceMock = mock(TodoService.class);

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Dance");

    when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(new ArrayList());
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

    assertEquals(0, filteredTodos.size());
  }

  @Test
  public void testDeleteTodosNotRelatedToSpring_usingBDD() {

    // Given - setUp
    TodoService todoServiceMock = mock(TodoService.class);

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    // When - Actual call! NOT WHEN(...).then...
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

    // Then
    verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");

    verify(todoServiceMock, never()).deleteTodo("Learn Spring");
    verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
  }
}
