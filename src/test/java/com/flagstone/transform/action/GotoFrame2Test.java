/*
 * GotoFrame2Test.java
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
package com.flagstone.transform.action;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.flagstone.transform.coder.Context;
import com.flagstone.transform.coder.SWFDecoder;
import com.flagstone.transform.coder.SWFEncoder;

public final class GotoFrame2Test {

    private static final int TYPE = ActionTypes.GOTO_FRAME_2;
    private static final boolean PLAY = true;
    private static final int OFFSET = 1;

    private transient GotoFrame2 fixture;

    private final transient byte[] encoded = new byte[] {(byte) TYPE, 0x03,
            0x00, 0x03, 0x01, 0x00 };

    private final transient byte[] stop = new byte[] {(byte) TYPE, 0x01, 0x00,
            0x00 };

    private final transient byte[] noOffset = new byte[] {(byte) TYPE, 0x01,
            0x00, 0x01 };

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForIdentifierWithLowerBound() {
        fixture = new GotoFrame2(-1, PLAY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForIdentifierWithUpperBound() {
        fixture = new GotoFrame2(65536, PLAY);
    }

    @Test
    public void checkCopy() {
        fixture = new GotoFrame2(OFFSET, PLAY);
        final GotoFrame2 copy = fixture.copy();

        assertSame(fixture, copy);
        assertEquals(fixture.toString(), copy.toString());
    }

    @Test
    public void encode() throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final SWFEncoder encoder = new SWFEncoder(stream);
        final Context context = new Context();

        fixture = new GotoFrame2(OFFSET, PLAY);
        assertEquals(encoded.length, fixture.prepareToEncode(context));
        fixture.encode(encoder, context);
        encoder.flush();

        assertArrayEquals(encoded, stream.toByteArray());
    }

    @Test
    public void encodeWithNoOffset() throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final SWFEncoder encoder = new SWFEncoder(stream);
        final Context context = new Context();

        fixture = new GotoFrame2(0, PLAY);
        assertEquals(noOffset.length,
                fixture.prepareToEncode(context));
        fixture.encode(encoder, context);
        encoder.flush();

        assertArrayEquals(noOffset, stream.toByteArray());
    }

    @Test
    public void encodeWithPlaySetToFalse() throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final SWFEncoder encoder = new SWFEncoder(stream);
        final Context context = new Context();

        fixture = new GotoFrame2(0, false);
        assertEquals(stop.length, fixture.prepareToEncode(context));
        fixture.encode(encoder, context);
        encoder.flush();

        assertArrayEquals(stop, stream.toByteArray());
    }

    @Test
    public void decode() throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream(encoded);
        final SWFDecoder decoder = new SWFDecoder(stream);

        decoder.readByte();
        fixture = new GotoFrame2(decoder);

        assertEquals(OFFSET, fixture.getFrameOffset());
        assertEquals(PLAY, fixture.isPlay());
    }

    @Test
    public void decodeWithNoOffset() throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream(noOffset);
        final SWFDecoder decoder = new SWFDecoder(stream);

        decoder.readByte();
        fixture = new GotoFrame2(decoder);

        assertEquals(0, fixture.getFrameOffset());
        assertEquals(PLAY, fixture.isPlay());
    }

    @Test
    public void decodeWithPlaySetToFalse() throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream(stop);
        final SWFDecoder decoder = new SWFDecoder(stream);

        decoder.readByte();
        fixture = new GotoFrame2(decoder);

        assertEquals(0, fixture.getFrameOffset());
        assertEquals(false, fixture.isPlay());
    }
}
