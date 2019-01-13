package com.hangover;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ResourceManager {
	
	//Stores all imageActors
	HashMap<String, ImageActor> im = new HashMap<String, ImageActor>();
	
	//Stores all entities
	HashMap<String, Entity> entities = new HashMap<String, Entity>();

	//Initialise imageActor and Entity stores
	public ResourceManager() {
		loadImages();
		loadEntities();
	}
	
	//Loads all imageActors from imData
	public void loadImages() {
		try {
			FileHandle fh = Gdx.files.internal("assets//imData");
			BufferedReader f = new BufferedReader(fh.reader());
			String line = f.readLine();
			String imName = "#";
			while(line != null) {
				ImageActor image = new ImageActor();
				line = line.replace("/n",  "");
				if(line.contains(":")) {
					imName = line.replace(":", "");
					line = f.readLine();
					line = line.replace(String.valueOf(line.toCharArray()[0]), "").replace(" ", "");
					image.setImage(line);
					im.put(imName, image);
				}
				
				line = f.readLine();
			}
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Gets imageActor from store based on name
	public ImageActor getImageActor(String name) {
		return im.get(name);
	}
	
	//Loads all entities from entityData file
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
						entityName = entityName.replace(" ", "");
						e = new Entity();
					}
				}
				else {
					line = line.replace(String.valueOf(line.toCharArray()[0]), "");
					if(data) {
						line = line.replace("/t", "");
						if(line.contains("speed")) {
							line = line.replaceAll("speed = ", "");
							e.setSpeed(Integer.parseInt(line.replace(" ", "")));
						}
						else if(line.contains("health")) {
							line = line.replaceAll("health = ", "");
							e.setMaxHealth(Integer.parseInt(line.replace(" ", "")));
						}
					}
					else if(anim) {
						String[] v = line.split(" ");
						if(v.length > 3) {
							e.storeAnim(v[0], v[1], Integer.parseInt(v[2]), Integer.parseInt(v[3]));
						}
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
	
	//Gets entity from store based on name
	public Entity getEntity(String name) {
		return entities.get(name);
	}
	
}
