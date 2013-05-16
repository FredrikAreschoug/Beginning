package post.client.util;

import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import post.client.Texture;

import org.lwjgl.BufferUtils;

import de.matthiasmann.twl.utils.PNGDecoder;

public class TextureManager {
	private static final String RESOURCE_PATH = "res/";
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public static Texture get(String name) {
		if(textures.get(name) != null) {
			return textures.get(name);
		} else {
			Texture t = load(name);
			textures.put(name, t);
			return t;
		}
	}
	
	public static Texture load(String name) {
		Texture t = null;
		InputStream in = null;
		int texture = glGenTextures();

		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, texture);

		try {
			in = new FileInputStream(RESOURCE_PATH + name + ".png");
			PNGDecoder decoder = new PNGDecoder(in);

			// Basically I'm initializing an empty image in bytes so that I can change it
			ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
			buffer.flip();

			glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_RECTANGLE_ARB, GL_TEXTURE_MIN_FILTER, GL_NEAREST); 
			glTexImage2D(GL_TEXTURE_RECTANGLE_ARB, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
			
			t = new Texture().setName(name).setId(texture).setWidth(decoder.getWidth()).setHeight(decoder.getHeight());
		} catch(FileNotFoundException e) {
			System.out.println("Texture file could not be found.");
		} catch(IOException e) {
			System.out.print("Failed to load the texture file.");
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Unbind workingarea
		glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);

		return t;
	}
}
