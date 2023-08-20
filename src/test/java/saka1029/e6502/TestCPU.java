package saka1029.e6502;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCPU {

    @Test
    public void testCPU() {
        assertEquals(0xfffc, CPU.RST);
        CPU cpu = new CPU();
        cpu.byte2(CPU.RST, (short)0x1234);
        assertEquals((short)0x1234, cpu.byte2(CPU.RST));
        cpu.byte2(CPU.RST, (short)0xFEFC);
        assertEquals((short)0xFEFC, cpu.byte2(CPU.RST));
    }
    
    @Test
    public void testStatus() {
        CPU cpu = new CPU();
        cpu.s = 0;
        assertFalse(cpu.s(CPU.N));
        assertFalse(cpu.s(CPU.V));
        assertFalse(cpu.s(CPU.B));
        assertFalse(cpu.s(CPU.D));
        assertFalse(cpu.s(CPU.I));
        assertFalse(cpu.s(CPU.Z));
        assertFalse(cpu.s(CPU.C));
        cpu.s(CPU.N, true);
        cpu.s(CPU.C, true);
        assertEquals((byte)0b10000001, cpu.s);
        assertTrue(cpu.s(CPU.N));
        assertTrue(cpu.s(CPU.C));
    }

}
