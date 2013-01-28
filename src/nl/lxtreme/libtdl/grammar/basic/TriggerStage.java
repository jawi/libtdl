/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import java.io.*;

import nl.lxtreme.libtdl.grammar.*;

/**
 * Provides the definition of a trigger stage.
 */
class TriggerStage implements TdlWritable {
    // CONSTANTS

    private static final long MASK_32BIT = (1L << 32) - 1L;

    private static final int SET_TRIGGER_MASK = 0xc0;
    private static final int SET_TRIGGER_VALUE = 0xc1;
    private static final int SET_TRIGGER_CONFIG = 0xc2;

    private static final int ACTIVATION_MASK = 0x03;
    private static final int DELAY_MASK = 0xffff;
    private static final int SERIAL_CHANNEL_MASK = 0x1f;

    private final static int TRIGGER_SERIAL = 0x04000000;
    private final static int TRIGGER_CAPTURE = 0x08000000;

    // VARIABLES

    private final int m_index;
    private final boolean m_ddrMode;

    private int m_activationLevel;
    private int m_delay;
    private int m_serialChannel;
    private boolean m_startCapture;

    private int m_mask;
    private int m_value;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TriggerStage} instance.
     * 
     * @param index
     *            the index of this stage, >= 0;
     * @param ddrMode
     *            <code>true</code> if DDR mode is enabled, <code>false</code>
     *            otherwise.
     */
    public TriggerStage(int index, boolean ddrMode) {
        m_index = index;
        m_ddrMode = ddrMode;

        m_activationLevel = 0;
        m_delay = 0;
        m_serialChannel = -1;
        m_startCapture = false;
    }

    // METHODS

    /**
     * Returns the level at which this stage will be activated.
     * 
     * @return the activation level, >= 0. A value of 0 means this stage will be
     *         immediately activated.
     */
    public int getActivationLevel() {
        return m_activationLevel;
    }

    /**
     * Returns the number of samples the capture should be delayed after the
     * trigger is activated.
     * 
     * @return the delay, in number of samples. A value of 0 means no delay.
     */
    public int getDelay() {
        return m_delay;
    }

    /**
     * Returns the index of this stage.
     * 
     * @return the index of this stage, >= 0.
     */
    public int getIndex() {
        return m_index;
    }

    /**
     * Returns the trigger mask.
     * 
     * @return the trigger mask.
     */
    public int getMask() {
        int result = m_mask;

        if (m_ddrMode) {
            // Make sure the lower word and upper word are equal...
            result = (m_mask & 0xFFFF) | ((m_mask & 0xFFFF) << 16);
        }

        return result;
    }

    /**
     * Returns the trigger value.
     * 
     * @return the trigger value.
     */
    public int getValue() {
        int result = m_value;

        if (m_ddrMode) {
            // Make sure the lower word and upper word are equal...
            result = (m_value & 0xFFFF) | ((m_value & 0xFFFF) << 16);
        }

        return result;
    }

    /**
     * Returns whether the activation of this trigger stage means that the
     * actual capture should be started.
     * 
     * @return <code>true</code> if capture should be started upon activation of
     *         this trigger stage, <code>false</code> to just increase the
     *         level.
     * @see #getDelay()
     */
    public boolean isStartCapture() {
        return m_startCapture;
    }

    /**
     * Sets activation level of this stage.
     * 
     * @param activationLevel
     *            the activation level to set, >= 0.
     */
    public void setActivationLevel(int activationLevel) {
        m_activationLevel = (activationLevel & ACTIVATION_MASK);
    }

    /**
     * @param delay
     *            the number of samples to wait after the trigger condition is
     *            met.
     */
    public void setDelay(int delay) {
        m_delay = (delay & DELAY_MASK);
    }

    /**
     * Sets the trigger mask for this stage.
     * 
     * @param mask
     *            the mask-value to set.
     */
    public void setMask(long mask) {
        m_mask = (int) (mask & MASK_32BIT);
    }

    /**
     * Sets the index of the channel to capture serially.
     * 
     * @param channel
     *            the channel index, >= 0 means a serial trigger, < 0 means a
     *            parallel trigger (the default).
     */
    public void setSerialChannel(int channel) {
        m_serialChannel = channel & SERIAL_CHANNEL_MASK;
    }

    /**
     * @param startCapture
     *            <code>true</code> if the capture should be started once this
     *            trigger is activated, <code>false</code> to increase the
     *            trigger level by one.
     */
    public void setStartCapture(boolean startCapture) {
        m_startCapture = startCapture;
    }

    /**
     * @param term
     * @param inverted
     */
    public void setTerm(Term term, boolean inverted) {
        long mask = term.getMask();
        setMask(mask);

        long value = term.getValue();
        if (inverted) {
            value = ~value;
        }
        setValue(value);
    }

    /**
     * Sets the trigger value.
     * 
     * @param value
     *            the trigger value to set.
     */
    public void setValue(long value) {
        m_value = (int) (value & MASK_32BIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        int offset = (4 * getIndex());

        outputStream.writeSelect(SET_TRIGGER_MASK | offset);
        outputStream.writeData(getMask());

        outputStream.writeSelect(SET_TRIGGER_VALUE | offset);
        outputStream.writeData(getValue());

        outputStream.writeSelect(SET_TRIGGER_CONFIG | offset);
        outputStream.writeData(getTriggerConfig());
    }

    /**
     * @return the trigger configuration, never <code>null</code>.
     */
    final int getTriggerConfig() {
        int result = 0;

        result |= m_delay;
        result |= (m_activationLevel << 16);

        if (m_serialChannel >= 0) {
            // Serial trigger...
            result |= TRIGGER_SERIAL;
            result |= (m_serialChannel << 20);
        }

        if (m_startCapture) {
            result |= TRIGGER_CAPTURE;
        }

        return result;
    }
}
