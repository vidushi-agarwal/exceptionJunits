package org.example;

import org.junit.Test;

import java.util.InvalidPropertiesFormatException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DemoTest {
    @Test
    public void testRegisterInstance() throws Exception {
        LoadBalancer loadBalancer = new LoadBalancer();
        String instance = "https://url1/";

            boolean output = loadBalancer.registerInstance(instance);
            assertEquals(output, true);

    }

    @Test
    public void testRegisterInstance1() {
        LoadBalancer loadBalancer = new LoadBalancer();
        String instance = "https://url1/";
        DuplicateException exception  = assertThrows(DuplicateException.class, () ->{
            loadBalancer.registerInstance(instance);
            loadBalancer.registerInstance(instance);

        });
        assertEquals(exception.getMessage(), "The instance "+instance+" is already registered");
    }

    @Test
    public void testRegisterInstance2() throws Exception {
        LoadBalancer loadBalancer = new LoadBalancer();
            for( int i=0;i<10;i++) {
                String instance = "https://url1/"+i;
                boolean output = loadBalancer.registerInstance(instance);
                assertEquals(output, true);
            }
        String instance = "https://url1/"+11;
        assertThrows(OutOfCapacityException.class,()->{
            loadBalancer.registerInstance(instance);
                });
    }

    @Test
    public void testRegisterInstanceTryCatch(){
        LoadBalancer loadBalancer = new LoadBalancer();
        String instance = "https://url1/";
        try {
            loadBalancer.registerInstance(instance);
        } catch (Exception e) {
           assertEquals(e,Exception.class);
        }
    }

    @Test
    public void testRegisterDuplicateInstanceTryCatch(){
        LoadBalancer loadBalancer = new LoadBalancer();

        try {
            for( int i=0;i<10;i++) {
                String instance = "https://url1/"+i;
                boolean output = loadBalancer.registerInstance(instance);
                assertEquals(output, true);
            }
            String instance = "https://url1/"+11;
            loadBalancer.registerInstance(instance);
        }
        catch (OutOfCapacityException e) {
            assertEquals(e.getMessage(),"registeredInstances is out of capacity ");
        }
        catch (Exception e) {
            assertEquals(e,Exception.class );
        }
    }

    @Test
    public void testInvalidInstance(){
        LoadBalancer loadBalancer = new LoadBalancer();
        String instance = "";
        assertThrows(InvalidPropertiesFormatException.class,()->{
            loadBalancer.registerInstance(instance);
        });

    }
}
