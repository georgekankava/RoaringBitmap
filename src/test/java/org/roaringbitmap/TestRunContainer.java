package org.roaringbitmap;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.roaringbitmap.ArrayContainer.DEFAULT_MAX_SIZE;

public class TestRunContainer {

    @Test
    public void remove() {
        Container rc = new RunContainer();
        rc.add((short) 1);
        Container newContainer = rc.remove(1, 2);
        assertEquals(0, newContainer.getCardinality());
    }

    @Test(expected = IllegalArgumentException.class)
    public void iremoveInvalidRange1() {
        Container rc = new RunContainer();
        rc.iremove(10, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void iremoveInvalidRange2() {
        Container rc = new RunContainer();
        rc.iremove(0, 1<<20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void iremoveInvalidRange2() {
        Container rc = new RunContainer();
        rc.remove(0, 1<<20);
    }

    @Test
    public void iremove1() {
        Container rc = new RunContainer();
        rc.add((short) 1);
        rc.iremove(1, 2);
        assertEquals(0, rc.getCardinality());
    }

    @Test
    public void iremove2() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iadd(20, 30);
        rc.iremove(0, 21);
        assertEquals(9, rc.getCardinality());
        for(short k=21; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(8, rc.getSizeInBytes());
    }

    @Test
    public void iremove3() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iadd(20, 30);
        rc.iadd(40, 50);
        rc.iremove(0, 21);
        assertEquals(19, rc.getCardinality());
        for(short k=21; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=40; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iremove4() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iremove(0, 5);
        assertEquals(5, rc.getCardinality());
        for(short k=5; k<10; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(8, rc.getSizeInBytes());
    }

    @Test
    public void iremove5() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iadd(20, 30);
        rc.iremove(0, 31);
        assertEquals(0, rc.getCardinality());
    }

    @Test
    public void iremove6() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iadd(20, 30);
        rc.iremove(0, 25);
        assertEquals(5, rc.getCardinality());
        for(short k=25; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(8, rc.getSizeInBytes());
    }

    @Test
    public void iremove7() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iremove(0, 15);
        assertEquals(0, rc.getCardinality());
    }

    @Test
    public void iremove8() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iadd(20, 30);
        rc.iremove(5, 21);
        assertEquals(14, rc.getCardinality());
        for(short k=0; k<5; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=21; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iremove9() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iadd(20, 30);
        rc.iremove(15, 21);
        assertEquals(19, rc.getCardinality());
        for(short k=0; k<10; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=21; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iremove10() {
        Container rc = new RunContainer();
        rc.iadd(5, 10);
        rc.iadd(20, 30);
        rc.iremove(0, 25);
        assertEquals(5, rc.getCardinality());
        for(short k=25; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(8, rc.getSizeInBytes());
    }

    @Test
    public void iremove11() {
        Container rc = new RunContainer();
        rc.iadd(5, 10);
        rc.iadd(20, 30);
        rc.iremove(0, 35);
        assertEquals(0, rc.getCardinality());
    }

    @Test
    public void iremove12() {
        Container rc = new RunContainer();
        rc.add((short) 0);
        rc.add((short) 10);
        rc.iremove(0, 11);
        assertEquals(0, rc.getCardinality());
    }

    @Test
    public void iremove13() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iadd(20, 30);
        rc.iremove(5, 25);
        assertEquals(10, rc.getCardinality());
        for(short k=0; k<5; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=25; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iremove14() {
        Container rc = new RunContainer();
        rc.iadd(0, 10);
        rc.iadd(20, 30);
        rc.iremove(5, 31);
        assertEquals(5, rc.getCardinality());
        for(short k=0; k<5; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(8, rc.getSizeInBytes());
    }

    @Test
    public void iremove15() {
        Container rc = new RunContainer();
        rc.iadd(0, 5);
        rc.iadd(20, 30);
        rc.iremove(5, 25);
        assertEquals(10, rc.getCardinality());
        for(short k=0; k<5; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=25; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iremove16() {
        Container rc = new RunContainer();
        rc.iadd(0, 5);
        rc.iadd(20, 30);
        rc.iremove(5, 31);
        assertEquals(5, rc.getCardinality());
        for(short k=0; k<5; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(8, rc.getSizeInBytes());
    }

    @Test
    public void iremoveRange() {
        for(int i = 0; i < 100; ++i) {
            for(int j = 0; j < 100; ++j) {
                for(int k = 0; k < 50; ++k) {
                    BitSet bs = new BitSet();
                    RunContainer container = new RunContainer();
                    for(int p = 0; p < i; ++p) {
                        container.add((short) p);
                        bs.set(p);
                    }
                    for(int p = 0; p < j; ++p) {
                        container.add((short) (99-p));
                        bs.set(99 - p);
                    }
                    container.iremove(49 - k, 50 + k);
                    bs.clear(49 - k, 50 + k);
                    assertEquals(bs.cardinality(), container.getCardinality());

                    int nb_runs = bs.isEmpty() ? 0 : 1;
                    int lastIndex = bs.nextSetBit(0);
                    for (int p = bs.nextSetBit(0); p >= 0; p = bs.nextSetBit(p+1)) {
                        if(p - lastIndex > 1) {
                            nb_runs++;
                        }
                        lastIndex = p;
                        assertTrue(container.contains((short) p));
                    }
                    assertEquals(nb_runs*4+4, container.getSizeInBytes());
                }
            }
        }
    }

    @Test
    public void toBitmapOrArrayContainer() {
        RunContainer rc = new RunContainer();
        rc.iadd(0, DEFAULT_MAX_SIZE / 2);
        Container ac = rc.toBitmapOrArrayContainer();
        assertTrue(ac instanceof ArrayContainer);
        assertEquals(DEFAULT_MAX_SIZE/2, ac.getCardinality());
        for(short k=0; k<DEFAULT_MAX_SIZE/2; ++k) {
            assertTrue(ac.contains(k));
        }
        rc.iadd(DEFAULT_MAX_SIZE/2, 2*DEFAULT_MAX_SIZE);
        Container bc = rc.toBitmapOrArrayContainer();
        assertTrue(bc instanceof BitmapContainer);
        assertEquals(2*DEFAULT_MAX_SIZE, bc.getCardinality());
        for(short k=0; k<2*DEFAULT_MAX_SIZE; ++k) {
            assertTrue(bc.contains(k));
        }
    }

    @Test
    public void fillLeastSignificantBits() {
        Container rc = new RunContainer();
        rc.add((short) 1);
        rc.add((short) 3);
        rc.add((short) 12);
        int[] array = new int[4];
        rc.fillLeastSignificant16bits(array, 1, 0);
        assertEquals(0, array[0]);
        assertEquals(1, array[1]);
        assertEquals(3, array[2]);
        assertEquals(12, array[3]);
    }

    @Test
    public void xor1() {
        Container bc = new BitmapContainer();
        Container rc = new RunContainer();
        rc.add((short) 1);
        Container result = rc.xor(bc);
        assertEquals(1, result.getCardinality());
        assertTrue(result.contains((short) 1));
    }

    @Test
    public void xor2() {
        Container bc = new BitmapContainer();
        Container rc = new RunContainer();
        bc.add((short) 1);
        Container result = rc.xor(bc);
        assertEquals(1, result.getCardinality());
        assertTrue(result.contains((short) 1));
    }

    @Test
    public void xor3() {
        Container bc = new BitmapContainer();
        Container rc = new RunContainer();
        rc.add((short) 1);
        bc.add((short) 1);
        Container result = rc.xor(bc);
        assertEquals(0, result.getCardinality());
    }

    @Test
    public void xor() {
        Container bc = new BitmapContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<2*DEFAULT_MAX_SIZE; ++k) {
            bc = bc.add((short) (k*10));
            bc = bc.add((short) (k*10+1));
            rc = rc.add((short) (k*10));
            rc = rc.add((short) (k*10+3));
        }
        Container result = rc.xor(bc);
        assertEquals(4*DEFAULT_MAX_SIZE, result.getCardinality());
        for(int k=0; k<2*DEFAULT_MAX_SIZE; ++k) {
            assertTrue(result.contains((short) (k*10+1)));
            assertTrue(result.contains((short) (k*10+3)));
        }
        assertEquals(4*DEFAULT_MAX_SIZE, bc.getCardinality());
        assertEquals(4*DEFAULT_MAX_SIZE, rc.getCardinality());
    }
    @Test
    public void xor1a() {
        Container bc = new ArrayContainer();
        Container rc = new RunContainer();
        rc.add((short) 1);
        Container result = rc.xor(bc);
        assertEquals(1, result.getCardinality());
        assertTrue(result.contains((short) 1));
    }

    @Test
    public void xor2a() {
        Container bc = new ArrayContainer();
        Container rc = new RunContainer();
        bc.add((short) 1);
        Container result = rc.xor(bc);
        assertEquals(1, result.getCardinality());
        assertTrue(result.contains((short) 1));
    }

    @Test
    public void xor3a() {
        Container bc = new ArrayContainer();
        Container rc = new RunContainer();
        rc.add((short) 1);
        bc.add((short) 1);
        Container result = rc.xor(bc);
        assertEquals(0, result.getCardinality());
    }

    @Test
    public void xor_array() {
        Container bc = new ArrayContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<2*DEFAULT_MAX_SIZE; ++k) {
            bc = bc.add((short) (k*10));
            bc = bc.add((short) (k*10+1));
            rc = rc.add((short) (k*10));
            rc = rc.add((short) (k*10+3));
        }
        Container result = rc.xor(bc);
        assertEquals(4*DEFAULT_MAX_SIZE, result.getCardinality());
        for(int k=0; k<2*DEFAULT_MAX_SIZE; ++k) {
            assertTrue(result.contains((short) (k*10+1)));
            assertTrue(result.contains((short) (k*10+3)));
        }
        assertEquals(4*DEFAULT_MAX_SIZE, bc.getCardinality());
        assertEquals(4*DEFAULT_MAX_SIZE, rc.getCardinality());
    }

    @Test
    public void clear() {
        Container rc = new RunContainer();
        rc.add((short) 1);
        assertEquals(1, rc.getCardinality());
        rc.clear();
        assertEquals(0, rc.getCardinality());
    }

   @Test
   public void andNot1() {
      Container bc = new BitmapContainer();
      Container rc = new RunContainer();
      rc.add((short) 1);
      Container result = rc.andNot(bc);
      assertEquals(1, result.getCardinality());
      assertTrue(result.contains((short) 1));
   }

   @Test
   public void andNot2() {
      Container bc = new BitmapContainer();
      Container rc = new RunContainer();
      bc.add((short) 1);
      Container result = rc.andNot(bc);
      assertEquals(0, result.getCardinality());
   }

    @Test
    public void andNot() {
        Container bc = new BitmapContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<2*DEFAULT_MAX_SIZE; ++k) {
            bc = bc.add((short) (k*10));
            rc = rc.add((short) (k*10+3));
        }
        Container result = rc.andNot(bc);
        assertEquals(rc, result);
    }

    @Test
    public void union() {
        Container bc = new BitmapContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<100; ++k) {
            bc = bc.add((short) (k*10));
            rc = rc.add((short) (k*10+3));
        }
        Container union = rc.or(bc);
        assertEquals(200, union.getCardinality());
        for(int k=0; k<100; ++k) {
            assertTrue(union.contains((short) (k*10)));
            assertTrue(union.contains((short) (k*10+3)));
        }
        assertEquals(100, bc.getCardinality());
        assertEquals(100, rc.getCardinality());
    }

    @Test
    public void union2() {
        System.out.println("union2");
        Container ac = new ArrayContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<100; ++k) {
            ac = ac.add((short) (k*10));
            rc = rc.add((short) (k*10+3));
        }
        Container union = rc.or(ac);
        assertEquals(200, union.getCardinality());
        for(int k=0; k<100; ++k) {
            assertTrue(union.contains((short) (k*10)));
            assertTrue(union.contains((short) (k*10+3)));
        }
        assertEquals(100, ac.getCardinality());
        assertEquals(100, rc.getCardinality());
    }




    @Test
    public void flip() {
        RunContainer rc = new RunContainer();
        rc.flip((short) 1);
        assertTrue(rc.contains((short) 1));
        rc.flip((short) 1);
        assertFalse(rc.contains((short) 1));
    }

    @Test
    public void intersectionTest1() {
        Container ac = new ArrayContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<100; ++k) {
            ac = ac.add((short) (k*10));
            rc = rc.add((short) (k*10));
        }
        assertEquals(ac, ac.and(rc));
        assertEquals(ac, rc.and(ac));
    }
    
    @Test
    public void intersectionTest2() {
        Container ac = new ArrayContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<10000; ++k) {
            ac = ac.add((short) k);
            rc = rc.add((short) k);
        }
        assertEquals(ac, ac.and(rc));
        assertEquals(ac, rc.and(ac));
    }

    @Test
    public void intersectionTest3() {
        Container ac = new ArrayContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<100; ++k) {
            ac = ac.add((short) k);
            rc = rc.add((short) (k+100));
        }
        assertEquals(0, rc.and(ac).getCardinality());
    }

    @Test
    public void intersectionTest4() {
        Container bc = new BitmapContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<100; ++k) {
            bc = bc.add((short) (k*10));
            bc = bc.add((short) (k*10+3));

            rc = rc.add((short) (k*10+5));
            rc = rc.add((short) (k*10+3));
        }
        Container intersection = rc.and(bc);
        assertEquals(100, intersection.getCardinality());
        for(int k=0; k<100; ++k) {
            assertTrue(intersection.contains((short) (k*10+3)));
        }
        assertEquals(200, bc.getCardinality());
        assertEquals(200, rc.getCardinality());
    }

    @Test
    public void andNotTest1() {
        // this test uses a bitmap container that will be too sparse- okay?
        Container bc = new BitmapContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<100; ++k) {
            bc = bc.add((short) (k*10));
            bc = bc.add((short) (k*10+3));

            rc = rc.add((short) (k*10+5));
            rc = rc.add((short) (k*10+3));
        }
        Container intersectionNOT = rc.andNot(bc);
        assertEquals(100, intersectionNOT.getCardinality());
        for(int k=0; k<100; ++k) {
            assertTrue(" missing k="+k, intersectionNOT.contains((short) (k*10+5)));
        }
        assertEquals(200, bc.getCardinality());
        assertEquals(200, rc.getCardinality());
    }


    
    @Test
    public void andNotTest2() {
        System.out.println("andNotTest2");
        Container ac = new ArrayContainer();
        Container rc = new RunContainer();
        for(int k = 0; k<100; ++k) {
            ac = ac.add((short) (k*10));
            ac = ac.add((short) (k*10+3));

            rc = rc.add((short) (k*10+5));
            rc = rc.add((short) (k*10+3));
        }
        Container intersectionNOT = rc.andNot(ac);
        assertEquals(100, intersectionNOT.getCardinality());
        for(int k=0; k<100; ++k) {
            assertTrue(" missing k="+k, intersectionNOT.contains((short) (k*10+5)));
        }
        assertEquals(200, ac.getCardinality());
        assertEquals(200, rc.getCardinality());
    }

    
    @Test
    public void equalTest1() {
        Container ac = new ArrayContainer();
        Container ar = new RunContainer();
        for(int k = 0; k<100; ++k) {
            ac = ac.add((short) (k*10));
            ar = ar.add((short) (k*10));
        }
        assertEquals(ac, ar);
    }

    @Test
    public void equalTest2() {
        Container ac = new ArrayContainer();
        Container ar = new RunContainer();
        for(int k = 0; k<10000; ++k) {
            ac = ac.add((short) k);
            ar = ar.add((short) k);
        }
        assertEquals(ac, ar);
    }

    
    @Test
    public void addRange() {
        for(int i = 0; i < 100; ++i) {
            for(int j = 0; j < 100; ++j) {
                for(int k = 0; k < 50; ++k) {
                    BitSet bs = new BitSet();
                    RunContainer container = new RunContainer();
                    for(int p = 0; p < i; ++p) {
                        container.add((short) p);
                        bs.set(p);
                    }
                    for(int p = 0; p < j; ++p) {
                        container.add((short) (99-p));
                        bs.set(99 - p);
                    }
                    Container newContainer = container.add(49 - k, 50 + k);
                    bs.set(49 - k, 50 + k);
                    assertNotSame(container, newContainer);
                    assertEquals(bs.cardinality(), newContainer.getCardinality());

                    int nb_runs = 1;
                    int lastIndex = bs.nextSetBit(0);
                    for (int p = bs.nextSetBit(0); p >= 0; p = bs.nextSetBit(p+1)) {
                        if(p - lastIndex > 1) {
                            nb_runs++;
                        }
                        lastIndex = p;
                        assertTrue(newContainer.contains((short) p));
                    }
                    assertEquals(nb_runs*4+4, newContainer.getSizeInBytes());
                }
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void iaddInvalidRange1() {
        Container rc = new RunContainer();
        rc.iadd(10, 9);
    }

    @Test(expected = IllegalArgumentException.class)
<<<<<<< HEAD
    public void iaddRangeInvalid2() {
=======
    public void iaddInvalidRange2() {
>>>>>>> 6effb47180e24c93a9a042bd3922cf0ef6aaa664
        Container rc = new RunContainer();
        rc.iadd(0, 1<<20);
    }

    @Test
    public void iaddRange1() {
        Container rc = new RunContainer();
        for(short k=0; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        for(short k=40; k<50; ++k) {
            rc.add(k);
        }
        rc.iadd(5, 21);
        assertEquals(40, rc.getCardinality());
        for(short k=0; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=40; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange2() {
        Container rc = new RunContainer();
        for(short k=0; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        for(short k=40; k<50; ++k) {
            rc.add(k);
        }
        rc.iadd(0, 26);
        assertEquals(40, rc.getCardinality());
        for(short k=0; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=40; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange3() {
        Container rc = new RunContainer();
        for(short k=0; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        for(short k=40; k<50; ++k) {
            rc.add(k);
        }
        rc.iadd(0, 20);
        assertEquals(40, rc.getCardinality());
        for(short k=0; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=40; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange4() {
        Container rc = new RunContainer();
        for(short k=0; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        for(short k=40; k<50; ++k) {
            rc.add(k);
        }
        rc.iadd(10, 21);
        assertEquals(40, rc.getCardinality());
        for(short k=0; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=40; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange5() {
        Container rc = new RunContainer();
        for(short k=0; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        for(short k=40; k<50; ++k) {
            rc.add(k);
        }
        rc.iadd(15, 21);
        assertEquals(35, rc.getCardinality());
        for(short k=0; k<10; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=15; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=40; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(16, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange6() {
        Container rc = new RunContainer();
        for(short k=5; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        for(short k=40; k<50; ++k) {
            rc.add(k);
        }
        rc.iadd(0, 21);
        assertEquals(40, rc.getCardinality());
        for(short k=0; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=40; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange7() {
        Container rc = new RunContainer();
        for(short k=0; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        for(short k=40; k<50; ++k) {
            rc.add(k);
        }
        rc.iadd(15, 25);
        assertEquals(35, rc.getCardinality());
        for(short k=0; k<10; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=15; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=40; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(16, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange8() {
        Container rc = new RunContainer();
        for(short k=0; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        for(short k=40; k<50; ++k) {
            rc.add(k);
        }
        rc.iadd(15, 40);
        assertEquals(45, rc.getCardinality());
        for(short k=0; k<10; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=15; k<50; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange10() {
        Container rc = new RunContainer();
        for(short k=0; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        rc.iadd(15, 35);
        assertEquals(30, rc.getCardinality());
        for(short k=0; k<10; ++k) {
            assertTrue(rc.contains(k));
        }
        for(short k=15; k<35; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(12, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange11() {
        Container rc = new RunContainer();
        for(short k=5; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        rc.iadd(0, 20);
        assertEquals(30, rc.getCardinality());
        for(short k=0; k<30; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(8, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange12() {
        Container rc = new RunContainer();
        for(short k=5; k<10; ++k) {
            rc.add(k);
        }
        for(short k=20; k<30; ++k) {
            rc.add(k);
        }
        rc.iadd(0, 35);
        assertEquals(35, rc.getCardinality());
        for(short k=0; k<35; ++k) {
            assertTrue(rc.contains(k));
        }
        assertEquals(8, rc.getSizeInBytes());
    }

    @Test
    public void iaddRange() {
        for(int i = 0; i < 100; ++i) {
            for(int j = 0; j < 100; ++j) {
                for(int k = 0; k < 50; ++k) {
                    BitSet bs = new BitSet();
                    RunContainer container = new RunContainer();
                    for(int p = 0; p < i; ++p) {
                        container.add((short) p);
                        bs.set(p);
                    }
                    for(int p = 0; p < j; ++p) {
                        container.add((short) (99-p));
                        bs.set(99 - p);
                    }
                    container.iadd(49 - k, 50 + k);
                    bs.set(49 - k, 50 + k);
                    assertEquals(bs.cardinality(), container.getCardinality());

                    int nb_runs = 1;
                    int lastIndex = bs.nextSetBit(0);
                    for (int p = bs.nextSetBit(0); p >= 0; p = bs.nextSetBit(p+1)) {
                        if(p - lastIndex > 1) {
                            nb_runs++;
                        }
                        lastIndex = p;
                        assertTrue(container.contains((short) p));
                    }
                    assertEquals(nb_runs*4+4, container.getSizeInBytes());
                }
            }
        }
    }

    @Test
    public void addRangeAndFuseWithPreviousValueLength() {
        RunContainer container = new RunContainer();
        for(short i = 10; i < 20; ++i) {
            container.add(i);
        }
        Container newContainer = container.add(20, 30);
        assertNotSame(container, newContainer);
        assertEquals(20, newContainer.getCardinality());
        for(short i = 10; i < 30; ++i) {
            assertTrue(newContainer.contains(i));
        }
        assertEquals(8, newContainer.getSizeInBytes());
    }
    @Test
    public void iaddRangeAndFuseWithPreviousValueLength() {
        RunContainer container = new RunContainer();
        for(short i = 10; i < 20; ++i) {
            container.add(i);
        }
        container.iadd(20, 30);
        assertEquals(20, container.getCardinality());
        for(short i = 10; i < 30; ++i) {
            assertTrue(container.contains(i));
        }
        assertEquals(8, container.getSizeInBytes());
    }
    @Test
    public void addRangeAndFuseWithNextValueLength() {
        RunContainer container = new RunContainer();
        for(short i = 10; i < 20; ++i) {
            container.add(i);
        }
        for(short i = 21; i < 30; ++i) {
            container.add(i);
        }
        Container newContainer = container.add(15, 21);
        assertNotSame(container, newContainer);
        assertEquals(20, newContainer.getCardinality());
        for(short i = 10; i < 30; ++i) {
            assertTrue(newContainer.contains(i));
        }
        assertEquals(8, newContainer.getSizeInBytes());
    }

    @Test
    public void addRangeOnNonEmptyContainerAndFuse() {
        RunContainer container = new RunContainer();
        for(short i = 1; i < 20; ++i) {
            container.add(i);
        }
        for(short i = 90; i < 120; ++i) {
            container.add(i);
        }
        Container newContainer = container.add(10, 100);
        assertNotSame(container, newContainer);
        assertEquals(119, newContainer.getCardinality());
        for(short i = 1; i < 120; ++i) {
            assertTrue(newContainer.contains(i));
        }
    }

    @Test
    public void iaddRangeOnNonEmptyContainerAndFuse() {
        RunContainer container = new RunContainer();
        for(short i = 1; i < 20; ++i) {
            container.add(i);
        }
        for(short i = 90; i < 120; ++i) {
            container.add(i);
        }
        container.iadd(10, 100);
        assertEquals(119, container.getCardinality());
        for(short i = 1; i < 120; ++i) {
            assertTrue(container.contains(i));
        }
    }
    @Test
    public void addRangeOnNonEmptyContainer() {
        RunContainer container = new RunContainer();
        container.add((short) 1);
        container.add((short) 256);
        Container newContainer = container.add(10, 100);
        assertNotSame(container, newContainer);
        assertEquals(92, newContainer.getCardinality());
        assertTrue(newContainer.contains((short) 1));
        assertTrue(newContainer.contains((short) 256));
        for(short i = 10; i < 100; ++i) {
            assertTrue(newContainer.contains(i));
        }
    }

    @Test
    public void addRangeOnEmptyContainer() {
        RunContainer container = new RunContainer();
        Container newContainer = container.add(10, 100);
        assertNotSame(container, newContainer);
        assertEquals(90, newContainer.getCardinality());
        for(short i = 10; i < 100; ++i) {
            assertTrue(newContainer.contains(i));
        }
    }

    @Test
    public void addRangeWithinSetBoundsAndFuse() {
        RunContainer container = new RunContainer();
        container.add((short) 1);
        container.add((short) 10);
        container.add((short) 55);
        container.add((short) 99);
        container.add((short) 150);
        Container newContainer = container.add(10, 100);
        assertNotSame(container, newContainer);
        assertEquals(92, newContainer.getCardinality());
        for(short i = 10; i < 100; ++i) {
            assertTrue(newContainer.contains(i));
        }
    }

    @Test
    public void iaddRangeWithinSetBounds() {
        RunContainer container = new RunContainer();
        container.add((short) 10);
        container.add((short) 99);
        container.iadd(10, 100);
        assertEquals(90, container.getCardinality());
        for(short i = 10; i < 100; ++i) {
            assertTrue(container.contains(i));
        }
    }

    @Test
    public void addRangeWithinSetBounds() {
        RunContainer container = new RunContainer();
        container.add((short) 10);
        container.add((short) 99);
        Container newContainer = container.add(10, 100);
        assertNotSame(container, newContainer);
        assertEquals(90, newContainer.getCardinality());
        for(short i = 10; i < 100; ++i) {
            assertTrue(newContainer.contains(i));
        }
    }

    @Test
    public void addAndCompress() {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 99);
        container.add((short) 98);
        assertEquals(12, container.getSizeInBytes());
    }

    @Test
    public void addOutOfOrder() {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 2);
        container.add((short) 55);
        container.add((short) 1);
        assertEquals(4, container.getCardinality());
        assertTrue(container.contains((short) 0));
        assertTrue(container.contains((short) 1));
        assertTrue(container.contains((short) 2));
        assertTrue(container.contains((short) 55));
    }

    @Test
    public void limit() {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 2);
        container.add((short) 55);
        container.add((short) 64);
        container.add((short) 256);
        Container limit = container.limit(1024);
        assertNotSame(container, limit);
        assertEquals(container, limit);
        limit = container.limit(3);
        assertNotSame(container, limit);
        assertEquals(3, limit.getCardinality());
        assertTrue(limit.contains((short) 0));
        assertTrue(limit.contains((short) 2));
        assertTrue(limit.contains((short) 55));
    }

    @Test
    public void rank() {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 2);
        container.add((short) 55);
        container.add((short) 64);
        container.add((short) 256);
        assertEquals(1, container.rank((short) 0));
        assertEquals(2, container.rank((short) 10));
        assertEquals(4, container.rank((short) 128));
        assertEquals(5, container.rank((short) 1024));
    }

    @Test
    public void select() {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 2);
        container.add((short) 55);
        container.add((short) 64);
        container.add((short) 256);
        assertEquals(0, container.select(0));
        assertEquals(2, container.select(1));
        assertEquals(55, container.select(2));
        assertEquals(64, container.select(3));
        assertEquals(256, container.select(4));
    }

    @Test
    public void safeSerialization() throws Exception {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 2);
        container.add((short) 55);
        container.add((short) 64);
        container.add((short) 256);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(container);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        RunContainer newContainer = (RunContainer) in.readObject();
        assertEquals(container, newContainer);
        assertEquals(container.serializedSizeInBytes(), newContainer.serializedSizeInBytes());
    }

    @Test
    public void basic() {
        RunContainer x = new RunContainer();
        for(int k = 0; k < (1<<16);++k) {
            assertFalse(x.contains((short)k));
        }
        for(int k = 0; k < (1<<16);++k) {
            assertFalse(x.contains((short)k));
            x = (RunContainer)x.add((short)k);
            assertEquals(k+1,x.getCardinality());
            assertTrue(x.contains((short)k));
        }
        for(int k = 0; k < (1<<16);++k) {
            assertTrue(x.contains((short)k));
        }
        for(int k = 0; k < (1<<16);++k) {
            assertTrue(x.contains((short)k));
            x = (RunContainer)x.remove((short)k);
            assertFalse(x.contains((short)k));
            assertEquals(k+1,(1<<16)-x.getCardinality());
        }
        for(int k = 0; k < (1<<16);++k) {
            assertFalse(x.contains((short)k));
            x = (RunContainer) x.add((short)k);
            assertEquals(k+1,x.getCardinality());
            assertTrue(x.contains((short)k));
        }
        for(int k = (1<<16)-1; k >= 0 ;--k) {
            assertTrue(x.contains((short)k));
            x = (RunContainer) x.remove((short)k);
            assertFalse(x.contains((short)k));
            assertEquals(k,x.getCardinality());
        }
        for(int k = 0; k < (1<<16);++k) {
            assertFalse(x.contains((short)k));
            x = (RunContainer) x.add((short)k);
            assertEquals(k+1,x.getCardinality());
            assertTrue(x.contains((short)k));
        }
        for(int k = 0; k < (1<<16);++k) {
            RunContainer copy = (RunContainer) x.clone();
            copy = (RunContainer) copy.remove((short) k);
            assertEquals(copy.getCardinality() + 1,x.getCardinality());
            copy = (RunContainer) copy.add((short) k);
            assertEquals(copy.getCardinality(),x.getCardinality());
            assertTrue(copy.equals(x));
            assertTrue(x.equals(copy));
            copy.trim();
            assertTrue(copy.equals(x));
            assertTrue(x.equals(copy));
        }        
    }
    @Test
    public void basictri() {
        RunContainer x = new RunContainer();
        for(int k = 0; k < (1<<16);++k) {
            assertFalse(x.contains((short)k));
        }
        for(int k = 0; k < (1<<16);++k) {
            assertFalse(x.contains((short)k));
            x = (RunContainer)x.add((short)k);
            x.trim();
            assertEquals(k+1,x.getCardinality());
            assertTrue(x.contains((short)k));
        }
        for(int k = 0; k < (1<<16);++k) {
            assertTrue(x.contains((short)k));
        }
        for(int k = 0; k < (1<<16);++k) {
            assertTrue(x.contains((short)k));
            x = (RunContainer)x.remove((short)k);
            x.trim();
            assertFalse(x.contains((short)k));
            assertEquals(k+1,(1<<16)-x.getCardinality());
        }
        for(int k = 0; k < (1<<16);++k) {
            assertFalse(x.contains((short)k));
            x = (RunContainer) x.add((short)k);
            x.trim();
            assertEquals(k+1,x.getCardinality());
            assertTrue(x.contains((short)k));
        }
        for(int k = (1<<16)-1; k >= 0 ;--k) {
            assertTrue(x.contains((short)k));
            x = (RunContainer) x.remove((short)k);
            x.trim();
            assertFalse(x.contains((short)k));
            assertEquals(k,x.getCardinality());
        }
        for(int k = 0; k < (1<<16);++k) {
            assertFalse(x.contains((short)k));
            x = (RunContainer) x.add((short)k);
            x.trim();
            assertEquals(k+1,x.getCardinality());
            assertTrue(x.contains((short)k));
        }
        for(int k = 0; k < (1<<16);++k) {
            RunContainer copy = (RunContainer) x.clone();
            copy.trim();
            copy = (RunContainer) copy.remove((short) k);
            copy = (RunContainer) copy.add((short) k);
            assertEquals(copy.getCardinality(),x.getCardinality());
            assertTrue(copy.equals(x));
            assertTrue(x.equals(copy));
            copy.trim();
            assertTrue(copy.equals(x));
            assertTrue(x.equals(copy));
        }        
    }

    @Test
    public void simpleIterator() {
        RunContainer x = new RunContainer();
        for(int k = 0; k < 100;++k) {
              x = (RunContainer)x.add((short)(k));
        }
        ShortIterator i = x.getShortIterator();
        for(int k = 0; k < 100;++k) {
                assertTrue(i.hasNext());
                assertEquals(i.next(), (short)k);
        }
        assertFalse(i.hasNext());
    }

    @Test
    public void longsimpleIterator() {
        RunContainer x = new RunContainer();
        for(int k = 0; k < (1<<16);++k) {
              x = (RunContainer)x.add((short)(k));
        }
        ShortIterator i = x.getShortIterator();
        for(int k = 0; k < (1<<16);++k) {
                assertTrue(i.hasNext());
                assertEquals(i.next(), (short)k);
        }
        assertFalse(i.hasNext());
    }
    
    @Test
    public void longbacksimpleIterator() {
        RunContainer x = new RunContainer();
        for(int k = 0; k < (1<<16);++k) {
              x = (RunContainer)x.add((short)k);
        }
        ShortIterator i = x.getReverseShortIterator();
        for(int k = (1<<16)-1; k >=0 ;--k) {
                assertTrue(i.hasNext());
                assertEquals(i.next(), (short)k);
        }
        assertFalse(i.hasNext());
    }

    @Test
    public void longcsimpleIterator() {
        RunContainer x = new RunContainer();
        for(int k = 0; k < (1<<16);++k) {
              x = (RunContainer)x.add((short)k);
        }
        Iterator<Short> i = x.iterator();
        for(int k = 0; k < (1<<16);++k) {
                assertTrue(i.hasNext());
                assertEquals(i.next().shortValue(), (short)k);
        }
        assertFalse(i.hasNext());
    }
    
    @Test
    public void iterator() {
        RunContainer x = new RunContainer();
        for(int k = 0; k < 100;++k) {
            for(int j = 0; j < k; ++j)
              x = (RunContainer)x.add((short)(k*100+j));
        }
        ShortIterator i = x.getShortIterator();
        for(int k = 0; k < 100;++k) {
            for(int j = 0; j < k; ++j) {
                assertTrue(i.hasNext());
                assertEquals(i.next(), (short)(k*100+j));
            }
        }
        assertFalse(i.hasNext());        
    }

    @Test
    public void basic2() {
        RunContainer x = new RunContainer();
        int a = 33;
        int b = 50000;
        for(int k = a; k < b;++k) {
            x = (RunContainer)x.add((short)k);
        }
        for(int k = 0; k < (1<<16);++k) {
            if(x.contains((short) k)) {
                RunContainer copy = (RunContainer) x.clone();
                copy = (RunContainer) copy.remove((short) k);
                copy = (RunContainer) copy.add((short) k);
                assertEquals(copy.getCardinality(),x.getCardinality());
                assertTrue(copy.equals(x));
                assertTrue(x.equals(copy));      
                x.trim();
                assertTrue(copy.equals(x));
                assertTrue(x.equals(copy));      
                
            } else {
                RunContainer copy = (RunContainer) x.clone();
                copy = (RunContainer) copy.add((short) k);
                assertEquals(copy.getCardinality(),x.getCardinality() + 1);
            }
        }
    }
    

    @Test
    public void not1() {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 2);
        container.add((short) 55);
        container.add((short) 64);
        container.add((short) 256);

        Container result = container.not(64,64);  // empty range
        assertNotSame(container, result);
        assertEquals(container, result);
    }


   
    @Test
    public void not2() {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 2);
        container.add((short) 55);
        container.add((short) 64);
        container.add((short) 256);

        RunContainer result = (RunContainer) container.not(64,66);
        assertEquals(5, result.getCardinality());
        for (short i : new short[] {0,2,55,65,256})
            assertTrue(result.contains(i));
    }
   

    @Test
    public void not3() {
        RunContainer container = new RunContainer();
        // applied to a run-less container
        RunContainer result = (RunContainer) container.not(64,68);
        assertEquals(4, result.getCardinality());
        for (short i : new short[] {64,65,66,67})
            assertTrue(result.contains(i));
    }
    
    @Test
    public void not4() {
        RunContainer container  = new RunContainer();
        container.add((short) 0);
        container.add((short) 2);
        container.add((short) 55);
        container.add((short) 64);
        container.add((short) 256);

        // all runs are before the range
        RunContainer result = (RunContainer) container.not(300,303);
        assertEquals(8, result.getCardinality());
        for (short i : new short[] {0,2,55,64,256,300,301,302})
            assertTrue(result.contains(i));
    }


    @Test
    public void not5() {
        RunContainer container  = new RunContainer();
        container.add((short) 500);
        container.add((short) 502);
        container.add((short) 555);
        container.add((short) 564);
        container.add((short) 756);

        // all runs are after the range
        RunContainer result = (RunContainer) container.not(300,303); 
        assertEquals(8, result.getCardinality());
        for (short i : new short[] {500,502,555,564,756,300,301,302})
            assertTrue(result.contains(i));
    }


    @Test
    public void not6() {
        RunContainer container  = new RunContainer();
        container.add((short) 500);
        container.add((short) 501);
        container.add((short) 502);
        container.add((short) 503);

        // one run is  strictly within the range
        RunContainer result = (RunContainer) container.not(499,505); 
        assertEquals(2, result.getCardinality());
        for (short i : new short[] {499,504})
            assertTrue(result.contains(i));
    }


    @Test
    public void not7() {
        RunContainer container  = new RunContainer();
        container.add((short) 500);
        container.add((short) 501);
        container.add((short) 502);
        container.add((short) 503);
        container.add((short) 504);
        container.add((short) 505);


        // one run, spans the range
        RunContainer result = (RunContainer) container.not(502,504); 

        assertEquals(4, result.getCardinality());
        for (short i : new short[] {500,501,504,505})
            assertTrue(result.contains(i));
    }


    @Test
    public void not8() {
        RunContainer container  = new RunContainer();
        container.add((short) 300);
        container.add((short) 500);
        container.add((short) 501);
        container.add((short) 502);
        container.add((short) 503);
        container.add((short) 504);
        container.add((short) 505);

        // second  run, spans the range
        RunContainer result = (RunContainer) container.not(502,504); 

        assertEquals(5, result.getCardinality());
        for (short i : new short[] {300, 500,501,504,505})
            assertTrue(result.contains(i));
    }

    @Test
    public void not9() {
        RunContainer container  = new RunContainer();
        container.add((short) 500);
        container.add((short) 501);
        container.add((short) 502);
        container.add((short) 503);
        container.add((short) 504);
        container.add((short) 505);

        // first run, begins inside the range but extends outside
        RunContainer result = (RunContainer) container.not(498,504); 

        assertEquals(4, result.getCardinality());
        for (short i : new short[] {498,499,504,505})
            assertTrue(result.contains(i));
    }


    @Test
    public void not10() {
        RunContainer container  = new RunContainer();
        container.add((short) 300);
        container.add((short) 500);
        container.add((short) 501);
        container.add((short) 502);
        container.add((short) 503);
        container.add((short) 504);
        container.add((short) 505);

        // second run begins inside the range but extends outside
        RunContainer result = (RunContainer) container.not(498,504); 

        assertEquals(5, result.getCardinality());
        for (short i : new short[] {300, 498,499,504,505})
            assertTrue(result.contains(i));
    }



    @Test
    public void not11() {
        RunContainer container  = new RunContainer();
        container.add((short) 300);

        container.add((short) 500);
        container.add((short) 501);
        container.add((short) 502);

        container.add((short) 504);

        container.add((short) 510);

        // second run entirely inside range, third run entirely inside range, 4th run entirely outside
        RunContainer result = (RunContainer) container.not(498,507); 

        assertEquals(7, result.getCardinality());
        for (short i : new short[] {300, 498,499,503,505,506, 510})
            assertTrue(result.contains(i));
    }

    @Test
    public void not12() {
        RunContainer container  = new RunContainer();
        container.add((short) 300);

        container.add((short) 500);
        container.add((short) 501);
        container.add((short) 502);

        container.add((short) 504);

        container.add((short) 510);
        container.add((short) 511);

        // second run crosses into range, third run entirely inside range, 4th crosses outside
        RunContainer result = (RunContainer) container.not(501,511); 

        assertEquals(9, result.getCardinality());
        for (short i : new short[] {300, 500, 503,505,506, 507, 508, 509, 511})
            assertTrue(result.contains(i));
    }



    @Test
    public void not12A() {
        RunContainer container  = new RunContainer();
        container.add((short) 300);
        container.add((short) 301);

        // first run crosses into range
        RunContainer result = (RunContainer) container.not(301,303); 

        assertEquals(2, result.getCardinality());
        for (short i : new short[] {300, 302})
            assertTrue(result.contains(i));
    }





    @Test
    public void not13() {
        RunContainer container  = new RunContainer();
        // check for off-by-1 errors that might affect length 1 runs

        for (int i=100; i < 120;  i += 3) 
            container.add((short) i);

        // second run crosses into range, third run entirely inside range, 4th crosses outside
        RunContainer result = (RunContainer) container.not(110, 115); 

        assertEquals(10, result.getCardinality());
        for (short i : new short[] {100,103,106, 109, 110,111,113, 114, 115, 118})
            assertTrue(result.contains(i));
    }




    private void not14once(int num, int rangeSize) {
        RunContainer container  = new RunContainer();
        BitSet checker = new BitSet();
        for (int i=0; i < num; ++i) {
            int val = (int) (Math.random()*65536);
            checker.set(val);
            container.add((short)val);
        }

        int rangeStart = (int) Math.random() * (65536-rangeSize);
        int rangeEnd = rangeStart+rangeSize;

        RunContainer result = (RunContainer) container.not(rangeStart, rangeEnd);
        checker.flip(rangeStart,rangeEnd);
        
        // esnsure they agree on each possible bit
        for (int i=0; i < 65536; ++i)
            assertFalse(result.contains((short)i) ^ checker.get(i));

    }

    @Test
    public void not14() {
        not14once(10,1);
        not14once(10,10);
        not14once(1000, 100);
        for (int i=1; i <= 100; ++i) {
            if (i % 10 == 0)
                System.out.println("not 14 attempt "+i);
            not14once(50000,100);
        }
    }
    
    
    private static void getSetOfRunContainers(ArrayList<RunContainer> set, ArrayList<Container> setb) {
    	RunContainer r1 = new RunContainer();
    	r1 = (RunContainer) r1.iadd(0, (1<<16));
    	Container b1 = new ArrayContainer();
    	b1 = b1.iadd(0, 1<<16);
        assertTrue(r1.equals(b1));

    	set.add(r1);
    	setb.add(b1);
    	
    	RunContainer r2 = new RunContainer();
    	r2 = (RunContainer) r2.iadd(0, 4096);
    	Container b2 = new ArrayContainer();
    	b2 = b2.iadd(0, 4096);
    	set.add(r2);
    	setb.add(b2);
        assertTrue(r2.equals(b2));
    	
    	RunContainer r3 = new RunContainer();
    	Container b3 = new ArrayContainer();
    	for(int k = 0; k < 655536; k += 2) {
    		r3 = (RunContainer) r3.add((short) k);
    		b3 = b3.add((short) k);
    	}
        assertTrue(r3.equals(b3));
    	set.add(r3);
    	setb.add(b3);
    	
    	RunContainer r4 = new RunContainer();
    	Container b4 = new ArrayContainer();
    	for(int k = 0; k < 655536; k += 256) {
    		r4 = (RunContainer) r4.add((short) k);
    		b4 = b4.add((short) k);
    	}
        assertTrue(r4.equals(b4));
    	set.add(r4);
    	setb.add(b4);
    	
    	RunContainer r5 = new RunContainer();
    	Container b5 = new ArrayContainer();
    	for(int k = 0; k + 4096 < 65536; k += 4096) {
    		r5 = (RunContainer) r5.iadd(k,k+256);
    		b5 = b5.iadd(k,k+256);
    	}
        assertTrue(r5.equals(b5));
    	set.add(r5);
    	setb.add(b5);

    	RunContainer r6 = new RunContainer();
    	Container b6 = new ArrayContainer();
    	for(int k = 0; k+1 < 65536; k += 7) {
            r6 = (RunContainer) r6.iadd(k,k+1);
    	    b6 = b6.iadd(k,k+1);
    	}
        assertTrue(r6.equals(b6));
    	set.add(r6);
    	setb.add(b6);


    	RunContainer r7 = new RunContainer();
    	Container b7 = new ArrayContainer();
    	for(int k = 0; k+1 < 65536; k += 11) {
            r7 = (RunContainer) r7.iadd(k,k+1);
    	    b7 = b7.iadd(k,k+1);
    	}
        assertTrue(r7.equals(b7));
    	set.add(r7);
    	setb.add(b7);
    	
    }

    
    @Test
    public void RunContainerVSRunContainerAND() {
    	ArrayList<RunContainer> set = new ArrayList<RunContainer>();
    	ArrayList<Container> setb = new ArrayList<Container>();
    	getSetOfRunContainers( set, setb);
    	for(int k = 0; k < set.size(); ++k ) {
    		for(int l = 0; l < set.size(); ++l) {
    			assertTrue(set.get(k).equals(setb.get(k)));
    			assertTrue(set.get(l).equals(setb.get(l)));
    			Container c1 = set.get(k).and(set.get(l));
    			Container c2 = setb.get(k).and(setb.get(l));
                assertTrue(c1.equals(c2));
    		}
    	}
    }

    @Test
    public void RunContainerVSRunContainerANDNOT() {
    	ArrayList<RunContainer> set = new ArrayList<RunContainer>();
    	ArrayList<Container> setb = new ArrayList<Container>();
    	getSetOfRunContainers( set, setb);
    	for(int k = 0; k < set.size(); ++k ) {
    		for(int l = 0; l < set.size(); ++l) {
    			assertTrue(set.get(k).equals(setb.get(k)));
    			assertTrue(set.get(l).equals(setb.get(l)));
    			Container c1 = set.get(k).andNot(set.get(l));
    			Container c2 = setb.get(k).andNot(setb.get(l));
                assertTrue(c1.equals(c2));
    		}
    	}    	
    }

    @Test
    public void RunContainerVSRunContainerXOR() {
    	ArrayList<RunContainer> set = new ArrayList<RunContainer>();
    	ArrayList<Container> setb = new ArrayList<Container>();
    	getSetOfRunContainers( set, setb);
    	for(int k = 0; k < set.size(); ++k ) {
    		for(int l = 0; l < set.size(); ++l) {
    			assertTrue(set.get(k).equals(setb.get(k)));
    			assertTrue(set.get(l).equals(setb.get(l)));
    			Container c1 = set.get(k).xor(set.get(l));
    			Container c2 = setb.get(k).xor(setb.get(l));
                assertTrue(c1.equals(c2));
    		}
    	}    	    	
    }


    @Test
    public void RunContainerVSRunContainerOR() {
    	ArrayList<RunContainer> set = new ArrayList<RunContainer>();
    	ArrayList<Container> setb = new ArrayList<Container>();
    	getSetOfRunContainers( set, setb);
    	for(int k = 0; k < set.size(); ++k ) {
    		for(int l = 0; l < set.size(); ++l) {
    			assertTrue(set.get(k).equals(setb.get(k)));
    			assertTrue(set.get(l).equals(setb.get(l)));
    			Container c1 = set.get(k).or(set.get(l));
    			Container c2 = setb.get(k).or(setb.get(l));
                        assertTrue(c1.equals(c2));
    		}
    	}    	    	    	
    }


  @Test
  public void RunContainerArg_ArrayOR() {
        boolean atLeastOneArray = false;
    	ArrayList<RunContainer> set = new ArrayList<RunContainer>();
    	ArrayList<Container> setb = new ArrayList<Container>();
    	getSetOfRunContainers( set, setb);
    	for(int k = 0; k < set.size(); ++k ) {
    		for(int l = 0; l < set.size(); ++l) {
    			assertTrue(set.get(k).equals(setb.get(k)));
    			assertTrue(set.get(l).equals(setb.get(l)));
                        Container thisContainer = setb.get(k);
                        // BitmapContainers are tested separately, but why not test some more?
                        if (thisContainer instanceof BitmapContainer) ; //continue;
                        else atLeastOneArray = true;
                        
    			Container c1 = thisContainer.or(set.get(l));
    			Container c2 = setb.get(k).or(setb.get(l));
                        assertTrue(c1.equals(c2));
    		}
    	}   
        assertTrue(atLeastOneArray);
  }


  @Test
  public void RunContainerArg_ArrayAND() {
        boolean atLeastOneArray = false;
    	ArrayList<RunContainer> set = new ArrayList<RunContainer>();
    	ArrayList<Container> setb = new ArrayList<Container>();
    	getSetOfRunContainers( set, setb);
    	for(int k = 0; k < set.size(); ++k ) {
    		for(int l = 0; l < set.size(); ++l) {
    			assertTrue(set.get(k).equals(setb.get(k)));
    			assertTrue(set.get(l).equals(setb.get(l)));
                        Container thisContainer = setb.get(k);
                        if (thisContainer instanceof BitmapContainer) ; //continue;
                        else atLeastOneArray = true;
                        
    			Container c1 = thisContainer.and(set.get(l));
    			Container c2 = setb.get(k).and(setb.get(l));
                        assertTrue(c1.equals(c2));
    		}
    	}   
        assertTrue(atLeastOneArray);
  }



  @Test
  public void RunContainerArg_ArrayXOR() {
        boolean atLeastOneArray = false;
    	ArrayList<RunContainer> set = new ArrayList<RunContainer>();
    	ArrayList<Container> setb = new ArrayList<Container>();
    	getSetOfRunContainers( set, setb);
    	for(int k = 0; k < set.size(); ++k ) {
    		for(int l = 0; l < set.size(); ++l) {
    			assertTrue(set.get(k).equals(setb.get(k)));
    			assertTrue(set.get(l).equals(setb.get(l)));
                        Container thisContainer = setb.get(k);
                        if (thisContainer instanceof BitmapContainer) ; //continue;
                        else atLeastOneArray = true;
                        
    			Container c1 = thisContainer.xor(set.get(l));
    			Container c2 = setb.get(k).xor(setb.get(l));
                        assertTrue(c1.equals(c2));
    		}
    	}   
        assertTrue(atLeastOneArray);
  }


  @Test
  public void RunContainerArg_ArrayANDNOT() {
        boolean atLeastOneArray = false;
    	ArrayList<RunContainer> set = new ArrayList<RunContainer>();
    	ArrayList<Container> setb = new ArrayList<Container>();
    	getSetOfRunContainers( set, setb);
    	for(int k = 0; k < set.size(); ++k ) {
    		for(int l = 0; l < set.size(); ++l) {
    			assertTrue(set.get(k).equals(setb.get(k)));
    			assertTrue(set.get(l).equals(setb.get(l)));
                        Container thisContainer = setb.get(k);
                        if (thisContainer instanceof BitmapContainer) {
                            //continue;
                        } 
                        else atLeastOneArray = true;
                        
    			Container c1 = thisContainer.andNot(set.get(l));
    			Container c2 = setb.get(k).andNot(setb.get(l));

                        assertTrue(c1.equals(c2));
    		}
    	}   
        assertTrue(atLeastOneArray);
  }


}