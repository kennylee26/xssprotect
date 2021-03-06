/**
 * Copyright 2007 Gerard Toonstra
 * 
 * Licensed under the terms of the Apache Software License v2
 *
 * This file is part of the XSS Protect library
 */

package com.blogspot.radialmind.html;

import java.io.IOException;
import java.io.Writer;

/**
 * Holder class for text within a node. Text within a node is the text between
 * the <p> and </p> tags.
 * 
 * @author gt
 *
 */
public class TextNode extends Node implements IHTMLVisitor {
	
	private StringBuilder text = new StringBuilder();
	private String tagName = null;
	
	public TextNode( String tagName, String text ) {
		super();
		this.text.append( text );
	}
	
	// Add text to the node
	public void addText( String text ) {
		this.text.append( text );
	}
	
	// Return the text	
	public String getText() {
		return text.toString();
	}
	
	// Write this node to the stream
	public void writeAll( Writer writer, IHTMLFilter htmlFilter, boolean convertIntoValidXML, boolean filterText ) throws IOException {
		
		if ( filterText ) {
			return;
		}
		
		if ( htmlFilter != null ) {
			String newText = htmlFilter.modifyNodeText( tagName, getText() );
			writer.append( newText );
		} else {
			writer.append( getText() );
		}
	}
}
