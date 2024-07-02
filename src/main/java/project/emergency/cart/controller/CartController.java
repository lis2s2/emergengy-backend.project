package project.emergency.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.emergency.cart.dto.CartDTO;
import project.emergency.cart.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    CartService service;

    @PostMapping("/register")
    public String registerPost(CartDTO dto, RedirectAttributes redirectAttributes) {
        int no = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", no);
        return "redirect:/board/list";
    }

}
