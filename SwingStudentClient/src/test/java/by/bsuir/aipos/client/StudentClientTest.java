package by.bsuir.aipos.client;

import by.bsuir.aipos.PropertyUtils;
import by.bsuir.aipos.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({StudentClient.class, ClientBuilder.class, ObjectMapper.class, PropertyUtils.class })

public class StudentClientTest {

    private StudentClient restClient;
    private Client client;
    private ObjectMapper mapper;
    private WebTarget webTarget;
    private Invocation.Builder builder;
    private Response response;

    @Before
    public void init() throws Exception {
        PowerMockito.mockStatic(StudentClient.class);
        PowerMockito.mockStatic(PropertyUtils.class);
        when(PropertyUtils.getUrlStudentSave()).thenReturn("save-student");
        when(PropertyUtils.getUrlStudentGet()).thenReturn("get-student");
        when(PropertyUtils.getUrlStudentDelete()).thenReturn("delete-student");
        when(PropertyUtils.getUrlStudentsAll()).thenReturn("all-student");
        when(PropertyUtils.getUrlStudentGroupName()).thenReturn("group-name");
        when(PropertyUtils.getUrlStudentGroupsAll()).thenReturn("group-all");
        PowerMockito.mockStatic(ClientBuilder.class);
        client = mock(Client.class);
        when(ClientBuilder.newClient()).thenReturn(client);
        PowerMockito.mockStatic(ObjectMapper.class);
        mapper = mock(ObjectMapper.class);
        PowerMockito.whenNew(ObjectMapper.class).withNoArguments().thenReturn(mapper);
        webTarget = mock(WebTarget.class);
        when(client.target(anyString())).thenReturn(webTarget);
        builder = mock(Invocation.Builder.class);
        when(webTarget.request()).thenReturn(builder);
        when(webTarget.request(anyString())).thenReturn(builder);
        response = mock(Response.class);
        when(builder.post(anyObject())).thenReturn(response);
        when(builder.accept(anyString())).thenReturn(builder);
        when(builder.method(anyObject())).thenReturn(response);
        when(builder.get(String.class)).thenReturn("response");
        restClient = new StudentClient(mock(MainWindow.class));
        restClient.run();
    }

    @Test
    public void saveStudent() throws Exception {
        restClient.saveStudent(new Student());
        verify(client).target(PropertyUtils.getUrlStudentSave());
        verify(webTarget).request();
        verify(builder).post(Entity.json(anyString()));
    }

    @Test
    public void getStudent() throws Exception {
        restClient.getStudent(1);
        verify(client).target(PropertyUtils.getUrlStudentGet() + 1);
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get(String.class);
    }

    @Test
    public void deleteStudent() throws Exception {
        restClient.deleteStudent(1);
        verify(client).target(PropertyUtils.getUrlStudentDelete() + 1);
        verify(webTarget).request();
        verify(builder).method("DELETE");
    }

    @Test
    public void getStudents() throws Exception {
        restClient.getStudents();
        verify(client).target(PropertyUtils.getUrlStudentsAll());
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get(String.class);
    }

    @Test
    public void getStudentGroup() throws Exception {
        restClient.getStudentGroup("421702");
        verify(client).target(PropertyUtils.getUrlStudentGroupName() + "421702");
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get(String.class);
    }

    @Test
    public void getStudentGroups() throws Exception {
        restClient.getStudentGroups();
        verify(client).target(PropertyUtils.getUrlStudentGroupsAll());
        verify(webTarget).request(MediaType.APPLICATION_JSON);
        verify(builder).get(String.class);
    }


}