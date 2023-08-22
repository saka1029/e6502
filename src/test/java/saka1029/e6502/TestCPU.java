package saka1029.e6502;

import static org.junit.Assert.*;

import java.util.HexFormat;

import org.junit.Test;

public class TestCPU {

    @Test
    public void testCPU() {
        assertEquals(0xfffc, CPU.RST);
        CPU cpu = new CPU();
        cpu.byte2(CPU.RST, (short) 0x1234);
        assertEquals((short) 0x1234, cpu.byte2(CPU.RST));
        cpu.byte2(CPU.RST, (short) 0xFEFC);
        assertEquals((short) 0xFEFC, cpu.byte2(CPU.RST));
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
        assertEquals((byte) 0b10000001, cpu.s);
        assertTrue(cpu.s(CPU.N));
        assertTrue(cpu.s(CPU.C));
    }

    @Test
    public void testBytes() {
        assertEquals((byte) -4, -4);
        CPU cpu = new CPU();
        cpu.bytes(0x4000, -4, -3, -2, -1, 0, 1, 2, 3, 4);
        for (int i = -4, a = 0x4000; i < 5; ++i, ++a)
            assertEquals((byte) i, cpu.byte1(a));
        assertEquals("FC FD FE FF 00 01 02 03 04", HexFormat.ofDelimiter(" ").withUpperCase().formatHex(cpu.memory, 0x4000, 0x4009));
    }

}
