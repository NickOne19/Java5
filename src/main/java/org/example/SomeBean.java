package org.example;

/**
 * The type Some bean.
 */
public class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Foo.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }

    /**
     * Gets field 1.
     *
     * @return the field 1
     */
    public SomeInterface getField1() {
        return field1;
    }

    /**
     * Gets field 2.
     *
     * @return the field 2
     */
    public SomeOtherInterface getField2() {
        return field2;
    }
}
