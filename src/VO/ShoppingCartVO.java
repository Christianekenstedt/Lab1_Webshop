package VO;

import BL.ShoppingCartItem;

import java.util.Collection;
import java.util.Vector;

/**
 * Created by Anton on 2016-09-29.
 */
public class ShoppingCartVO {
    private Collection<ShoppingCartItemVO> items;
    private int id;

    private ShoppingCartVO(Collection<ShoppingCartItemVO> items, int id){

    }
}
