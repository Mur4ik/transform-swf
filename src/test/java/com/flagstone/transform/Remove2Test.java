/*
 * Remove2Test.java
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
package com.flagstone.transform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public final class Remove2Test {

    private static final int LAYER = 1;

    private transient Remove2 fixture;

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForLayerWithLowerBound() {
        fixture = new Remove2(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForLayerWithUpperBound() {
        fixture = new Remove2(65536);
    }

    @Test
    public void checkCopy() {
        fixture = new Remove2(LAYER);
        final Remove2 copy = fixture.copy();

        assertNotSame(fixture, copy);
        assertEquals(fixture.getLayer(), copy.getLayer());
        assertEquals(fixture.toString(), copy.toString());
    }
}
