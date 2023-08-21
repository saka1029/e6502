package saka1029.e6502;

public class CPU {
    
    public static final int NMI = 0xFFFA;
    public static final int RST = 0xFFFC;
    public static final int IRQ = 0xFFFE;
    public static final int N = 0b10000000;
    public static final int V = 0b01000000;
    public static final int B = 0b00010000;
    public static final int D = 0b00001000;
    public static final int I = 0b00000100;
    public static final int Z = 0b00000010;
    public static final int C = 0b00000001;
    
    public byte a = 0, x = 0, y = 0, s = 0, sp = 0;
    public short pc = 0;
    
    public byte[] memory = new byte[0x10000];
    
    public byte byte1(int address) {
        return memory[address & 0xFFFF];
    }
    
    public void byte1(int address, byte value) {
        memory[address & 0XFFFF] = value;
    }

    public short byte2(int address) {
        return (short)((byte1(address + 1) << 8) | byte1(address) & 0xFF);
    }

    public void byte2(int address, short value) {
        byte1(address, (byte)(value & 0xFF));
        byte1(address + 1, (byte)(value >> 8));
    }
    
    public void bytes(int address, int... values) {
            for (int v : values)
                byte1(address++, (byte)v);
    }
    
    public boolean s(int bit) {
        return (s & bit) != 0;
    }
    
    public void s(int bit, boolean value) {
        s =  (byte) (value ? s | bit : s & ~bit);
    }

    public void reset() {
        pc = byte2(RST);
    }

}
