package org.example;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        String filePath = "propertiesFile.properties";
        Injector injector = new Injector(filePath);

        SomeBean sb = injector.inject(new SomeBean());
        sb.foo();
    }
}