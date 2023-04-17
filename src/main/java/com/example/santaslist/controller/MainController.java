package com.example.santaslist.controller;

import com.example.santaslist.model.User;
import com.example.santaslist.model.Wish;
import com.example.santaslist.repository.UserRepository;
import com.example.santaslist.repository.WishRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@ControllerAdvice
@Controller
public class MainController {

    UserRepository userRepository;
    WishRepository wishRepository;

    @ModelAttribute("currentuser")
    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        return currentUser;
    }

    public MainController(UserRepository userRepository, WishRepository wishRepository){
        this.userRepository = userRepository;
        this.wishRepository = wishRepository;
    }

    @GetMapping("/")
    public String homepage(){
        return "homepage";
    }

    @PostMapping("/loginuser")
    public String loginUser(@RequestParam("email") String checkEmail, @RequestParam("userPassword") String checkPassword, Model model, HttpSession session){
        User user = new User();
        model.addAttribute("user",user);
        for (User checkuser: userRepository.getAllUsers()) {
            String actualEmail = checkuser.getEmail();
            String actualPassword = checkuser.getUserPassword();
            if(actualPassword.equals(checkPassword) && actualEmail.equals(checkEmail))
            {
                model.addAttribute("currentuser",checkuser);
                session.setAttribute("currentuser", checkuser);
                User currentuser = (User) session.getAttribute("currentuser");
                System.out.println(currentuser);
                return "redirect:/santalist/" + checkuser.getUserID();
            }
        }
        return "login";
    }



    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("users", wishRepository.getAll());
        //testdata
        for(User user : userRepository.getAllUsers())
        {
            System.out.println(user);
        }
        User user = new User();
        model.addAttribute("user",user);
        return "signup";
    }

    @GetMapping("/createwish")
    public String showCreate(){
        return "createwish";
    }

    @PostMapping("/createuser")
    public String createUser(@RequestParam("email") String newEmail, @RequestParam("userPassword") String newPassword, @RequestParam("firstName") String newFirstName, @RequestParam("lastName") String newLastName)
    {
        // TODO: Implement bellow methods in model and in userdirectory and also the parameters in request param

        User user = new User();
        user.setEmail(newEmail);
        user.setUserPassword(newPassword);
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);

        userRepository.addUser(user);

        return "redirect:/";

    }

    @PostMapping("/createWish")
    public String createWish(@RequestParam("userID") int theID, @RequestParam("wishName") String newWishName, @RequestParam("price") float newPrice, @RequestParam("priority") int newPriority, @RequestParam("wishDescription") String newWishDescription, @RequestParam("url") String newUrl, @RequestParam("reserved") boolean reserved)
    {
        // TODO: Implement bellow methods in model and in wishdirectory and also the parameters in request param

        Wish wish = new Wish();
        wish.setUserID(theID);
        wish.setWishName(newWishName);
        wish.setPrice(newPrice);
        wish.setPriority(newPriority);
        wish.setWishDescription(newWishDescription);
        wish.setUrl(newUrl);
        wish.setReserved(reserved);

        wishRepository.addWish(wish);

        return "redirect:/";

    }

    @GetMapping("/modifywish/{number}")
    public String showModifiedWish(@PathVariable("number") int updateNumber, Model model)
    {
        // TODO: Implement bellow methods
        /*
        Wish updateWish = WishRepository.findWishByNumber(updateNumber);

        model.addAttribute("wish", updateWish);

         */
        return "modifywish";
    }

    @GetMapping("/modifywish")
    public String modifyWish(@RequestParam("userID") int theID, @RequestParam("wishName") String updateWishName, @RequestParam("price") float updatePrice, @RequestParam("priority") int updatePriority, @RequestParam("wishDescription") String updateWishDescription, @RequestParam("url") String updateUrl, @RequestParam("reserved") boolean updateReserved)
    {
        // TODO: Implement bellow methods
        /*
        Wish updateWish = new Wish(theID, updateWishName, updatePrice, updatePriority, updateWishDescription, updateUrl, updateReserved);

        wishRepository.updateWish(updateWish);

         */

        return "redirect:/";
    }

    @PostMapping("/reserve")
    public String reserveWish(@RequestParam("reserved") boolean reserved, @RequestParam("wishId") int wishID)
    {
        // Fetch the Wish object from the repository using the wishID
        Wish wish = wishRepository.findById(wishID);

        // Set the reserved status of the wish
        wish.setReserved(reserved);

        // Update the Wish object in the repository
        wishRepository.updateWish(wish);

        return "redirect:/santalist";
    }

    @GetMapping("/delete/{number}")
    public String deleteWish(@PathVariable("number") int number)
    {
        // TODO: Implement bellow method
        /*
        wishRepository.deleteByID(number);

         */
        return "redirect:/";
    }


    @GetMapping("/santalist")
    public String santaList(Model model)
    {
        // TODO: Implement bellow method wishrepository.getAll()
        model.addAttribute("wishes", wishRepository.getAll());
        //testdata
        for(Wish wish : wishRepository.getAll())
        {
            System.out.println(wish);
        }
        System.out.println();
        return "santalist";
    }

    @GetMapping("/santalist/{id}")
    public String santaListForSpecificUser(@PathVariable("id") int userID, Model model)
    {
        // TODO: Implement bellow method wishrepository.getAll()
        model.addAttribute("wishes", wishRepository.getAllForUser(userID));
        //testdata
        return "santalist";
    }

}
