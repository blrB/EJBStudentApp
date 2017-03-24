package by.bsuir.aipos.controller;

import by.bsuir.aipos.service.StudentGroupService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StudentGroupRestControllerTest {

    private StudentGroupRestController studentGroupRestController;
    private StudentGroupService studentGroupService;

    @Before
    public void init() {
        studentGroupService = mock(StudentGroupService.class);
        studentGroupRestController = new StudentGroupRestController();
        Whitebox.setInternalState(studentGroupRestController, "studentGroupService", studentGroupService);
    }

    @Test
    public void doSave() throws Exception {
        studentGroupRestController.doSave(anyObject());
        verify(studentGroupService).save(anyObject());
    }

    @Test
    public void doGetByID() throws Exception {
        studentGroupRestController.doGetByID(anyLong());
        verify(studentGroupService).get(anyLong());
    }

    @Test
    public void doGetByName() throws Exception {
        studentGroupRestController.doGetByName(anyString());
        verify(studentGroupService).get(anyString());
    }

    @Test
    public void doDeleteByID() throws Exception {
        studentGroupRestController.doDeleteByID(anyLong());
        verify(studentGroupService).get(anyLong());
    }

    @Test
    public void doGetAll() throws Exception {
        studentGroupRestController.doGetAll();
        verify(studentGroupService).getAll();
    }

}