package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/list")
    public String listUsers(Model model){
        model.addAttribute("users", userService.listAll());
        return "user/list";
    }

    @RequestMapping("/user/show/{id}")
    public String getUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }

    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "user/userform";
    }

    @RequestMapping("/user/new")
    public String newProduct(Model model){
        model.addAttribute("user", new Product());
        return "user/userform";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveOrUpdateProduct(User user){
        User savedUser = userService.saveOrUpdate(user);
        return "redirect:/user/show/" + savedUser.getId();
    }

    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/user/list";
    }
}
