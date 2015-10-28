package pnb.warpstone.proxy;

import com.google.inject.Injector;

import pnb.utils.services.BlockService;
import pnb.utils.services.ItemService;

public class CommonProxy {
	
	protected Injector injector;
	protected ItemService is;
	protected BlockService bs;
	
	public void setInjector(Injector i) {
		injector = i;
		is = injector.getInstance(ItemService.class);
		bs = injector.getInstance(BlockService.class);
	}
	
	public void setRenders(){	
		//This should remain empty
	}
}
