package org.sbelei.booksvis.html;

import org.sbelei.booksvis.core.BooksVis;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class BooksVisHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new BooksVis();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
