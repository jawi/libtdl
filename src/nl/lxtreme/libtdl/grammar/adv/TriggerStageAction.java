/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.adv;

import org.antlr.v4.runtime.*;

/**
 * Denotes an action that is performed in a trigger stage.
 */
class TriggerStageAction {
    // CONSTANTS

    private static final int ELSE_BITOFFSET = 20;
    private static final int STOP_TIMER0 = 0x01000000;
    private static final int STOP_TIMER1 = 0x02000000;
    private static final int CLEAR_TIMER0 = 0x04000000;
    private static final int CLEAR_TIMER1 = 0x08000000;
    private static final int START_TIMER0 = 0x10000000;
    private static final int START_TIMER1 = 0x20000000;
    private static final int TRIGGER_FLAG = 0x40000000;
    private static final int LASTSTATE = 0x80000000;

    // VARIABLES

    private int m_waitCount;
    private int m_occurrenceCount;
    private boolean m_endState;
    private boolean m_setTrigger;
    private int m_startTimer;
    private int m_clearTimer;
    private int m_stopTimer;
    private int m_elseState;

    // CONSTRUCTORS

    /**
     * Creates a new {@link TriggerStageAction} instance.
     */
    public TriggerStageAction() {
        m_waitCount = -1;
        m_occurrenceCount = 1;
        m_endState = false;
        m_setTrigger = false;
        m_startTimer = 0;
        m_stopTimer = 0;
        m_clearTimer = 0;
        m_elseState = 0;
    }

    // METHODS

    /**
     * Encodes this stage into a single value.
     * 
     * @return an encoded value.
     */
    public int encodeValue() {
        int result = (m_elseState << ELSE_BITOFFSET) | m_occurrenceCount;
        if (m_endState) {
            result |= LASTSTATE;
        }
        if (m_setTrigger) {
            result |= TRIGGER_FLAG;
        }
        if ((m_startTimer & 1) != 0) {
            result |= START_TIMER0;
        }
        if ((m_startTimer & 2) != 0) {
            result |= START_TIMER1;
        }
        if ((m_stopTimer & 1) != 0) {
            result |= STOP_TIMER0;
        }
        if ((m_stopTimer & 2) != 0) {
            result |= STOP_TIMER1;
        }
        if ((m_clearTimer & 1) != 0) {
            result |= CLEAR_TIMER0;
        }
        if ((m_clearTimer & 2) != 0) {
            result |= CLEAR_TIMER1;
        }
        return result;
    }

    /**
     * @return the clearTimer
     */
    public int getClearTimer() {
        return m_clearTimer;
    }

    /**
     * @return the elseState
     */
    public int getElseState() {
        return m_elseState;
    }

    /**
     * @return the occurrenceCount
     */
    public int getOccurrenceCount() {
        return m_occurrenceCount;
    }

    /**
     * @return the startTimer
     */
    public int getStartTimer() {
        return m_startTimer;
    }

    /**
     * @return the stopTimer
     */
    public int getStopTimer() {
        return m_stopTimer;
    }

    /**
     * @return the waitCount
     */
    public int getWaitCount() {
        return m_waitCount;
    }

    /**
     * Returns whether the action is an end state, causing the capture to be
     * started.
     * 
     * @return <code>true</code> if the trigger stage is an end-stage,
     *         <code>false</code> otherwise.
     */
    public boolean isEndState() {
        return m_endState;
    }

    /**
     * @return the setTrigger
     */
    public boolean isSetTrigger() {
        return m_setTrigger;
    }

    /**
     * @param clearTimer
     *            the clearTimer to set
     */
    public void setClearTimer(Token timerName) {
        m_clearTimer = getTimerNumber(timerName);
    }

    /**
     * @param elseState
     *            the elseState to set
     */
    public void setElseState(int elseState) {
        m_elseState = elseState;
    }

    /**
     * @param lastState
     *            the lastState to set
     */
    public void setEndState(boolean lastState) {
        m_endState = lastState;
    }

    /**
     * @param occurrenceCount
     *            the occurrenceCount to set
     */
    public void setOccurrenceCount(int occurrenceCount) {
        m_occurrenceCount = occurrenceCount;
    }

    /**
     * @param setTrigger
     *            the setTrigger to set
     */
    public void setSetTrigger(boolean setTrigger) {
        m_setTrigger = setTrigger;
    }

    /**
     * @param startTimer
     *            the startTimer to set
     */
    public void setStartTimer(Token timerName) {
        m_startTimer = getTimerNumber(timerName);
    }

    /**
     * @param stopTimer
     *            the stopTimer to set
     */
    public void setStopTimer(Token timerName) {
        m_stopTimer = getTimerNumber(timerName);
    }

    /**
     * @param waitCount
     *            the waitCount to set
     */
    public void setWaitCount(int waitCount) {
        m_waitCount = waitCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (m_occurrenceCount > 0) {
            sb.append("occurs ").append(m_occurrenceCount);
        }
        if (m_startTimer > 0) {
            sb.append(" start timer").append(m_startTimer);
        } else if (m_stopTimer > 0) {
            sb.append(" stop timer").append(m_stopTimer);
        } else if (m_setTrigger && m_endState) {
            sb.append(" start capture");
        } else if (!m_setTrigger && m_endState) {
            sb.append(" stop capture");
        }
        return sb.toString().trim();
    }

    /**
     * Returns the numeric index of the timer, so "timer1" -&gt; 1 and "timer2"
     * -&gt; 2.
     * 
     * @param timerName
     *            the timer name, cannot be <code>null</code>.
     * @return a numeric index, >= 0.
     */
    private int getTimerNumber(Token timerName) {
        String name = timerName.getText();

        char index = name.charAt(name.length() - 1);
        if (!Character.isDigit(index)) {
            return 0;
        }

        return index - '0';
    }
}
