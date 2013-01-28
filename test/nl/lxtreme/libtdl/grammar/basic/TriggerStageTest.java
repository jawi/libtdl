/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import java.io.*;

import junit.framework.*;
import nl.lxtreme.libtdl.grammar.*;

/**
 * Test cases for {@link TriggerStage}.
 */
public class TriggerStageTest extends TestCase {
    // INNER TYPES

    static class CapturingTdlOutputStream implements TdlOutputStream {
        // VARIABLES

        private final ByteArrayOutputStream m_os = new ByteArrayOutputStream(16);
        private final DataOutputStream m_dos = new DataOutputStream(m_os);

        // METHODS

        @Override
        public void close() throws IOException {
            // Only flush the wrapped stream; we do NOT close it...
            m_dos.flush();
        }

        /**
         * @return the written data as byte array.
         */
        public byte[] getWrittenData() {
            return m_os.toByteArray();
        }

        @Override
        public void writeSelect(int opcode) throws IOException {
            m_dos.writeByte(opcode);
        }

        @Override
        public void writeData(int data) throws IOException {
            m_dos.writeInt(data);
        }
    }

    // METHODS

    /**
     * Tests that we can create a simple serial stage that starts capture
     * without delaying the capture.
     */
    public void testSerialStageWithDelayOk() throws Exception {
        TriggerStage stage = new TriggerStage(0, false /* ddrMode */);

        stage.setActivationLevel(0);
        stage.setDelay(0x4321);
        stage.setSerialChannel(3);
        stage.setStartCapture(true);

        int config = stage.getTriggerConfig();
        assertEquals(0xC304321, config);
    }

    /**
     * Tests that we can create a simple parallel stage that activates at level
     * 2 and upon activation goes to the next level.
     */
    public void testStageWithActivationLevelOk() throws Exception {
        TriggerStage stage = new TriggerStage(0, false /* ddrMode */);

        stage.setActivationLevel(2);
        stage.setStartCapture(false);

        int config = stage.getTriggerConfig();
        assertEquals(0x20000, config);
    }

    /**
     * Tests that we can create a simple parallel stage that starts capture with
     * delaying the capture.
     */
    public void testStageWithDelayOk() throws Exception {
        TriggerStage stage = new TriggerStage(0, false /* ddrMode */);

        stage.setActivationLevel(0);
        stage.setDelay(0x1234);
        stage.setStartCapture(true);

        int config = stage.getTriggerConfig();
        assertEquals(0x8001234, config);
    }

    /**
     * Tests that we can create a simple parallel stage that starts capture
     * without delaying the capture.
     */
    public void testStageWithoutDelayOk() throws Exception {
        TriggerStage stage = new TriggerStage(0, false /* ddrMode */);

        stage.setActivationLevel(0);
        stage.setDelay(0);
        stage.setStartCapture(true);

        int config = stage.getTriggerConfig();
        assertEquals(0x8000000, config);
    }

    /**
     * Tests that we can create a stage with a mask/value in non-DDR mode works
     * as expected.
     */
    public void testStageMaskValueWithoutDdrOk() throws Exception {
        TriggerStage stage = new TriggerStage(0, false /* ddrMode */);

        stage.setMask(0x1234);
        stage.setValue(0x2345);

        assertEquals(0x1234, stage.getMask());
        assertEquals(0x2345, stage.getValue());
    }

    /**
     * Tests that we can create a stage with a mask/value in DDR mode works as
     * expected.
     */
    public void testStageMaskValueWithDdrOk() throws Exception {
        TriggerStage stage = new TriggerStage(0, true /* ddrMode */);

        stage.setMask(0x1234);
        stage.setValue(0x2345);

        assertEquals(0x12341234, stage.getMask());
        assertEquals(0x23452345, stage.getValue());
    }

    /**
     * Tests that writing a stage to a {@link TdlOutputStream} works as
     * expected.
     */
    public void testWriteStageWithoutDdrModeOk() throws Exception {
        TriggerStage stage = new TriggerStage(0, false /* ddrMode */);

        stage.setMask(0x1234);
        stage.setValue(0x2345);
        stage.setActivationLevel(1);
        stage.setDelay(0x34);
        stage.setStartCapture(true);

        CapturingTdlOutputStream tos = new CapturingTdlOutputStream();
        stage.write(tos);

        byte[] data = tos.getWrittenData();
        assertEquals(data, 0xc0, 0x00, 0x00, 0x12, 0x34, 0xc1, 0x00, 0x00, 0x23, 0x45, 0xc2, 0x08, 0x01, 0x00, 0x34);
    }

    /**
     * Tests that writing a stage to a {@link TdlOutputStream} works as
     * expected.
     */
    public void testWriteStageWithDdrModeOk() throws Exception {
        TriggerStage stage = new TriggerStage(1, true /* ddrMode */);

        stage.setMask(0x1234);
        stage.setValue(0x2345);
        stage.setActivationLevel(1);
        stage.setDelay(0x34);
        stage.setStartCapture(true);

        CapturingTdlOutputStream tos = new CapturingTdlOutputStream();
        stage.write(tos);

        byte[] data = tos.getWrittenData();
        assertEquals(data, 0xc4, 0x12, 0x34, 0x12, 0x34, 0xc5, 0x23, 0x45, 0x23, 0x45, 0xc6, 0x08, 0x01, 0x00, 0x34);
    }

    private void assertEquals(byte[] input, int... expected) {
        assertNotNull(input);
        assertTrue(input.length <= expected.length);

        for (int i = 0; i < expected.length; i++) {
            assertEquals("Index = " + i, (byte) expected[i], input[i]);
        }
    }
}
