/**
 * ItemService Injected Singleton
 * @author Emily Marriott
 * 
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Project New Beginning
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pnb.utils.services;

import java.util.HashMap;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Singleton
public class ItemService {
	//Our little collection of custom items for quick lookup.
	private final HashMap<String,Item> customItems;
	
	@Inject
	public ItemService() {
		customItems = new HashMap<String,Item>();
	}
	
	public void registerItem(String name, Item item) {
		//Registering the item.
		GameRegistry.registerItem(item, name);
		
		//Initialize our custom item
		customItems.put(name, item);
	}
	
	public Item getItem(String name) {
		return customItems.get(name);
	}
}
