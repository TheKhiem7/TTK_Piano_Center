package Khiemnvd.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TheKhiem7
 */
public class CartObj implements Serializable{
    private String customerID;
    private Map<String, Integer>item;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Map<String, Integer> getItem() {
        return item;
    }

    public void setItem(Map<String, Integer> item) {
        this.item = item;
    }
    
    public void addToCart(String title){
        if(item==null){
            item = new HashMap<>();
        }
        int quantity = 1;
        if(item.containsKey(title)){
            quantity = item.get(title) + 1;
        }
        item.put(title, quantity);
    }
    
    public void removeFromCart(String title){
        if(item==null){
            return;
        }
        if(item.containsKey(title)){
            item.remove(title);
            if(item.isEmpty()){
                item = null;
            }
        }
    }
}
