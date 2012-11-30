/*
 * FocalGradientFillTest.java
 * Transform
 *
 * Copyright (c) 2009-2010 Flagstone Software Ltd. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  * Neither the name of Flagstone Software Ltd. nor the names of its
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.flagstone.transform.fillstyle;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.flagstone.transform.coder.Context;
import com.flagstone.transform.coder.SWFDecoder;
import com.flagstone.transform.coder.SWFEncoder;
import com.flagstone.transform.datatype.Color;

public final class FocalGradientFillTest {

//    private static transient boolean radial = false;
//    private static transient CoordTransform transform = CoordTransform
//            .translate(1, 2);
    private static transient List<Gradient> list = new ArrayList<Gradient>();

    static {
        list.add(new Gradient(1, new Color(2, 3, 4)));
        list.add(new Gradient(5, new Color(6, 7, 8)));
    }

    private transient FocalGradientFill fixture;

    private final transient byte[] encoded = new byte[] {};

    @Test
    @Ignore
    public void checkCopy() {
        // fixture = new FocalGradientFill(type, transform, list);
        final FocalGradientFill copy = fixture.copy();

        assertNotSame(fixture.getGradients(), copy.getGradients());
        assertEquals(fixture.toString(), copy.toString());
    }

    @Test
    @Ignore
    public void encode() throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final SWFEncoder encoder = new SWFEncoder(stream);
        final Context context = new Context();

        // fixture = new FocalGradientFill(type, transform, list);
        assertEquals(encoded.length, fixture.prepareToEncode(context));
        fixture.encode(encoder, context);


        assertArrayEquals(encoded, stream.toByteArray());
    }

    @Test
    @Ignore
    public void decode() throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream(encoded);
        final SWFDecoder decoder = new SWFDecoder(stream);
        final Context context = new Context();

        decoder.readByte();
        fixture = new FocalGradientFill(decoder, context);

        assertNotNull(fixture);
    }
}
