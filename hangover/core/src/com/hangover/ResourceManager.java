package com.hangover;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ResourceManager {
	
	HashMap<String, ImageActor> im = new HashMap<String, ImageActor>();
	HashMap<String, Entity> entities = new HashMap<String, Entity>();

	public ResourceManager() {
		loadImages();
		loadEntities();
	}
	
	public void loadImages() {
		try {
			FileHandle fh = Gdx.files.internal("assets//imData");
			BufferedReader f = new BufferedReader(fh.reader());
			String line = f.readLine();
			String imName = "#";
			ImageActor image = new ImageActor();
			while(line != null) {
				line = line.replace("/n",  "");
				if(line.contains(":")) {
					if (imName != "#") {
						im.put(imName, image);
					}
					imName = line.replace(":", "");
				}
				else if(!line.contains("#")){
					line = line.replace(String.valueOf(line.toCharArray()[0]), "").replace(" ", "");
					image.setImage(line);
				}
				
				line = f.readLine();
			}
			if(imName != null) {
				im.put(imName, image);
			}
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ImageActor getImageActor(String name) {
		return im.get(name);
	}
	
	public void loadEntities() {
		try {
			BufferedReader f = new BufferedReader(Gdx.files.internal("assets//entityData").reader());
			String line = f.readLine();
			String entityName = null;
			Entity e = new Entity();
			boolean anim = false;
			boolean data = false;
			while(line != null) {
				line = line.replace("/n", "");
				if (line.contains(":")) {
					if(line.contains("Data")) {
						data = true;
						anim = false;
					}
					else if(line.contains("Anim")) {
						data = false;
						anim = true;
					}
					else {
						data = false;
						anim = false;
						if (entityName != null) {
							entities.put(entityName, e);
						}
						entityName = line.replace(":", "");
						e = new Entity();
					}
				}
				else {
					line = line.replace(String.valueOf(line.toCharArray()[0]), "");
					if(data) {
						line = line.replace("/t", "");
						if(line.contains("speed")) {
							line = line.replaceAll("speed = ", "");
							e.setSpeed(Integer.parseInt(line));
						}
						else if(line.contains("health")) {
							line = line.replaceAll("health = ", "");
							e.setMaxHealth(Integer.parseInt(line));
						}
					}
					else if(anim) {
						String[] v = line.split(" ");
						e.storeAnim(v[0], v[1], Integer.parseInt(v[2]), Integer.parseInt(v[3]));
					}
				}
				line = f.readLine();
			}
			if(entityName != null) {
				entities.put(entityName, e);
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Entity getEntity(String name) {
		return entities.get(name);
	}
	
}
