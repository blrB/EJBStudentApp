package by.bsuir.aipos.controller;

import by.bsuir.aipos.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StudentRestControllerTest {

    private StudentRestController studentRestController;
    private StudentService studentService;

    @Before
    public void init() {
        studentService = mock(StudentService.class);
        studentRestController = new StudentRestController();
        Whitebox.setInternalState(studentRestController, "studentService", studentService);
    }

    @Test
    public void doSave() throws Exception {
        studentRestController.doSave(anyObject());
        verify(studentService).save(anyObject());
    }

    @Test
    public void doGetByID() throws Exception {
        studentRestController.doGetByID(anyLong());
        verify(studentService).get(anyLong());
    }

    @Test
    public void doDeleteByID() throws Exception {
        studentRestController.doDeleteByID(anyLong());
        verify(studentService).delete(anyLong());
    }

    @Test
    public void doGetAll() throws Exception {
        studentRestController.doGetAll();
        verify(studentService).getAll();
    }

}