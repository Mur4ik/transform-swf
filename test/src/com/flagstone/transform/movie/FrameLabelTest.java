/*
 * FrameLabelTest.java
 * Transform
 *
 * Copyright (c) 2009 Flagstone Software Ltd. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  * Neither the name of Flagstone Software Ltd. nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.flagstone.transform.movie;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import com.flagstone.transform.coder.CoderException;
import com.flagstone.transform.coder.SWFContext;
import com.flagstone.transform.coder.SWFDecoder;
import com.flagstone.transform.coder.SWFEncoder;

@SuppressWarnings( { 
	"PMD.LocalVariableCouldBeFinal",
	"PMD.JUnitAssertionsShouldIncludeMessage" 
})
public final class FrameLabelTest {
	
	private transient final String label = "Frame";
	private transient final boolean anchor = true;
	
	private transient FrameLabel fixture;
	
	private transient final byte[] empty = new byte[] { 
			(byte)0xC1, 0x0A, 0x00};
	private transient final byte[] encoded = new byte[] { 
			(byte)0xC7, 0x0A, 0x46, 0x72, 0x061, 0x6D, 0x65, 0x00, 0x01};
	private transient final byte[] extended = new byte[] { 
			(byte)0xFF, 0x0A, 0x07, 0x00, 0x00, 0x00, 0x46, 0x72, 0x061, 0x6D, 0x65, 0x00, 0x01};

	@Test(expected=IllegalArgumentException.class)
	public void checkAccessorForLabelWithNull() {
		fixture = new FrameLabel((String)null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void checkAccessorForLabelWithEmpty() {
		fixture = new FrameLabel("");
	}
	
	@Test
	public void checkCopy() {
		fixture = new FrameLabel(label, anchor);
		FrameLabel copy = fixture.copy();

		assertEquals(fixture.getLabel(), copy.getLabel());
		assertEquals(fixture.isAnchor(), copy.isAnchor());
		assertEquals(fixture.toString(), copy.toString());
	}
	
	@Test
	public void encode() throws CoderException {
		SWFEncoder encoder = new SWFEncoder(encoded.length);		
		SWFContext context = new SWFContext();

		fixture = new FrameLabel(label, anchor);
		assertEquals(encoded.length, fixture.prepareToEncode(encoder, context));
		fixture.encode(encoder, context);
		
		assertTrue(encoder.eof());
		assertArrayEquals(encoded, encoder.getData());
	}
	
	@Test
	public void decode() throws CoderException {
		SWFDecoder decoder = new SWFDecoder(encoded);
		SWFContext context = new SWFContext();

		fixture = new FrameLabel(decoder, context);
		
		assertTrue(decoder.eof());
		assertEquals(label, fixture.getLabel());
		assertEquals(anchor, fixture.isAnchor());
	}
	
	@Test
	public void decodeExtended() throws CoderException {
		SWFDecoder decoder = new SWFDecoder(extended);
		SWFContext context = new SWFContext();

		fixture = new FrameLabel(decoder, context);
		
		assertTrue(decoder.eof());
		assertEquals(label, fixture.getLabel());
		assertEquals(anchor, fixture.isAnchor());
	}
}