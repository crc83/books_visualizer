package org.sbelei.booksvis.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.utils.Array;

import org.sbelei.booksvis.nlp.UdpipeFacade;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class BooksVis implements ApplicationListener {
    Texture texture;
    SpriteBatch batch;
    float elapsed;
    BitmapFont font;
    /*
    private FreeTypeBitmapFontData font15;
    private FreeTypeBitmapFontData font22;
*/
    @Override
    public void create () {
        texture = new Texture(Gdx.files.internal("libgdx-logo.png"));
        batch = new SpriteBatch();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("arial.ttf"));

        /*
        font15 = generator.generateData(15);
        font22 = generator.generateData(80);
        generator.dispose();

        Texture textureBackground = new Texture(Gdx.files.internal("background.png"));
        TextureRegion region = new TextureRegion(textureBackground);
        font = new BitmapFont(font22,region, true); //or use alex answer to use custom font
        */

        font = generator.generateFont(40, "абвгґдеєжзиіїйклмнопрстуфхцчшщьюяАБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ!.?'\",0123456789", false);
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

            UdpipeFacade udpipe = new UdpipeFacade();

            String text = "У Сергійка було дев'ять яблук, а в Іринки 4.";
            String processed = udpipe.process(text);

            System.out.print(processed);
            font.draw(batch, processed, 550, 550);

            //font.draw(batch, "Hello World!", 500, 500);
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
