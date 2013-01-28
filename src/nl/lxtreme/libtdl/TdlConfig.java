/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl;

/**
 * Denotes the configuration of how TDL is to be converted to a binary
 * bitstream.
 */
public interface TdlConfig {
    // METHODS

    /**
     * Returns the dialect used for interpreting TDL snippets.
     * 
     * @return a dialect, never <code>null</code>.
     */
    TdlDialect getDialect();

    /**
     * Returns the maximum number of stages that is supported by the
     * backend/device.
     * 
     * @return a stage count, > 0.
     */
    int getMaxStages();

    /**
     * Returns the maximum number of channels that is supported by the
     * backend/device.
     * 
     * @return a channel count, > 0.
     */
    int getMaxChannels();

    /**
     * Returns whether or not DDR (demux) mode is enabled.
     * <p>
     * In DDR-mode, the 3rd and 4th channel group are mirrors of the 1st and 2nd
     * channel groups.
     * </p>
     * 
     * @return <code>true</code> if DDR/demux mode is enabled,
     *         <code>false</code> if normal mode is enabled.
     */
    boolean isDdrMode();

}
