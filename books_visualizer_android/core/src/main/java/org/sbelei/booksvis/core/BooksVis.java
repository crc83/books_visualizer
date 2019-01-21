package org.sbelei.booksvis.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class BooksVis implements ApplicationListener {
    Texture texture;
    SpriteBatch batch;
    float elapsed;
    BitmapFont font;

    @Override
    public void create () {
        texture = new Texture(Gdx.files.internal("libgdx-logo.png"));
        batch = new SpriteBatch();
        font = new BitmapFont();//Gdx.files.internal("Calibri.fnt"),Gdx.files.internal("Calibri.png"),false); //or use alex answer to use custom font
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void render () {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (font != null) {
            //font.draw(batch, "Hello World!", 500, 500);
            font.draw(batch, "Привіт світ", 550, 550);
        }
        batch.draw(texture, 100+100*(float)Math.cos(elapsed), 100+25*(float)Math.sin(elapsed));
        batch.end();
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
    }
}
