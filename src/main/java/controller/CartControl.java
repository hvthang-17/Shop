package controller;

import dao.ProductDao;
import model.CartProduct;
import model.Order;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartControl {

    private final ProductDao productDao;

    @Autowired
    public CartControl(ProductDao productDao) {
        this.productDao = productDao;
    }

    private double removeCartProduct(int productId, Order order, double totalPrice) {
        List<CartProduct> list = order.getCartProducts();
        Iterator<CartProduct> iterator = list.iterator();
        while (iterator.hasNext()) {
            CartProduct cartProduct = iterator.next();
            if (cartProduct.getProduct().getId() == productId) {
                totalPrice -= (cartProduct.getPrice() * cartProduct.getQuantity());
                iterator.remove();
            }
        }
        return totalPrice;
    }

    @GetMapping("/cart")
    public String cart(
            @RequestParam(value = "remove-product-id", required = false) Integer removeProductId,
            @RequestParam(value = "product-id", required = false) Integer productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
            HttpSession session,
            Model model
    ) {
        // Lấy tổng tiền hiện tại trong session
        Double totalPrice = (Double) session.getAttribute("total_price");
        if (totalPrice == null) totalPrice = 0d;

        if (removeProductId != null) {
            Order order = (Order) session.getAttribute("order");
            if (order != null) {
                totalPrice = removeCartProduct(removeProductId, order, totalPrice);
                session.setAttribute("total_price", totalPrice);
                session.setAttribute("order", order);
            }
            return "redirect:/cart.jsp";
        }

        if (productId != null) {
            Product product = productDao.getProduct(productId);
            if (product != null) {
                if (product.getAmount() < quantity) {
                    // Nếu số lượng yêu cầu lớn hơn số lượng còn hàng, redirect về trang chi tiết với thông báo
                    return "redirect:/product-detail?id=" + product.getId() + "&invalid-quantity=1";
                }

                Order order = (Order) session.getAttribute("order");
                if (order == null) {
                    order = new Order();
                    List<CartProduct> list = new ArrayList<>();

                    CartProduct cartProduct = new CartProduct();
                    cartProduct.setQuantity(quantity);
                    cartProduct.setProduct(product);
                    cartProduct.setPrice(product.getPrice());

                    totalPrice += product.getPrice() * quantity;

                    list.add(cartProduct);
                    order.setCartProducts(list);

                    session.setAttribute("total_price", totalPrice);
                    session.setAttribute("order", order);
                } else {
                    List<CartProduct> list = order.getCartProducts();
                    boolean flag = false;
                    for (CartProduct cartProduct : list) {
                        if (cartProduct.getProduct().getId() == product.getId()) {
                            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
                            totalPrice += product.getPrice() * quantity;
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        CartProduct cartProduct = new CartProduct();
                        cartProduct.setQuantity(quantity);
                        cartProduct.setProduct(product);
                        cartProduct.setPrice(product.getPrice());
                        totalPrice += product.getPrice() * quantity;
                        list.add(cartProduct);
                    }
                    session.setAttribute("total_price", totalPrice);
                    session.setAttribute("order", order);
                }
            }
            return "redirect:/product-detail?id=" + productId;
        }

        // Nếu không có hành động nào, hiển thị trang giỏ hàng
        return "cart";  // map tới cart.jsp qua ViewResolver (ví dụ cấu hình InternalResourceViewResolver)
    }
}
