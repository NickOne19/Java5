import org.example.Injector;
import org.example.SomeBean;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThrows;

public class InjectorTest {
    @Test
    public void testInject() throws Exception {
        String filePath = "propertiesFile.properties";
        Injector injector = new Injector(filePath);

        SomeBean sb = injector.inject(new SomeBean());

        assertNotNull(sb);
        assertNotNull(sb.getField1());
        assertNotNull(sb.getField2());
    }

    @Test
    public void testInjectNoProperties() {
        String filePath = "nonexistent.properties";
        Injector injector = new Injector(filePath);

        assertThrows(RuntimeException.class, () -> injector.inject(new SomeBean()));
    }
}
