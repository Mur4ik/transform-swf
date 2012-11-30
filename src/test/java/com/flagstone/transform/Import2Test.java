/*
 * Import2Test.java
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

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public final class Import2Test {

    private static String url;
    private static Map<Integer, String> table;

    @BeforeClass
    public static void initialize() {
        url = "ABC";

        table = new LinkedHashMap<Integer, String>();
        table.put(1, "A");
        table.put(2, "B");
        table.put(3, "C");
    }

    private transient Import2 fixture;

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForUrlWithNull() {
        fixture = new Import2(null, table);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForUrlWithEmpty() {
        fixture = new Import2("", table);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForIdentifierWithLowerBound() {
        fixture = new Import2(url, table);
        fixture.add(0, "A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForIdentifierWithUpperBound() {
        fixture = new Import2(url, table);
        fixture.add(65536, "A");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForNameWithNull() {
        fixture = new Import2(url, table);
        fixture.add(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAccessorForNameWithEmpty() {
        fixture = new Import2(url, table);
        fixture.add(1, "");
    }

    @Test
    public void checkCopy() {
        fixture = new Import2(url, table);
        final Import2 copy = fixture.copy();

        assertEquals(fixture.getUrl(), copy.getUrl());
        assertNotSame(fixture.getObjects(), copy.getObjects());
        assertEquals(fixture.toString(), copy.toString());
    }
}
