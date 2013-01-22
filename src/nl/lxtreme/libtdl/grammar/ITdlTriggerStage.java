/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar;

import nl.lxtreme.libtdl.*;

/**
 * Denotes a single trigger stage.
 */
public interface ITdlTriggerStage extends TdlWritable {
    // METHODS

    /**
     * @return the capture expression, never <code>null</code>.
     */
    TdlTriggerSum getCapture();

    /**
     * @return the else expression, never <code>null</code>.
     */
    TdlTriggerSum getElse();

    /**
     * @return the if expression, never <code>null</code>.
     */
    TdlTriggerSum getIf();

    /**
     * @return the index of this stage, > 0.
     */
    int getIndex();

}
