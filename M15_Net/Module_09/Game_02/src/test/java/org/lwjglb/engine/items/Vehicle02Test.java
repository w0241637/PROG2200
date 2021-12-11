package org.lwjglb.engine.items;

import junit.framework.TestCase;

import org.junit.Test;


public class Vehicle02Test extends TestCase {




    @Test //control
    public void testDoesItCollide00(){

        boolean expResult = true;

        boolean collide = true;

        assertEquals(expResult,collide);
    }

    @Test // outside threshold
    public void testDoesItCollide01(){

        boolean expResult = false;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(13.90f, 10.000f, 0.000f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.000f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult,collide);

    }

    @Test // inside threshold
    public void testDoesItCollide02(){

        boolean expResult = true;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(14.00f, 10.000f, 0.000f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.000f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult,collide);

    }


    @Test // far apart
    public void testDoesItCollide03(){

        boolean expResult = false;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(-15.00f, 10.000f, 0.000f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.000f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult,collide);

    }

    @Test //far apart no velocity
    public void testDoesItCollide04(){

        boolean expResult = false;
//                        Mesh[] addMeshTest01 = null;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(-15.00f, 10.000f, 0.000f);
        item01.setVelocity(-0.00f, 0.00000f, 0.00000f);

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.000f, 0.000f);
        item02.setVelocity(-0.00f, 0.00000f, 0.00000f);

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult,collide);


    }


    @Test // occupy same position
    public void testDoesItCollide05() {

        boolean expResult = true;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(-15.00f, 10.000f, 0.000f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);;

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(-15.00f, 10.000f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);;

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult, collide);
    }

    @Test // close no collide y axis
    public void testDoesItCollide06() {

        boolean expResult = false;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(15.00f, 8.000f, 0.000f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);;

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.000f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);;

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult, collide);
    }


    @Test // close no collide z axis
    public void testDoesItCollide07() {

        boolean expResult = false;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(15.00f, 10.000f, 1.200f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);;

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.000f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);;

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult, collide);
    }

    @Test // close no collide all axis
    public void testDoesItCollide08() {

        boolean expResult = false;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(16.20f, 11.200f, 1.200f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);;

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.000f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);;

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult, collide);
    }

    @Test // just outside threshold
    public void testDoesItCollide09(){

        boolean expResult = false;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(14.40f, 9.400f, 0.600f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.100f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult,collide);

    }

    @Test // just inside threshold
    public void testDoesItCollide10(){

        boolean expResult = true;

        Vehicle02 item01 = new Vehicle02(null);
        item01.setPosition(14.50f, 9.500f, 0.500f);
        item01.setVelocity(-0.005f, 0.000001f, 0.000001f);

        Vehicle02 item02 = new Vehicle02(null);
        item02.setPosition(15.00f, 10.100f, 0.000f);
        item02.setVelocity(-0.005f, 0.000001f, 0.000001f);

        boolean collide = item01.DoesItCollide(item02);

        assertEquals(expResult,collide);

    }

}
