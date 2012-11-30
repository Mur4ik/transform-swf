/*
 * BoundsCodingTest.java
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
package com.flagstone.transform.datatype;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.flagstone.transform.AbstractCodingTest;
import com.flagstone.transform.coder.Context;
import com.flagstone.transform.coder.SWFDecoder;
import com.flagstone.transform.coder.SWFEncoder;

public final class BoundsCodingTest extends AbstractCodingTest {

    @Test
    public void checkPositiveDimensionsAreEncoded() throws IOException {
        final Bounds object = new Bounds(1, 2, 3, 4);
        final byte[] binary = new byte[] {0x20, (byte) 0x99, 0x20 };

        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final SWFEncoder encoder = new SWFEncoder(stream);
        final Context context = new Context();

        final int length = object.prepareToEncode(context);
        object.encode(encoder, context);
        encoder.flush();

        assertEquals(CALCULATED_LENGTH, binary.length, length);

        assertArrayEquals(NOT_ENCODED, binary, stream.toByteArray());
    }

    @Test
    public void checkPositiveDimensionsAreDecoded() throws IOException {
        final Bounds object = new Bounds(1, 2, 3, 4);
        final byte[] binary = new byte[] {0x20, (byte) 0x99, 0x20 };
        final ByteArrayInputStream stream = new ByteArrayInputStream(binary);
        final SWFDecoder decoder = new SWFDecoder(stream);

        assertEquals(NOT_DECODED, object, new Bounds(decoder));
    }

    @Test
    public void checkZeroDimensionsAreEncoded() throws IOException {
        final Bounds object = new Bounds(0, 0, 0, 0);
        final byte[] binary = new byte[] {0x08, 0x00 };
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final SWFEncoder encoder = new SWFEncoder(stream);
        final Context context = new Context();

        final int length = object.prepareToEncode(context);
        object.encode(encoder, context);
        encoder.flush();

        assertEquals(CALCULATED_LENGTH, binary.length, length);

        assertArrayEquals(NOT_ENCODED, binary, stream.toByteArray());
    }

    @Test
    public void checkZeroDimensionsAreDecoded() throws IOException {
        final Bounds object = new Bounds(0, 0, 0, 0);
        final byte[] binary = new byte[] {0x08, 0x00 };
        final ByteArrayInputStream stream = new ByteArrayInputStream(binary);
        final SWFDecoder decoder = new SWFDecoder(stream);

        assertEquals(NOT_DECODED, object, new Bounds(decoder));
    }

    @Test
    public void checkNegativeMinimumIsEncoded() throws IOException {
        final Bounds object = new Bounds(-1, -1, 1, 1);
        final byte[] binary = new byte[] {0x16, (byte) 0xE8 };

        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final SWFEncoder encoder = new SWFEncoder(stream);
        final Context context = new Context();

        final int length = object.prepareToEncode(context);
        object.encode(encoder, context);
        encoder.flush();

        assertEquals(CALCULATED_LENGTH, binary.length, length);

        assertArrayEquals(NOT_ENCODED, binary, stream.toByteArray());
    }

    @Test
    public void checkNegativeMinimumIsDecoded() throws IOException {
        final Bounds object = new Bounds(-1, -1, 1, 1);
        final byte[] binary = new byte[] {0x16, (byte) 0xE8 };
        final ByteArrayInputStream stream = new ByteArrayInputStream(binary);
        final SWFDecoder decoder = new SWFDecoder(stream);

        assertEquals(NOT_DECODED, object, new Bounds(decoder));
    }
}
