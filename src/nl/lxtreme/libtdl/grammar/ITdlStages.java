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
 * Represents a container for all stage definitions.
 */
public interface ITdlStages extends Iterable<ITdlTriggerStage>, TdlWritable {
    // METHODS

    /**
     * Returns the number of contained definitions.
     * 
     * @return the number of definitions, >= 0.
     */
    int size();
}
