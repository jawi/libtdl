/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import java.io.*;

import nl.lxtreme.libtdl.*;

/**
 * Provides a timer definition.
 */
public class TdlTimerDefinition extends AbstractTdlDefinition {
    // CONSTANTS

    private static final int MAX_TIMERS = 2;
    private static final long MASK_TIME = (1L << 36) - 1L;

    // VARIABLES

    private final int m_index;
    private final long m_time; // in ns.

    // CONSTRUCTORS

    /**
     * Creates a new {@link TdlTermDefinition}.
     */
    public TdlTimerDefinition(int index, long time, String timeUnit) {
        super(Type.TIMER);

        m_index = index % MAX_TIMERS;
        m_time = convertToNS(time, timeUnit) & MASK_TIME;
    }

    /**
     * Creates a new {@link TdlTermDefinition}.
     */
    public TdlTimerDefinition(String name, long time, String timeUnit) {
        this(toIndex(name), time, timeUnit);
    }

    // METHODS

    /**
     * Converts a given time with unit to a time in nanoseconds.
     * 
     * @param time
     *            the time value to convert;
     * @param timeUnit
     *            the time unit of the time to convert.
     * @return a time value in nanoseconds.
     */
    static final long convertToNS(long time, String timeUnit) {
        if ((timeUnit == null) || "".equals(timeUnit)) {
            throw new IllegalArgumentException("TimeUnit cannot be null or empty!");
        }

        timeUnit = timeUnit.toLowerCase();
        if ("us".equals(timeUnit)) {
            return time * 1000L;
        } else if ("ms".equals(timeUnit)) {
            return time * 1000L * 1000L;
        } else if ("s".equals(timeUnit)) {
            return time * 1000L * 1000L * 1000L;
        }

        // default to ns...
        return time;
    }

    /**
     * Converts the given term name to a numeric index, for example, "timer1"
     * =&gt; 0, "timer2" =&gt; 1.
     * 
     * @param timerName
     *            the timer name to convert.
     * @return the numeric index for the given timer name, >= 0.
     */
    static int toIndex(String timerName) {
        if ((timerName == null) || "".equals(timerName)) {
            throw new IllegalArgumentException("Term name cannot be null or empty!");
        }
        return timerName.charAt(timerName.length() - 1) - '1';
    }

    /**
     * @return the index of this timer, either 0 or 1.
     */
    public int getIndex() {
        return m_index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return String.format("timer%d", (m_index + 1));
    }

    /**
     * @return the time of this timer, in nanoseconds.
     */
    public long getTime() {
        return m_time;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%s: %dns", getName(), getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(TdlOutputStream outputStream) throws IOException {
        // each timer tick corresponds to 10ns, so the value written needs to be
        // divided by 10...
        long time = getTime() / 10;

        outputStream.writeSelect(0x38 + (getIndex() * 2));
        outputStream.writeChain((int) (time & MASK_32BIT));
        outputStream.writeSelect(0x39 + (getIndex() * 2));
        outputStream.writeChain((int) (time >> 32));
    }
}
