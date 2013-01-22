/*
 * LibTDL - Library for parsing/handling the "Trigger Definition Language".
 *
 * (C) Copyright 2012-2013 - J.W. Janssen, <j.w.janssen@lxtreme.nl>
 *
 * Licensed under Apache Software License version 2.0, see <http://www.apache.org/licenses/LICENSE-2.0.html>.
 */
package nl.lxtreme.libtdl.grammar.basic;

import static nl.lxtreme.libtdl.grammar.ValidationUtil.*;

import java.io.*;

import nl.lxtreme.libtdl.*;
import nl.lxtreme.libtdl.grammar.*;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.ProgContext;
import nl.lxtreme.libtdl.grammar.basic.BasicTdlParser.TermDeclContext;

/**
 * Provides a visitor for collecting the defined terms and stages.
 */
public class BasicTdlCompiler extends BasicTdlBaseVisitor<BasicTdlSemanticAnalyzer> {
    // VARIABLES

    private final TdlOutputStream m_outputStream;
    private final TdlDefinitions m_declarations;
    private final TdlTriggerStages m_stages;

    // CONSTRUCTORS

    /**
     * Creates a new {@link BasicTdlCompiler} instance.
     */
    public BasicTdlCompiler(TdlOutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream cannot be null!");
        }
        m_outputStream = outputStream;
        m_declarations = new TdlDefinitions();
        m_stages = new TdlTriggerStages();
    }

    // METHODS

    @Override
    public BasicTdlSemanticAnalyzer visitProg(ProgContext ctx) {
        BasicTdlSemanticAnalyzer result = super.visitProg(ctx);
        // Done walking through the entire program; compile the output to single
        // bitstream...
        try {
            m_declarations.write(m_outputStream);
            return result;
        } catch (IOException exception) {
            // Wrap in a runtime exception...
            throw new RuntimeException("Compilation failed!", exception);
        }
    }

    @Override
    public BasicTdlSemanticAnalyzer visitTermDecl(TermDeclContext ctx) {
        String name = normalizeName(ctx.name.getText());
        Long value = decode(ctx.value.getText());
        Long mask = decode(ctx.mask.getText());

        m_declarations.addTermDefinition(name, value, mask);

        return super.visitTermDecl(ctx);
    }
}
