package aism.dat.controller;

import java.util.List;

import aism.dat.entity.cart.Cart;
import aism.dat.entity.cart.CartMedia;
import aism.dat.entity.media.Media;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */
public class BaseController {
    
    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */
    public CartMedia checkMediaInCart(Media media){
        return Cart.getCart().checkMediaInCart(media);
    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */
    public List getListCartMedia(){
        return Cart.getCart().getListMedia();
    }
}
