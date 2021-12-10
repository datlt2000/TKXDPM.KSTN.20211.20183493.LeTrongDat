package aism.dat.controller;

import java.sql.SQLException;
import java.util.List;

import aism.dat.entity.cart.Cart;
import aism.dat.entity.media.Media;

/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */
public class HomeController extends BaseController{


    /**
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
    public List getAllMedia() throws SQLException{
        return new Media().getAllMedia();
    }

}
