package by.bsuir.aipos;

import by.bsuir.aipos.controller.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest")
public class StudentsApplication extends Application {

    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(
                StudentRestController.class,
                StudentGroupRestController.class,
                org.glassfish.jersey.jackson.JacksonFeature.class
        ));
    }

}
