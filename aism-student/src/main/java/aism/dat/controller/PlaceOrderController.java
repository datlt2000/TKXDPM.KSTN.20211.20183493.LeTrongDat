package aism.dat.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import aism.dat.entity.cart.Cart;
import aism.dat.entity.cart.CartMedia;
import aism.dat.entity.invoice.Invoice;
import aism.dat.entity.order.Order;
import aism.dat.entity.order.OrderMedia;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author nguyenlm
 */
public class PlaceOrderController extends BaseController{

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = aism.dat.utils.Utils.getLogger(PlaceOrderController.class.getName());

    private static final Pattern charPattern = Pattern.compile("^[\\p{L} ]+$", Pattern.UNICODE_CHARACTER_CLASS );
    private static final Pattern unSpecalPattern = Pattern.compile("^[\\w ]+$", Pattern.UNICODE_CHARACTER_CLASS );
    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	if (validatePhoneNumber(info.get("phone_number"))) {
    	    if(validateAddress(info.get("address"))) {
    	        if(validateName(info.get("name"))) {
    	            return;
                }
            }
        }
    	throw new InterruptedException("Invalid delivery info");
    }
    
    public boolean validatePhoneNumber(String phoneNumber) {
        if(!StringUtils.isNumeric(phoneNumber)) {
            return false;
        }
    	if(phoneNumber.length() > 10) {
    	    return false;
        }
    	if(!phoneNumber.startsWith("0")) {
    	    return false;
        }
    	return true;
    }
    
    public boolean validateName(String name) {
        if(StringUtils.isEmpty(name)) {
            return false;
        }
        Matcher matcher = charPattern.matcher(name);
    	return matcher.find();
    }
    
    public boolean validateAddress(String address) {
        if(StringUtils.isEmpty(address)) {
            return false;
        }
        Matcher matcher = unSpecalPattern.matcher(address);
        return matcher.find();
    }

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
