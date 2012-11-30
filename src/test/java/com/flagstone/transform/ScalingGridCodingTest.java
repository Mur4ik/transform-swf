/*
 * ScalingGridCodingTest.java
 * Transform
 *
 * Copyright (c) 2010 Flagstone Software Ltd. All rights reserved.
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
package com.flagstone.transform;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.flagstone.transform.datatype.Bounds;

public final class ScalingGridCodingTest extends AbstractCodingTest {

    @Test
    public void checkScalingGridLengthForEncoding() throws IOException {
        final Bounds bounds = new Bounds(1, 2, 3, 4);
        final ScalingGrid object = new ScalingGrid(1, bounds);
        final byte[] binary = new byte[] {(byte) 0x85, 0x13, 0x01, 0x00, 0x20,
                    (byte) 0x99, 0x20};

        assertEquals(CALCULATED_LENGTH, binary.length, prepare(object));
    }

    @Test
    public void checkScalingGridIsEncoded() throws IOException {
        final Bounds bounds = new Bounds(1, 2, 3, 4);
        final ScalingGrid object = new ScalingGrid(1, bounds);
        final byte[] binary = new byte[] {(byte) 0x85, 0x13, 0x01, 0x00, 0x20,
                    (byte) 0x99, 0x20};

        assertArrayEquals(NOT_ENCODED, binary, encode(object));
    }

    @Test
    public void checkScalingGridIsDecoded() throws IOException {
        final Bounds bounds = new Bounds(1, 2, 3, 4);
        final byte[] binary = new byte[] {(byte) 0x85, 0x13, 0x01, 0x00, 0x20,
                (byte) 0x99, 0x20};

        final ScalingGrid object = (ScalingGrid) decodeMovieTag(binary);
        assertEquals(NOT_DECODED, 1, object.getIdentifier());
        assertEquals(NOT_DECODED, bounds, object.getBounds());
   }

    @Test
    public void checkExtendedScalingGridIsDecoded() throws IOException {
        final Bounds bounds = new Bounds(1, 2, 3, 4);
        final byte[] binary = new byte[] {(byte) 0xBF, 0x13, 0x05, 0x00, 0x00,
                0x00, 0x01, 0x00, 0x20, (byte) 0x99, 0x20};

        final ScalingGrid object = (ScalingGrid) decodeMovieTag(binary);
        assertEquals(NOT_DECODED, 1, object.getIdentifier());
        assertEquals(NOT_DECODED, bounds, object.getBounds());
   }
}
